// Для отправки данных на формы
function sendData(data, url, successMEsageHeader, successMesage) {
    // Для отображения спец опопвещений
    $('#short_message').puigrowl();
    function showMessage (msg) {
        $('#short_message').puigrowl('show', msg);
    }
    $.ajax({
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8',
        type: 'POST',
        data: JSON.stringify(data),
        success: function (data) {
            if (successMEsageHeader !== undefined && successMesage !== undefined) {
                showMessage([{
                    severity: 'info',
                    summary: successMEsageHeader,
                    detail: successMesage
                }]);
            }
        },
        error: function (data) {
            showMessage([{severity: 'info', summary: 'Новость не создана!', detail: data}]);
        }
    });
}