$(document).ready(function () {
    let tagData = {};
    let tagIdToDelete = -1;
    let tagIdToRename = -1;

    $("#new-tag-btn").on("click", function () {
        tagData = {};
        tagIdToDelete = -1;
        tagIdToRename = -1;

        $('#input-tag-name').val("");
    });
    //button to open modal
    $(".delete-modal-btn").on("click", function () {
        tagData = {};
        tagIdToDelete = $(this).data("id");
        $(".tag-delete-name").text($(this).data("name"))
    })

    $(".delete-tag-btn").on("click", function () {
        axios.delete("/api/blogtag/tag/delete/" + tagIdToDelete).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Good job!',
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
        console.log(tagData);
        var linkPost = "/api/blogtag/tag/save";

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


});