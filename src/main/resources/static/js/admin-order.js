$(document).ready(function () {

    $(".apply-status-btn").on("click", function () {
        let orderId = this.id.substring(4);
        let orderNewStatus = $("#select-" + orderId).val();
        let orderCurrStatus = $("#status-" + orderId).val();
        if (orderNewStatus != orderCurrStatus) {

            let formData = new FormData();
            formData.append("orderStatus", orderNewStatus);
            let updateStatusLink = "/api/order/update-status/";

            axios.post(updateStatusLink + orderId, formData).then(function (res) {
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
                    'Error when updating order status',
                    'error'
                );
            }, function (err) {
                console.log("Error");
            })
        }
    });
})