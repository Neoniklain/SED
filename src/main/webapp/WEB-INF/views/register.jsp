<jsp:include page="../partials/head.jsp"/>
<script src="${request.contextPath}/js/account.js"></script>

<div class="header">
    <h1><i class="fa fa-university" aria-hidden="true"></i> КемГУ Кафедра Юнеско</h1>
    <p>Информационный кафедральный портал.</p>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="login-form">
                <div class="account-wall">
                    <h1 class="text-center login-title">Регистрация</h1>
                    <img class="profile-img" src="/img/anon-user.jpg"
                         alt="">
                    <form class="form-signin" id="register-form" action="/Account/register" method="post">
                        <input type="text" class="form-control" placeholder="Имя" name="username" required autofocus>
                        <input type="text" class="form-control" placeholder="Email" name="email" required autofocus>
                        <input type="password" class="form-control" placeholder="Пароль" name="password" required>
                        <input type="password" class="form-control" placeholder="Повторите пароль" name="confirm-password" required>
                        <button class="btn btn-lg btn-login btn-block" type="submit">
                            Зарегистрироваться
                        </button>
                        <span class="clearfix"></span>
                    </form>
                    <!--<button class="btn btn-lg btn-login btn-block" id="send-register-form-button">
                        Зарегистрироваться
                    </button>-->
                </div>
                <a href="/login" class="text-center new-account">Вход</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../partials/footer.jsp"/>