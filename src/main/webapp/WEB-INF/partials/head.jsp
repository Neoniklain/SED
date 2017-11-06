<%@ include file="../partials/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Unesco</title>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
    <!--  Библиотеки -->
        <!--  jQuerry -->
        <script src="${request.contextPath}/libs/JQuery/jquery-3.2.1.min.js"></script>
        <!--  jQuerryUi -->
        <script src="${request.contextPath}/libs/jquery-ui/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="${request.contextPath}/libs/jquery-ui/jquery-ui.min.css">
        <link rel="stylesheet" href="${request.contextPath}/libs/jquery-ui/jquery-ui.structure.min.css">
        <link rel="stylesheet" href="${request.contextPath}/libs/jquery-ui/jquery-ui.theme.min.css">
        <!--  Prime UI -->
        <script src="${request.contextPath}/libs/PrimeUi/primeui.min.js"></script>
        <link rel="stylesheet" href="${request.contextPath}/libs/PrimeUi/primeui.min.css">
        <link rel="stylesheet" href="${request.contextPath}/libs/PrimeUi/primeui-all.min.css">
        <link rel="stylesheet" href="${request.contextPath}/libs/PrimeUi/primeui-ng-all.min.css">
        <!--  Bootstrap -->
        <link rel="stylesheet" href="${request.contextPath}/libs/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${request.contextPath}/libs/bootstrap/css/bootstrap-responsive.css">
        <link rel="stylesheet" href="${request.contextPath}/libs/bootstrap/css/bootstrap-theme.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="${request.contextPath}/libs/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--  Статичные файлы -->
    <link href="${request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
    <script src="${request.contextPath}/js/functions.js"></script>
    <script src="${request.contextPath}/js/main.js"></script>
</head>
<body>
<div id="short_message"></div>
<jsp:include page="../partials/navigation.jsp"/>