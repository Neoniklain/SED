<jsp:include page="../partials/head.jsp"/>

<div class="container container-block">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="user-info">
                        <div class="user-img">
                            <img src="/img/anon-user.jpg" alt="">
                        </div>
                        <div class="user-desc">
                            <p class="user-desc-name">Семенов Семен</p>
                            <p class="user-desc-role">Администратор</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container container-block">
    <div class="row">
        <div class="col-md-3">
            <div class="head">
                <div class="panel panel-default panel-inverse">
                    <div class="panel-heading">
                        <h3><i class="fa fa-bars" aria-hidden="true"></i> Меню</h3>
                    </div>
                    <div class="list-group">
                        <a href="#" class="list-group-item">Управление аккаунтом</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="head">
                <div class="panel panel-default panel-inverse">
                    <div class="panel-heading">
                        <h3> Стриница Управление аккаунтом</h3>
                    </div>
                    <div class="panel-body">
                        <form action="/news/editor">
                            <button type="submit" class="btn btn-default btn-sm">Создать статью</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../partials/footer.jsp"/>