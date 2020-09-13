$(document).ready(function () {

    $(".switch-status-btn").on("click", function () {
        let userId = $(this).data("user");

        let formData = new FormData();
        let updateStatusLink = "/api/user/switch-status/";

        axios.post(updateStatusLink + userId, formData).then(function (res) {
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
                'Error when updating user status',
                'error'
            );
        }, function (err) {
            console.log("Error");
        })
    })
})