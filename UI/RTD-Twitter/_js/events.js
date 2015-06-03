


$(function() {

//    $('.slider-content').on('click', function() {
//        
//        prepareDrawChart($(this).attr('data-id'));
//
//        $("html, body").animate({ scrollTop: $(window).height() }, 600);
//    });

    $('.home').on('click', function() {

        $(window.location).attr('href', './index.html');
    });

    $('.temp').on('click', function() {

        $(window.location).attr('href', './temp.html');
    });
    
    $('#resetScreen').on('click', function() {

        resetScreen();
    });

});