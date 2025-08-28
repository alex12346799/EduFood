<#import "../layout.ftlh" as main>

<@main.layout>
    <h2 class="text-center my-3">Список блюд</h2>

    <table class="table table-striped table-bordered text-center">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Цена</th>
            <th>Ресторан</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <#list dishes as dish>
            <tr>

                <td>${dish.id}</td>
                <td>${dish.name}</td>
                <#--                <td>${dish.description}</td>-->
                <td>${dish.price} сом</td>
                <td>${dish.restaurant.name}</td>
                <td>
                    <form action="/cart/add/${dish.id}" method="post" class="d-inline">
                        <#if _csrf??>
                            <input
                                    type="hidden"
                                    name="${(_csrf.parameterName)!'csrf-param-name'}"
                                    value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <input type="number" name="quantity" value="1" min="1" class="form-control d-inline w-25">
                        <button type="submit" class="btn btn-success btn-sm">Добавить в корзину</button>
                    </form>

                    <a href="/dishes/edit/${dish.id}" class="btn btn-sm btn-primary">✏ Редактировать</a>
                    <a href="/dishes/delete/${dish.id}" class="btn btn-sm btn-danger">🗑 Удалить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</@main.layout>
