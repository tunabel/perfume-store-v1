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
        $("#input-product-brand").val("");
        $("#input-product-scent").val("");
        $("#input-product-type").val("");
        $("#input-product-gender").val("");
        $('.product-img').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');

    });


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