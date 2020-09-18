$(document).ready(function () {
    let imageId = 0;
    let productId = $("#productId").val();
    $(".edit-product-image").change(function () {

        let formData = new FormData();

        imageId = $(this).next().text();
        formData.append('file', $("#" + imageId)[0].files[0]);

        axios.post("/api/upload/upload-skuimg", formData).then(function (res) {
            if (res.data.successful) {
                $('#image' + imageId).attr('src', res.data.link);
            }
        }, function (err) {
            console.log("Image upload error");
        });
    });

    $(".save-image-btn").on("click", function () {
        let formData = new FormData();
        linkPost = "/api/product-image/update/" + imageId;

        formData.append('imageURL', $("#image" + imageId).attr("src").substring(1));

        axios.post(linkPost, formData).then(function (res) {
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
                'Some error when saving image',
                'error'
            );
        })
    });

    $("#new-image-btn").change(function () {

        let formData = new FormData();

        formData.append('file', $("#new-image-btn")[0].files[0]);

        axios.post("/api/upload/upload-skuimg", formData).then(function (res) {
            if (res.data.successful) {
                let newImageURL = res.data.link.substring(1);
                linkPost = "/api/product-image/create";
                let postFormData = new FormData();
                postFormData.append('imageURL', newImageURL);
                postFormData.append("productId", productId);

                console.log(postFormData);

                axios.post(linkPost, postFormData).then(function (res) {
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
                        'Some error when saving image',
                        'error'
                    );
                })
            }
        }, function (err) {
            console.log("Image upload error");
        });
    });

    $(".delete-modal-btn").on("click", function () {
        imageId = $(this).data("image");
    })

    $(".delete-image-btn").on("click", function () {
        axios.delete("/api/product-image/delete/" + imageId).then(function(res){
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
                'Some error when deleting image',
                'error'
            );
        })
    })
});