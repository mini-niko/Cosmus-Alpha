AOS.init();

const swiper = new Swiper('.swiper', {
    loop: true,
    effect: "fade",
    crossFade: true,
    keyboard: {
        enable: true,
    },

    // If we need pagination
    pagination: {
        el: '.swiper-pagination',
    },

    // Navigation arrows
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    // And if we need scrollbar
    scrollbar: {
        el: '.swiper-scrollbar',
    },
});