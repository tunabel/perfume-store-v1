$(document).ready(function () {
    //summernote HTML text editor
    $('#input-short-desc').summernote();
    $('#input-full-desc').summernote();

    //tag list to submit
    let currTagList = [];
    //blog data to submit
    let blogData = {};

    $('.new-blog-btn').on('click', function () {
        currTagList =[];
        blogData = {};
        $('#blog-id').attr('src', '');
        $('#input-blog-title').val('');
        $("#blog-date").text('');
        $("#short-img").attr('src', '');
        $("#full-img").attr('src', '');
        $('#input-short-desc').summernote('reset');
        $('#input-full-desc').summernote('reset');
        $('input:checkbox').removeAttr('checked');
        $('#new-tag-list').empty();
        $('#input-blog-status').val(0);
        // $(this).val('uncheck all');



    })

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

    //upload new fullImg. and delete the old one
    $("#input-full-img").change(function () {

        let oldFullImg = new FormData();
        oldFullImg.append("filePath", $('#full-img').attr('src'));
        console.log($('#full-img').attr('src'));

        readURL(this);
        var newFullImg = new FormData();
        newFullImg.append('file', $("#input-full-img")[0].files[0]);
        //upload new image
        axios.post("/api/upload/upload-blogimg", newFullImg).then(function (res) {
                if (res.data.successful) {
                    $('#full-img').attr('src', res.data.link);
                    isFullImgNew = true;
                }
            }, function (err) {
                console.log("Image upload error");
            }
        );
        //delete old image
        axios.post("/api/upload/delete", oldFullImg).then(function (res) {
            if (res.data.successful) {
                console.log("Old Full Img deleted")
            }
        }, function (err) {
            console.log("Image deletion error");
        })
    });

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

    //submit btn
    $('.submit-btn').on('click', function () {
        //insert checked tag into currTagList
        $.each($("input[name='tag']:checked"), function () {
            currTagList.push($(this).val());
        });

        blogData.id = $('#blog-id').text();
        blogData.title = $('#input-blog-title').val();
        //If a new image is uploaded, then the URL string is like //images/abcxyz
        if (isShortImgNew) {
            blogData.shortImg = $('#short-img').attr('src').substring(1);
        } else {
            //if the image is not changed then the URL string is ../../images/abcxyz
            blogData.shortImg = $('#short-img').attr('src').substring(6)
        }

        blogData.shortDesc = $('#input-short-desc').summernote('code');
        //If a new image is uploaded, then the URL string is like //images/abcxyz
        if (isFullImgNew) {
            blogData.fullImg = $('#full-img').attr('src').substring(1);
        } else {
            //if the image is not changed then the URL string is ../../images/abcxyz
            blogData.fullImg = $('#full-img').attr('src').substring(6)
        }
        blogData.fullDesc = $('#input-full-desc').summernote('code');
        blogData.tagIdList = currTagList;
        blogData.status = $('#input-blog-status').val();

        console.log(blogData);

        let linkPost = "/api/blogtag/update"
        //submit blog.
        axios.post(linkPost, blogData).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Success!',
                    res.data.message,
                    'success'
                ).then(function () {
                    window.location.replace("/admin/blog")
                });
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
                'Some error when saving blog',
                'error'
            );
        })

    })
});