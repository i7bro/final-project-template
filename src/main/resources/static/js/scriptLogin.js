let loginBtn = $('#loginPassSubmit');
let registrationBtn = $('#registrationBtn');

registrationBtn.click(function () {
    let req = fetch("/registration", {
        method: 'GET',
        headers: {
            'Sec-Fetch-Dest': 'document'
        }
    });
});
