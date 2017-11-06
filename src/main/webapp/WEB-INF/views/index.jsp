<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../partials/head.jsp"/>

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

                        <c:forEach items="${News}" var="n">

                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="thumbnail">
                                    <img src="${n.image}" alt="Первая запись">
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-8">
                                <div class="news-header">
                                    <h2><a href="#">${n.header}</a></h2>
                                    <p><i class="fa fa-calendar" aria-hidden="true"></i>
                                        <fmt:formatDate value="${n.date}" pattern="dd.MM.yyyy" /></p>
                                    <hr>
                                </div>
                                <div class="news-content">
                                    <p>${n.content}</p>
                                </div>
                            </div>
                        </div>
                        <hr>
                        </c:forEach >
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

