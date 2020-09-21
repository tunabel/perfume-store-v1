$(document).ready(function () {
    var dataProduct = {};
    let newImage = false;
    let deleteSkuId = -1;

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.sku-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#change-sku-image").change(function () {
        readURL(this);
        var formData = new FormData();
        formData.append('file', $("#change-sku-image")[0].files[0]);
        axios.post("/api/upload/upload-skuimg", formData).then(function (res) {
            if (res.data.successful) {
                $('.sku-img').attr('src', res.data.link);
                newImage = true;
            }
        }, function (err) {
            console.log("Image upload error");
        });
    });


    $("#new-product").on("click", function () {
        dataProduct = {};
        $('#input-product-name').val("");
        $('#input-product-desc').val("");
        $("#input-product-brand").val("");
        $("#input-product-scent").val("");
        $("#input-product-type").val("");
        $("#input-product-gender").val("");
        $('.sku-img').attr('src', '/../images/blank_avatar.png');

    });


    $(".edit-product").on("click", function () {
        var pdInfo = $(this).data("product");

        axios.get("/api/sku/detail/" + pdInfo).then(function (res) {
            if (res.data.successful) {
                data = res.data.data;
                dataProduct.id = data.id;
                $("#input-sku-name").val(data.name);
                $("#input-sku-spec").val(data.spec);
                $("#input-sku-quantity").val(data.quantity);
                $("#input-sku-price").val(data.price);
                $("#input-sku-mainSku").val(data.mainSku);

                if (data.imageURL != null) {
                    $('.sku-img').attr('src', '/../' + data.imageURL);
                } else {
                    $('.sku-img').attr('src', '/../images/blank_avatar.png');
                }
            } else {
                console.log("Error");
            }
        }, function (err) {
            console.log("Error");
        })
    });


    $(".btn-save-product").on("click", function () {

        //if there isn't new image and the photo is the default blank photo
        if (!newImage && $('.sku-img').attr('src') == "/../images/blank_avatar.png") {
            swal(
                'Error',
                'You need to upload a photo',
                'error'
            );
            return;
        }

        dataProduct.name = $('#input-sku-name').val();
        dataProduct.price = $("#input-sku-price").val();
        dataProduct.quantity = $("#input-sku-quantity").val();
        //If a new image is uploaded, then the URL string is like //images/abcxyz
        if (newImage) {
            dataProduct.imageURL = $(".sku-img").attr('src').substring(1);
        }

        //if the image is not changed then the URL string is /../images/abcxyz
        if (!newImage && $('.sku-img').attr('src') != null) {
            dataProduct.imageURL = $(".sku-img").attr('src').substring(4);
        }

        dataProduct.productId = $("#productId").val();
        dataProduct.mainSku = $("#input-sku-mainSku").val();
        dataProduct.spec = $('#input-sku-spec').val();

        var linkPost = "/api/sku/create";
        if (dataProduct.id) {
            linkPost = "/api/sku/update/" + dataProduct.id;
        }

        console.log(dataProduct);

        axios.post(linkPost, dataProduct).then(function (res) {
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
                'Some error when saving product',
                'error'
            );
        })
    });


    $(".delete-modal-btn").on("click", function () {
        deleteSkuId = $(this).data("product");
    })

    $(".delete-sku-btn").on("click", function () {
        axios.delete("/api/sku/delete/" + deleteSkuId).then(function (res) {
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
                'Some error when deleting SKU',
                'error'
            );
        })
    })

});