let cards = $('.card');


cards.hover(function () {
    $(this).css('box-shadow', '2px 2px 5px rgb(141, 141, 141)');
}, function () {
    $(this).css('box-shadow', 'none');
})