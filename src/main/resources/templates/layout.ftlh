<#import "spring.ftl" as spring>
<#macro layout>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>JobSearch</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
                crossorigin="anonymous"></script>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">EduFood</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                    <li class="nav-item">
                        <a class="nav-link" href="/cart">
                            <i class="bi bi-cart"></i>
                            Корзина
                            <#if cartCount?? && cartCount > 0>
                                <span class="badge bg-danger">${cartCount}</span>
                            </#if>
                        </a>
                    </li>

                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/orders/history">Личный кабинет</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/restaurants">Заведение</a>
                    </li>

                </ul>
                <#--                <form class="d-flex" role="search">-->
                <#--                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>-->
                <#--                    <button class="btn btn-outline-success" type="submit">Search</button>-->
                <#--                </form>-->




            <#assign known = SPRING_SECURITY_CONTEXT?? >
            <#if known>
                <#assign
                user = SPRING_SECURITY_CONTEXT.authentication.principal
                name = user.getUsername()
                >
                <#if user??>
                    <a href="/profile/${name}" class="text-light m-2 m-sm-0">
                        Hello, ${name}

                        <#list user.authorities as auth>
                            <span class="text-danger">${auth}</span>
                            <#if auth?has_next>
                                /
                            </#if>
                        </#list>
                    </a>
                    <form action="/logout" method="post">
                        <#if _csrf??>
                            <input
                                    type="hidden"
                                    name="${(_csrf.parameterName)!'csrf-param-name'}"
                                    value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <button type="submit" class="btn btn-outline-info m-2"
                                id="logout">
                            Logout
                        </button>
                    </form>
                </#if>
            <#else>
<#--                <a href="user/login"-->
<#--                   class="btn btn-outline-info m-2">Login</a>-->
<#--                <a href="user/register"-->
<#--                   class="btn btn-outline-info m-2">Register</a>-->

                <a href="/user/login" class="btn btn-outline-info m-2">Login</a>
                <a href="/user/register" class="btn btn-outline-info m-2">Register</a>

            </#if>

        </div>
    </nav>


    <div class="container">
        <#nested>
    </div>
    </body>

</html>

</#macro>