$(document).ready(function() {
    var dataProduct = {};

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('.sku-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#change-sku-image").change(function() {
        readURL(this);
        var formData = new FormData();
        formData.append('file', $("#change-sku-image")[0].files[0]);
        axios.post("/api/upload/upload-skuimg", formData).then(function(res){
            if(res.data.success) {
                $('.sku-img').attr('src', res.data.link);
            }
        }, function(err){
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
        $('.sku-img').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');

    });


    $(".edit-product").on("click", function () {
        var pdInfo = $(this).data("product");

        axios.get("/api/sku/detail/" + pdInfo).then(function(res){
            if(res.data.successful) {
                data = res.data.data;
                console.log(data);
                dataProduct.id = data.id;
                $("#input-sku-name").val(data.name);
                $("#input-sku-spec").val(data.spec);
                $("#input-sku-quantity").val(data.quantity);
                $("#input-sku-price").val(data.price);
                $("#input-sku-mainSku").val(data.mainSku);

                if(data.imageURL != null) {
                    $('.sku-img').attr('src', '/../'+data.imageURL);
                } else {
                    $('.sku-img').attr('src', '/../images/blank_avatar.png');
                }
            } else {
                console.log("Error");
            }
        }, function(err){
            console.log("Error");
        })
    });



    $(".btn-save-product").on("click", function () {
        if($("#input-product-name").val() === "" || $("#input-product-desc").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }

        dataProduct.name = $('#input-product-name').val();
        dataProduct.brandId = $("#input-product-brand").val();
        dataProduct.scentId = $("#input-product-scent").val();
        dataProduct.typeId = $("#input-product-type").val();
        dataProduct.gender = $("#input-product-gender").val();
        dataProduct.description = $('#input-product-desc').val();

        var linkPost = "/api/product/create";
        if (dataProduct.id) {
            linkPost = "/api/product/update/" + dataProduct.id;
        }

        axios.post(linkPost, dataProduct).then(function(res){
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
                'Some error when saving product',
                'error'
            );
        })
    });



});