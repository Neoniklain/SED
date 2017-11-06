/**
 * Для отправки данных
 * @data Данные в формате ассоциативного масива
 * @url Путь куда отправляем
 * @successMEsageHeader Заголовок сообщения при успешной отправке
 * @successMesage Сообщение при успешной отправке
 */
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
            console.log(data);
            showMessage([{severity: 'error', summary: 'Ошибка!', detail: "Подробности в консоли."}]);
        }
    });
}
/**
 * Переводчит все поля формы в ассоциативный массив
 * @name Имя формы (ИД или класс)
 */
function FormToJson(name) {

    var formData = {};
    $(name).find('[name]').each(function() {
        formData[this.name] = this.value;
    })
    return formData;
};
/**
 * Устанавивает картинку в формате base64 a указаную форму
 * @image_container_id ID формы куда установить картинку
 * @data_image Сама картинка в формате JSON
 */
function LoadImgFromB64(image_container_id,data_image)
{
    $(image_container_id).attr('src', data_image);
}