// Файл данных картинки
var imagedata;

$(function() {

    $('#create-news-button').click(function() {
        var data = FormToJson('#newsForm');
        data['image']=imagedata;
        var dd = JSON.stringify(data);
        sendData(data, "/news/create", 'Новость создана!', 'Новая новость "' + data['header'] + '" была создана.');
    });
});

/**
 * Сохранить выбранную картинку в формате Base64
 * @fileInput Input выбора картинки
 */
function SaveImgAsB64(fileInput) {
    //
    $('#create-news-button').prop("disabled",true);;
    // Загружаем картинку
    var file = document.querySelector('#selectFile').files[0];
    var reader  = new FileReader();
    reader.addEventListener("load", function () {
        preview.src = reader.result;
        imagedata = reader.result;
        }, false);
    if (file) {
        reader.addEventListener("load", function(e) {
            // Отображаем загруженную картинку
            LoadImgFromB64("#preview",imagedata);
            // отображаем имя картинки
            $(".thumbnail-name p").text(file.name);
            //
            $('#create-news-button').prop("disabled",false);
        });
        reader.readAsDataURL(file);
    }
}