$(document).ready(function() {

    var dataProduct = {};


    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-product-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#new-product").on("click", function () {
        dataProduct = {};
        $('#input-product-name').val("");
        $('#input-product-desc').val("");
        $("#input-product-category").val("");
        $("#input-product-price").val("");
        $('.product-main-image').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');

    });


    $(".edit-product").on("click", function () {
        var pdInfo = $(this).data("product");

        axios.get("/api/product/detail/" + pdInfo).then(function(res){
            if(res.data.successful) {
                dataProduct.id = res.data.data.id;
                console.log(res.data.data);
                $("#input-product-name").val(res.data.data.name);
                $("#input-product-desc").val(res.data.data.description);
                $("#input-product-brand").val(res.data.data.brandId);
                $("#input-product-scent").val(res.data.data.scentId);
                $("#input-product-type").val(res.data.data.typeId);
                $("#input-product-gender").val(res.data.data.gender);
                if(res.data.data.mainImageURL != null) {
                    $('.product-img').attr('src', '/../'+res.data.data.mainImageURL);
                }
            }else {
                console.log("Error");
            }
        }, function(err){
            console.log("Error");
        })
    });



    $(".btn-save-product").on("click", function () {
        if($("#input-product-name").val() === "" || $("#input-product-desc").val() === "" || $("#input-product-price").val()==="") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }


        dataProduct.name = $('#input-product-name').val();
        dataProduct.shortDesc = $('#input-product-desc').val();
        dataProduct.categoryId = $("#input-product-category").val();
        dataProduct.mainImage = $('.product-main-image').attr('src');
        dataProduct.price = $("#input-product-price").val();
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