$(document).ready(function() {
    //summernote HTML text editor
    $('#input-short-desc').summernote();
    $('#input-full-desc').summernote();
    //
    let currTagList = [];

    //tag label clicking
    $('.tag-label').on('click', function() {
        if ( $(this).prev().is(':checked')) {
            $(this).prev().attr('checked', false);
        } else {
            $(this).prev().attr('checked', true);
        }
    })

    $('.submit-btn').on('click', function () {
        //insert checked tag into currTagList
        $.each($("input[name='tag']:checked"), function(){
            currTagList.push($(this).val());
        });
        console.log(currTagList.join("-"));
    })

    //create new tag
    $('#new-tag-btn').on('click', function () {
        let newTag = $('#input-new-tag').val();
        let newTagId;

        let tagData = new FormData();
        linkPost = "/api/blogtag/newTag";

        tagData.append('newTag', newTag);

        axios.post(linkPost, tagData).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Tag created!',
                    res.data.message,
                    'success'
                );
                newTagId = res.data.data.id;
                $("#new-tag-list").append(
                    "<div><input type='checkbox' checked name='tag' value="+newTagId+"><label class='tag-label ml-2'>"+newTag+"</label></div>"
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
});