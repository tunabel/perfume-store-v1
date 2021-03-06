$(document).ready(function () {
    let tagData = {};
    let tagIdToDelete = -1;
    let tagIdToRename = -1;

    //button to open new tag modal
    $("#new-tag-btn").on("click", function () {
        tagData = {};
        tagIdToDelete = -1;
        tagIdToRename = -1;
        $('#input-tag-name').val("");
    });
    //button to open delete modal
    $(".delete-modal-btn").on("click", function () {
        tagData = {};
        let tagInfo = $(this).data("tag").split(";");
        tagIdToDelete = tagInfo[0];
        $(".tag-delete-name").text(tagInfo[1]);
    })
    //button to confirm tag deletion
    $(".delete-tag-btn").on("click", function () {
        axios.delete("/api/blogtag/tag/delete/" + tagIdToDelete).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Deletion succeeded!',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.reload();
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
                'Some error when deleting tag',
                'error'
            );
        })
    })


    $(".edit-tag-btn").on('click', function () {
        let tagInfo = $(this).data("tag").split(";");
        tagIdToRename = tagInfo[0];
        $("#input-tag-name").val(tagInfo[1]);
    });

    $(".save-tag-btn").on('click', function () {

        if ($("#input-tag-name").val() === "") {
            swal(
                'Error',
                'You need to set the tag\'s name',
                'error'
            );
            return;
        }

        if (tagIdToRename > 0) {
            tagData.id = tagIdToRename;
        }
        tagData.name = $("#input-tag-name").val();
        let linkPost = "/api/blogtag/tag/save";
        axios.post(linkPost, tagData).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Success!',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.reload();
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
                'Some error when saving tag',
                'error'
            );
        })

    })

    $(".switch-status-btn").on('click', function () {
        let blogId = $(this).data("blog");
        let linkPost = "/api/blogtag/switch-status/";
        axios.post(linkPost + blogId).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Success!',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.reload();
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
                'Some error when updating status',
                'error'
            );
        })

    })

});