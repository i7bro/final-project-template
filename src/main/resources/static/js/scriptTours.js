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

$('.btn-danger').click(function(e) {
    let answer = confirm("Are you confirm action?");

    if(!answer) {
        e.preventDefault();
    } else {
        $.ajax({
            url: $(this).parent().attr('action'),
            method: 'POST',
            type: 'POST'
        })
        let modal = $(this).parents().eq(4);
        modal.prev().children().attr('disabled', 'disabled');
        modal.prev().prev().attr('disabled', 'disabled');
        modal.parent().parent().css('opacity', 0.2);
        modal.parent().parent().css('box-shadow', 'none');
        modal.remove();
        $('.modal-backdrop').remove();
    }
})

function hiddenMessage() {
    $('.pop-up').attr('hidden', 'hidden');
    $('.pop-up-error').attr('hidden', 'hidden');
}

setTimeout(hiddenMessage, 6000);


