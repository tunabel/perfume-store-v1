$(document).ready(function() {
    //summernote HTML text editor
    $('#input-brand-desc').summernote({
        toolbar: [
            // [groupName, [list of button]]
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough', 'superscript', 'subscript']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']]
        ],
        dialogsInBody: true
    });

    var dataUpload = {};

    $("#new-brand").on("click", function () {
        dataUpload = {};
        $('#input-brand-name').val("");
        $('#input-brand-desc').val("");
    });

    $(".edit-brand").on("click", function () {
        var pdInfo = $(this).data("brand");

        axios.get("/api/brand/detail/" + pdInfo).then(function(res){
            if(res.data.successful) {
                data = res.data.data;
                dataUpload.id = data.id;
                $("#input-brand-name").val(data.name);

                // $(".note-editable").val(data.description);

                $('#input-brand-desc').summernote('insertText', data.description);
            } else {
                console.log("Error getting brand data");
            }
        }, function(err){
            console.log("Error getting brand data");
        })
    });

    $(".btn-save-brand").on("click", function () {
        if($("#input-brand-name").val() === "" || $("#input-brand-desc").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }

        dataUpload.name = $('#input-brand-name').val();
        dataUpload.description = $('#input-brand-desc').val();

        var linkPost = "/api/brand/create";
        if (dataUpload.id) {
            linkPost = "/api/brand/update/" + dataUpload.id;
        }

        axios.post(linkPost, dataUpload).then(function(res){
            if(res.data.successful) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            swal(
                'Error',
                'Some error when saving brand',
                'error'
            );
        })
    });

});