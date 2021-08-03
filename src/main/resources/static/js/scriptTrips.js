
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
        let modal = $(this).parents().eq(5);
        modal.parent().css('text-decoration', 'line-through')
        modal.remove();
        $('.modal-backdrop').remove();
    }
})

let footer = $('#footer');

footer.hover(function () {
    $(this).css('box-shadow', '2px 2px 5px rgb(141, 141, 141)');
    $(this).css('background-color', '#20c997');
}, function () {
    $(this).css('box-shadow', 'none');
    $(this).css('background-color', '#fafafa');
})


