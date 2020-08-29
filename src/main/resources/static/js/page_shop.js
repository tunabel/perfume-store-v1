//nice-select
$(document).ready(function () {
    $('select').niceSelect();

    //dropdown
    $('.select_option_dropdown').hide();
    $(".select_option_list").click(function () {
        $(this).parent(".select_option").children(".select_option_dropdown").slideToggle('100');
        $(this).find(".right").toggleClass("icon-caret-down, icon-caret-up");
    });
});