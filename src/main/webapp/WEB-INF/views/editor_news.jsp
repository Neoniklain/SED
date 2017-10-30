<jsp:include page="../partials/head.jsp"/>

<div class="header">
    <h1><i class="fa fa-university" aria-hidden="true"></i> КемГУ Кафедра Юнеско</h1>
    <p>Информационный кафедральный портал.</p>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="head">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3><i class="fa fa-file-text-o" aria-hidden="true"></i> Редактирование статьи</h3></div>

                    <div class="panel-body edit-news">
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon">Заголовок: </span>
                                    <input type="text" class="form-control" placeholder="Название статьи">
                                </div>
                            </div>
                            <div class="col-xs-6 col-md-3">
                                <div class="input-group">
                                    <span class="input-group-addon">Дата: </span>
                                    <input type="date" class="form-control" id="date" name="date" placeholder="Дата" required>
                                </div>
                            </div>
                            <div class="col-xs-6 col-md-3">
                                <div class="input-group">
                                    <span class="input-group-addon">Автор: </span>
                                    <input type="text" value="Admin" disabled class="form-control">
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">Тэги: </span>
                                    <input type="text" class="form-control" placeholder="Username">
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <textarea class="form-control" rows="10" id="comment"></textarea>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <button class="btn btn-success">Схранить изменения</button>
                                <button class="btn btn-danger">Отмена</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="panel panel-default edit-news-picture">
                <div class="panel-heading"><h3><i class="fa fa-picture-o" aria-hidden="true"></i> Миниатюра</h3></div>
                <label class="btn btn-default btn-block">
                    Указать файл <input type="file" hidden>
                </label>
                <div class="thumbnail">
                    <img src="news.png" alt="Миниатюра">
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../partials/footer.jsp"/>