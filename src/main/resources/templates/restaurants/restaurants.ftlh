<#--<div class="container mt-4">-->
<#--    <h2 class="mb-3">Рестораны</h2>-->

<#--    <!-- Поиск &ndash;&gt;-->
<#--    <form method="get" class="mb-3 d-flex">-->
<#--        <input type="text" class="form-control me-2" name="query"-->
<#--               placeholder="Поиск ресторана"-->
<#--               value="${query!}">-->
<#--        <button type="submit" class="btn btn-primary">Поиск</button>-->
<#--    </form>-->

<#--    <!-- Список ресторанов &ndash;&gt;-->
<#--    <div class="list-group">-->
<#--        <#if restaurants??&& (restaurants?size > 0)>-->
<#--            <#list restaurants as restaurant>-->
<#--                <a href="/restaurant/${restaurant.id}/dishes"-->
<#--                   class="list-group-item list-group-item-action">-->
<#--                    <h5 class="mb-1">${restaurant.name}</h5>-->
<#--                    <p class="mb-1 text-muted">${restaurant.address!''}</p>-->
<#--                </a>-->
<#--            </#list>-->
<#--        <#else>-->
<#--            <p class="text-muted">Рестораны не найдены.</p>-->
<#--        </#if>-->
<#--    </div>-->
<#--</div>-->




<div class="container mt-5">
    <h2 class="mb-4 text-center">Наши рестораны</h2>

    <!-- Поиск -->
    <form method="get" class="mb-4 d-flex justify-content-center">
        <input type="text" class="form-control w-50 me-2" name="query"
               placeholder="Поиск ресторана"
               value="${query!}">
        <button type="submit" class="btn btn-primary">Поиск</button>
    </form>

    <!-- Карточки ресторанов -->
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#if restaurants?? && (restaurants?size > 0)>
            <#list restaurants as restaurant>
                <div class="col">
                    <div class="card h-100 shadow-sm">
                        <img src="${restaurant.image!'/images/default-restaurant.jpg'}"
                             class="card-img-top" alt="${restaurant.name}">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${restaurant.name}</h5>
                            <p class="card-text text-muted mb-2">${restaurant.address!''}</p>
                            <a href="/restaurant/${restaurant.id}/dishes"
                               class="btn btn-outline-primary mt-auto">Посмотреть меню</a>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="text-muted text-center">Рестораны не найдены.</p>
        </#if>
    </div>
</div>
