$(document).ready(function() {
    //summernote HTML text editor
    $('#input-short-desc').summernote();
    $('#input-full-desc').summernote();



    $('.tag-label').on('click', function() {
        if ( $(this).prev().is(':checked')) {
            $(this).prev().attr('checked', false);
        } else {
            $(this).prev().attr('checked', true);
        }
    })

});