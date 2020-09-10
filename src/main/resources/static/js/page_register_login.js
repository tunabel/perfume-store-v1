$(document).ready(function () {

    $('#submitBtn').attr('disabled','disabled');

    $('#agree_check').click(function () {

        if ($(this).is(':checked'))
        {
            $('#submitBtn').removeAttr('disabled');
        }
    });
});