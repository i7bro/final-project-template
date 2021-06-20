let cards = $('.card');
let footer = $('#footer');

cards.hover(function () {
    $(this).css('box-shadow', '2px 2px 5px rgb(141, 141, 141)');
}, function () {
    $(this).css('box-shadow', 'none');
})

footer.hover(function () {
    $(this).css('box-shadow', '2px 2px 5px rgb(141, 141, 141)');
    $(this).css('background-color', '#20c997');
}, function () {
    $(this).css('box-shadow', 'none');
    $(this).css('background-color', '#fafafa');
})

$('.btn-danger').click((e) => {
    let answer = confirm("Are you confirm action?");

    if(!answer) {
        e.preventDefault();
    }
})

function hiddenMessage() {
    $('.alert').attr('hidden', 'hidden');
}

setTimeout(hiddenMessage, 6000);


