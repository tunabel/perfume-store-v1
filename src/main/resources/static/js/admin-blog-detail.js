$(document).ready(function () {
    //summernote HTML text editor
    $('#input-short-desc').summernote();
    $('#input-full-desc').summernote();

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.sku-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    let isShortImgNew = false;
    let isFullImgNew = false;

    //upload new shortImg. and delete the old one
    $("#input-short-img").change(function () {

        let oldShortImg = new FormData();
        oldShortImg.append("filePath", $('#short-img').attr('src'));
        console.log($('#short-img').attr('src'));

        readURL(this);
        var newShortImg = new FormData();
        newShortImg.append('file', $("#input-short-img")[0].files[0]);
        //upload new image
        axios.post("/api/upload/upload-blogimg", newShortImg).then(function (res) {
                if (res.data.successful) {
                    $('#short-img').attr('src', res.data.link);
                    isShortImgNew = true;
                }
            }, function (err) {
                console.log("Image upload error");
            }
        );
        //delete old image
        axios.post("/api/upload/delete", oldShortImg).then(function (res) {
            if (res.data.successful) {
                console.log("Old Short Img deleted")
            }
        }, function (err) {
            console.log("Image deletion error");
        })

    });


    //tag list to submit
    let currTagList = [];

    //tag label clicking
    $('.tag-label').on('click', function () {
        if ($(this).prev().is(':checked')) {
            $(this).prev().attr('checked', false);
        } else {
            $(this).prev().attr('checked', true);
        }
    })

    //create new tag
    $('#new-tag-btn').on('click', function () {
        let newTag = $('#input-new-tag').val();
        let newTagId;

        let tagData = new FormData();
        linkPost = "/api/blogtag/newTag";

        tagData.append('newTag', newTag);

        //submit new tag for creation in backend. after that create new checkbox for blog content submission later.
        axios.post(linkPost, tagData).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Tag created!',
                    res.data.message,
                    'success'
                );
                newTagId = res.data.data.id;
                $("#new-tag-list").append(
                    "<div><input type='checkbox' checked name='tag' value=" + newTagId + "><label class='tag-label ml-2'>" + newTag + "</label></div>"
                )
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function (err) {
            swal(
                'Error',
                'Some error when creating tag',
                'error'
            );
        })
    })

    $('.submit-btn').on('click', function () {
        //insert checked tag into currTagList
        $.each($("input[name='tag']:checked"), function () {
            currTagList.push($(this).val());
        });
        console.log(currTagList.join("-"));
    })
});