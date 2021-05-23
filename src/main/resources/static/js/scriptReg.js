// ---------login input ------------
let login = $('#inputLogin'),
    loginJS = document.getElementById('inputLogin'),
    classes = login.attr('class');

loginJS.addEventListener('input', () => {
    isValid(login, classes, 1);
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


let passwordJS1 = document.getElementById('inputPassword1');
let passwordJS2 = document.getElementById('inputPassword2');
let password1 = $('#inputPassword1');
let password2 = $('#inputPassword2');
let password1Classes = password1.attr('class');
let password2Classes = password2.attr('class');

passwordJS1.addEventListener('input', () => {
    isValid(password1, password1Classes, 8);
    if (password1.val().length < 8) {
        password2.attr('disabled', 'disabled');
    } else {
        password2.removeAttr('disabled');
    }
})

passwordJS2.addEventListener('input', () => {
    let nextElement = $('#inputPassword2 + div');
    let classesCurrent = password2.attr('class');

    if (password2.val() != password1.val()) {
        if (!classesCurrent.includes('is-innvalid')) {
            password2.attr('class', password2Classes + ' is-innvalid');
            nextElement.remove();
            password2.after('<div class="invalid-feedback">Passwords is not same</div>');

        }
    } else {
        if (!classesCurrent.includes('is-valid')) {
            password2.attr('class', password2Classes + ' is-valid');
            nextElement.remove();

        }
    }
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




let email = $('#inputEmail');
let emailClasses = email.attr('class');

email.blur(() => {
    let nextElement = $('#inputEmail + div');
    let classesCurrent = email.attr('class');

    if (!validateEmail(email.val())) {
        if (!classesCurrent.includes('is-innvalid')) {
            email.attr('class', emailClasses + ' is-innvalid');
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
let buttonCl = button.attr('class');
let buttonRtrn = $('#returnBtn');

button.click((e) => {

    let loginCl = login.attr('class');
    let emailCl = email.attr('class');
    let fNameCl = fName.attr('class');
    let lNameCl = lName.attr('class');
    let pass1Cl = password1.attr('class');
    let pass2Cl = password2.attr('class');
    let phoneCl = phone.attr('class');

    let classCurrent = button.attr('class');

    if (!isIncludeValid(loginCl) ||
        !isIncludeValid(emailCl) ||
        !isIncludeValid(fNameCl) ||
        !isIncludeValid(lNameCl) ||
        !isIncludeValid(pass1Cl) ||
        !isIncludeValid(pass2Cl) ||
        !isIncludeValid(phoneCl)) {

        e.preventDefault();
        if (!classCurrent.includes('is-innvalid')) {
            button.attr('class', buttonCl + ' is-innvalid');
            buttonRtrn.after('<div style="color: red; font-sixe: 5px; margin-top: 4px;">Please check form</div>');
        }
    }
})

buttonRtrn.click(() => {
    window.location.href = '/';
})


function isValid(element, classes, length) {
    let id = element[0].id;
    let nextElement = $('#' + id + '+' + 'div');
    let classesCurrent = element.attr('class');

    if (element.val().length < length) {
        if (!classesCurrent.includes('is-innvalid')) {
            element.attr('class', classes + ' is-innvalid');
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

    return i == array.length;
}

function isIncludeValid(element) {
    return element.includes('is-valid');
}
