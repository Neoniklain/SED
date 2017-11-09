$(document).ready(function () {
    $("#send-register-form-button").click(function () {
        var data = $('#register-form').FormToJSON();
        sendData(data, "/Account/register", 'Новый пользователь добавлен!', 'Новый пользователь "' + data.name + '" зарегистрирован.');
    })
})
