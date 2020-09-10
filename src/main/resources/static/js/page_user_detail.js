$(document).ready(function() {

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('.user-avatar').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }


    $("#input-avatar").change(function(e) {
        readURL(this);
        var formData = new FormData();
        formData.append('file', $("#input-avatar")[0].files[0]);
        axios.post("/api/upload/upload-avatar", formData).then(function(res){
            console.log(res.data);
            if(res.data.successful) {
                $("#avatar").val(res.data.link);
                console.log($("#avatar").val());
            }
        }, function(err){

        });
    });


});