// ---------login input ------------
let login = $('#inputLogin'),
    loginJS = document.getElementById('inputLogin'),
    classes = login.attr('class');

loginJS.addEventListener('input', () => {
    isValid(login, classes, 3);
})

let fNameJS = document.getElementById('inputFirstName'),
    fName = $('#inputFirstName'),
    fNameClasses = fName.attr('class'),
    lNameJS = document.getElementById('inputLastName'),
    lName = $('#inputLastName'),
    lNameClasses = lName.attr('class');

fNameJS.addEventListener('input', () => {
    isValid(fName, fNameClasses, 1);
})

lNameJS.addEventListener('input', () => {
    isValid(lName, lNameClasses, 1);
})

let phoneJS = document.getElementById('inputPhone');
let phone = $('#inputPhone');
let phoneClasses = phone.attr('class');
let currentLengthPhone = 0;

phoneJS.addEventListener('input', () => {
    let phoneVal = phone.val();

    if (currentLengthPhone < phoneVal.length) {
        if (phoneVal.length == 1 && !['+', '7', '8', '9'].includes(phoneVal)) {
            phone.val('');
            phoneVal = '';
        } else if (['7', '+7', '8'].includes(phoneVal)) {
            phone.val('+7 ');
        } else if (phoneVal == '9') {
            phone.val('+7 9')
        } else if (phoneVal.length == 6) {
            phone.val(phoneVal + ' ');
        } else if (phoneVal.length == 10) {
            phone.val(phoneVal + ' ')
        } else if (phoneVal.length == 13) {
            phone.val(phoneVal + ' ')
        }
    }

    if (phoneVal.length > 16) {
        phone.val(phoneVal.substring(0, phoneVal.length - 1));
    }

    isValid(phone, phoneClasses, 16);
    currentLengthPhone = phoneVal.length;
})

function hiddenMessage() {
    $('.pop-up').attr('hidden', 'hidden');
}

setTimeout(hiddenMessage, 6000);


let email = $('#inputEmail');
let emailClasses = email.attr('class');
let infoRedirect = $('#infoRedirect');

email.blur(() => {
    let nextElement = $('#inputEmail + div');
    let classesCurrent = email.attr('class');

    if (!validateEmail(email.val())) {
        if (!classesCurrent.includes('is-invalid')) {
            email.attr('class', emailClasses + ' is-invalid');
            nextElement.remove();
            email.after('<div class="invalid-feedback">Please set valid email</div>');
            ;
        }
    } else {
        if (!classesCurrent.includes('is-valid')) {
            email.attr('class', emailClasses + ' is-valid');
            nextElement.remove();

        }
    }
})

let button = $('#submitBtn');

let dataBtn = $('#dataBtn');

dataBtn.click(() => {
    login.removeAttr('disabled');
    email.removeAttr('disabled');
    fName.removeAttr('disabled');
    lName.removeAttr('disabled');
    phone.removeAttr('disabled');
    infoRedirect.removeAttr('hidden');
    button.removeAttr('disabled');
})


function isValid(element, classes, length) {
    let id = element[0].id;
    let nextElement = $('#' + id + '+' + 'div');
    let classesCurrent = element.attr('class');

    if (element.val().length < length) {
        if (!classesCurrent.includes('is-invalid')) {
            element.attr('class', classes + ' is-invalid');
            nextElement.remove();
            element.after('<div class="invalid-feedback">Please enter more ' + length + ' chars</div>');

        }
    } else {
        if (!classesCurrent.includes('is-valid')) {
            element.attr('class', classes + ' is-valid');

        }
    }
}

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function isClassesInclude(array) {
    let i = 0;
    array.forEach(element => {
        if (element.attr('class').includes('is-valid')) {
            i++;
        }
    });

    return i === array.length;
}

function isIncludeValid(element) {
    return element.includes('is-valid');
}
