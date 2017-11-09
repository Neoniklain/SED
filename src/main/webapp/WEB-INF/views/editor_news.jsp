<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../partials/head.jsp"/>
<script src="${request.contextPath}/js/news.js"></script>

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
                            <form:form id="newsForm" commandName="News">
                            <div class="col-xs-12 col-md-12">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon">Заголовок: </span>
                                    <form:input path="header" type="text" class="form-control" id="header" name="header" placeholder="Название статьи" />
                                </div>
                            </div>
                            <div class="col-xs-6 col-md-3">
                                <div class="input-group">
                                    <span class="input-group-addon">Дата: </span>
                                    <form:input path="date" class="form-control" type="text" readonly="true" name="date"/>

                                </div>
                            </div>
                            <div class="col-xs-6 col-md-3">
                                <div class="input-group">
                                    <span class="input-group-addon">Автор: </span>
                                    <form:input path="author" type="text" readonly="true" value="Admin" class="form-control" />
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">Тэги: </span>
                                    <form:input path="tags" type="text" class="form-control" placeholder="tags, anytags..." />
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <form:textarea name="content" class="form-control" rows="10" id="content" path="content"></form:textarea>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <button type="button" id="create-news-button" class="btn btn-success">Схранить изменения</button>
                                <button type="button" class="btn btn-danger">Отмена</button>
                            </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="panel panel-default edit-news-picture">
                <div class="panel-heading"><h3><i class="fa fa-picture-o" aria-hidden="true"></i> Миниатюра</h3></div>
                <label class="btn btn-default btn-block">
                    Указать файл <input id="selectFile" accept="image/jpeg,image/png,image/gif" type="file" multiple onchange="SaveImgAsB64(this)" hidden>
                </label>
                <div class="thumbnail">
                    <img id="preview" src="${request.contextPath}/img/news.png" alt="Миниатюра">
                </div>
                <div class="thumbnail-name">
                    <p></p>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../partials/footer.jsp"/>