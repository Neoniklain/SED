$(document).ready(function () {
    $("#send-register-form-button").click(function () {
        var data = FormToJson('#register-form');
        sendData(data, "/Account/register", 'Новый пользователь добавлен!', 'Новый пользователь "' + data.name + '" зарегистрирован.');
    })
})
