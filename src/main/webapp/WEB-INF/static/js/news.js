$(function() {

    $('#create-news-button').click(function() {
        var data = {
            "header" : $('#header').val(),
            "content" : $('#content').val(),
            "author": $('#author').val(),
            "tags": $('#tags').val(),
            "date": $('#date').val()};
        sendData(data, "/news/create", 'Новость создана!', 'Новая новость "' + data.header + '" была создана.');
    });

});
