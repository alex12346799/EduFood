<div class="container mt-4">
    <h2 class="mb-3">Рестораны</h2>

    <!-- Поиск -->
    <form method="get" class="mb-3 d-flex">
        <input type="text" class="form-control me-2" name="query"
               placeholder="Поиск ресторана"
               value="${query!}">
        <button type="submit" class="btn btn-primary">Поиск</button>
    </form>

    <!-- Список ресторанов -->
    <div class="list-group">
        <#if restaurants?size > 0>
            <#list restaurants as restaurant>
                <a href="/restaurant/${restaurant.id}/dishes"
                   class="list-group-item list-group-item-action">
                    <h5 class="mb-1">${restaurant.name}</h5>
                    <p class="mb-1 text-muted">${restaurant.description!''}</p>
                </a>
            </#list>
        <#else>
            <p class="text-muted">Рестораны не найдены.</p>
        </#if>
    </div>
</div>