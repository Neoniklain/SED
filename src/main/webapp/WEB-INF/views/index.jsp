<%@ include file="../partials/taglibs.jsp" %>
<jsp:include page="../partials/head.jsp"/>

<nav class="navbar navbar-default navbar-inverse" role="navigation">
    <div class="container container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <i class="fa fa-university" aria-hidden="true"></i>
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#"><i class="fa fa-home" aria-hidden="true"></i> Главная</a></li>
                <li><a href="#"><i class="fa fa-address-card-o" aria-hidden="true"></i> Личный кабинет</a></li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <button type="submit" class="btn btn-default"><i class="fa fa-sign-out" aria-hidden="true"></i> Выход</button>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="header">
    <h1><i class="fa fa-university" aria-hidden="true"></i> КемГУ Кафедра Юнеско</h1>
    <p>Информационный кафедральный портал.</p>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="head">
                <div class="panel panel-default panel-inverse">
                    <div class="panel-heading"><h3><i class="fa fa-newspaper-o" aria-hidden="true"></i> Новости</h3></div>

                    <div class="panel-body news">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <a href="#" class="thumbnail">
                                    <img src="/img/news.png" alt="Первая запись">
                                </a>
                            </div>
                            <div class="col-xs-12 col-md-8">
                                <div class="news-header">
                                    <h2><a href="#">Заголовок первой записи</a></h2>
                                    <p><i class="fa fa-calendar" aria-hidden="true"></i> 30.10.17</p>
                                    <hr>
                                </div>
                                <div class="news-content">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nesciunt eius voluptatem tempora, et veritatis temporibus molestiae ea consectetur quasi dolore fugiat rem cumque at deserunt, minima officiis ipsum beatae labore! Quos ab sint ad inventore harum quas sunt tempora fugit pariatur iusto, similique deleniti iste <a>illo id cum ipsum quasi exercitationem odio</a> itaque nostrum, placeat dolorem architecto. Voluptatibus, consequuntur, beatae! Suscipit totam, illum aliquid, corrupti excepturi ut similique. Hic nesciunt vero corporis ducimus nihil quidem! Illum dolore quas dignissimos at minima sit blanditiis! Veniam, expedita! Magni cum eligendi, consequuntur consequatur. Animi incidunt sint dolorem nisi adipisci nesciunt veritatis consequuntur, saepe repudiandae sequi, similique maiores placeat! Illum itaque repellendus mollitia ullam cum officiis sunt provident asperiores? Dolor molestias sed error exercitationem.</p>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="head">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3><i class="fa fa-th" aria-hidden="true"></i> Разное</h3></div>
                    <ul class="list-group">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Morbi leo risus</li>
                        <li class="list-group-item">Porta ac consectetur ac</li>
                        <li class="list-group-item">Vestibulum at eros</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../partials/footer.jsp"/>
