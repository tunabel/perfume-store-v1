AOS.init({
    duration: 800,
    easing: 'slide'
});

(function ($) {

    "use strict";

    var isMobile = {
        Android: function () {
            return navigator.userAgent.match(/Android/i);
        },
        BlackBerry: function () {
            return navigator.userAgent.match(/BlackBerry/i);
        },
        iOS: function () {
            return navigator.userAgent.match(/iPhone|iPad|iPod/i);
        },
        Opera: function () {
            return navigator.userAgent.match(/Opera Mini/i);
        },
        Windows: function () {
            return navigator.userAgent.match(/IEMobile/i);
        },
        any: function () {
            return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
        }
    };


    $(window).stellar({
        responsive: true,
        parallaxBackgrounds: true,
        parallaxElements: true,
        horizontalScrolling: false,
        hideDistantElements: false,
        scrollProperty: 'scroll'
    });


    var fullHeight = function () {

        $('.js-fullheight').css('height', $(window).height());
        $(window).resize(function () {
            $('.js-fullheight').css('height', $(window).height());
        });

    };
    fullHeight();

    // loader
    var loader = function () {
        setTimeout(function () {
            if ($('#ftco-loader').length > 0) {
                $('#ftco-loader').removeClass('show');
            }
        }, 1);
    };
    loader();

    // Scrollax
    $.Scrollax();

    var carousel = function () {

        $('.product-slider').owlCarousel({
            autoplay: true,
            loop: true,
            items: 1,
            margin: 30,
            stagePadding: 0,
            nav: false,
            dots: true,
            navText: ['<span class="ion-ios-arrow-back">', '<span class="ion-ios-arrow-forward">'],
            responsive: {
                0: {
                    items: 1
                },
                600: {
                    items: 2
                },
                1000: {
                    items: 3
                }
            }
        });
    };
    carousel();

    $('nav .dropdown').hover(function () {
        var $this = $(this);
        // 	 timer;
        // clearTimeout(timer);
        $this.addClass('show');
        $this.find('> a').attr('aria-expanded', true);
        // $this.find('.dropdown-menu').addClass('animated-fast fadeInUp show');
        $this.find('.dropdown-menu').addClass('show');
    }, function () {
        var $this = $(this);
        // timer;
        // timer = setTimeout(function(){
        $this.removeClass('show');
        $this.find('> a').attr('aria-expanded', false);
        // $this.find('.dropdown-menu').removeClass('animated-fast fadeInUp show');
        $this.find('.dropdown-menu').removeClass('show');
        // }, 100);
    });


    $('#dropdown04').on('show.bs.dropdown', function () {
        console.log('show');
    });

    // scroll
    var scrollWindow = function () {
        $(window).scroll(function () {
            var $w = $(this),
                st = $w.scrollTop(),
                navbar = $('.ftco_navbar'),
                sd = $('.js-scroll-wrap');

            if (st > 150) {
                if (!navbar.hasClass('scrolled')) {
                    navbar.addClass('scrolled');
                }
            }
            if (st < 150) {
                if (navbar.hasClass('scrolled')) {
                    navbar.removeClass('scrolled sleep');
                }
            }
            if (st > 350) {
                if (!navbar.hasClass('awake')) {
                    navbar.addClass('awake');
                }

                if (sd.length > 0) {
                    sd.addClass('sleep');
                }
            }
            if (st < 350) {
                if (navbar.hasClass('awake')) {
                    navbar.removeClass('awake');
                    navbar.addClass('sleep');
                }
                if (sd.length > 0) {
                    sd.removeClass('sleep');
                }
            }
        });
    };
    scrollWindow();


    var counter = function () {

        $('#section-counter').waypoint(function (direction) {

            if (direction === 'down' && !$(this.element).hasClass('ftco-animated')) {

                var comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',')
                $('.number').each(function () {
                    var $this = $(this),
                        num = $this.data('number');
                    console.log(num);
                    $this.animateNumber(
                        {
                            number: num,
                            numberStep: comma_separator_number_step
                        }, 7000
                    );
                });

            }

        }, {offset: '95%'});

    }
    counter();

    var contentWayPoint = function () {
        var i = 0;
        $('.ftco-animate').waypoint(function (direction) {

            if (direction === 'down' && !$(this.element).hasClass('ftco-animated')) {
                i++;

                $(this.element).addClass('item-animate');
                setTimeout(function () {

                    $('body .ftco-animate.item-animate').each(function (k) {
                        var el = $(this);
                        setTimeout(function () {
                            var effect = el.data('animate-effect');
                            if (effect === 'fadeIn') {
                                el.addClass('fadeIn ftco-animated');
                            } else if (effect === 'fadeInLeft') {
                                el.addClass('fadeInLeft ftco-animated');
                            } else if (effect === 'fadeInRight') {
                                el.addClass('fadeInRight ftco-animated');
                            } else {
                                el.addClass('fadeInUp ftco-animated');
                            }
                            el.removeClass('item-animate');
                        }, k * 50, 'easeInOutExpo');
                    });

                }, 100);
            }

        }, {offset: '95%'});
    };
    contentWayPoint();


    // navigation
    var OnePageNav = function () {
        $(".smoothscroll[href^='#'], #ftco-nav ul li a[href^='#']").on('click', function (e) {
            e.preventDefault();

            var hash = this.hash,
                navToggler = $('.navbar-toggler');
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 700, 'easeInOutExpo', function () {
                window.location.hash = hash;
            });


            if (navToggler.is(':visible')) {
                navToggler.click();
            }
        });
        $('body').on('activate.bs.scrollspy', function () {
            console.log('nice');
        })
    };
    OnePageNav();



    // if ($('.new_arrival_iner').length > 0) {
    //     var containerEl = document.querySelector('.new_arrival_iner');
    //     var mixer = mixitup(containerEl);
    // }
    //
    // $('.controls').on('click', function () {
    //     $(this).addClass('active').siblings().removeClass('active');
    // });

    // //nice-scroll
    // $(".nice-scroll").niceScroll({
    //     cursorborder: "",
    //     cursorcolor: "#dddddd",
    //     boxzoom: false,
    //     cursorwidth: 5,
    //     background: 'rgba(0, 0, 0, 0.2)',
    //     cursorborderradius: 50,
    //     horizrailenabled: fa / lse
    // });
})(jQuery);


$(document).ready(function () {
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    var delete_cookie = function (name) {
        document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    };

    function getCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    }

    function checkCookie() {
        var user = getCookie("username");
        if (user != "") {
            alert("Welcome again " + user);
        } else {
            user = prompt("Please enter your name:", "");
            if (user != "" && user != null) {
                setCookie("username", user, 365);
            }
        }
    }

    //ADD TO CART BTN IN HOME & SHOP
    $(".add-to-cart").on("click", function () {
        var dataCart = {};
        var skuId = $(this).data("sku");
        dataCart.qty = 1;
        dataCart.skuId = skuId;
        dataCart.guid = getCookie("guid");

        var linkPost = "/api/cart-sku/add";

        axios.post(linkPost, dataCart).then(function (res) {
            console.log(res.data);
            if (res.data.successful) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function () {
                    // location.reload();
                    $("#cart-count").load(window.location.href + " #cart-count");
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function (err) {
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });

    //ADD TO CART BTN IN SKU DETAIL
    $(".add-to-cart-sku").on("click", function () {
        var dataCart = {};
        var skuId = $(this).data("sku");
        dataCart.qty = document.getElementById('quantity').value;
        dataCart.skuId = skuId;
        dataCart.guid = getCookie("guid");
        console.log(dataCart);

        var linkPost = "/api/cart-sku/add";

        axios.post(linkPost, dataCart).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function () {
                    $("#cart-count").load(window.location.href + " #cart-count");
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function (err) {
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });

    //UPDATE CART IN CART PAGE
    $(".update-cart-btn").on("click", function () {

        var skuList = [];
        var count = 0;
        var itemList = document.getElementsByClassName("cart-sku-row");

        Array.prototype.forEach.call(itemList, function(el) {
            let dataCart = {};
            dataCart.qty = el.firstElementChild.value;
            dataCart.id = el.lastElementChild.innerText;
            dataCart.guid = getCookie("guid");
            skuList[count] = dataCart;
            count++;
        })

        var linkPost = "/api/cart-sku/update";

        axios.post(linkPost, skuList).then(function (res) {
            if (res.data.successful) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function () {
                    $("#cart-count").load(window.location.href + " #cart-count");
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function (err) {
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });

});