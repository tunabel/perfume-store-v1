$(document).ready(function () {

    let dataProduct = {};

    $("#new-tag-btn").on("click", function () {
        dataProduct = {};
        $('#input-product-name').val("");
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

    $(".edit-product").on("click", function () {
        var pdInfo = $(this).data("product");

        axios.get("/api/product/detail/" + pdInfo).then(function(res){
            if(res.data.successful) {
                data = res.data.data;
                dataProduct.id = data.id;
                $("#input-product-name").val(data.name);
                $("#input-product-desc").val(data.description);
                $("#input-product-brand").val(data.brandId);
                $("#input-product-scent").val(data.scentId);
                $("#input-product-type").val(data.typeId);
                $("#input-product-gender").val(data.gender);
                if(data.mainImageURL != null) {
                    $('.product-img').attr('src', '/../'+data.mainImageURL);
                } else {
                    $('.product-img').attr('src', '/../images/blank_avatar.png');
                }
            } else {
                console.log("Error");
            }
        }, function(err){
            console.log("Error");
        })
    });
});