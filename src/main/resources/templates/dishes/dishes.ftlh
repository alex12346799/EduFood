<#import "../layout.ftlh" as main>

<@main.layout>
    <h2 class="text-center my-3">Список блюд</h2>

    <table class="table table-striped table-bordered text-center">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Название</th>
<#--            <th>Описание</th>-->
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
                    <a href="/dishes/edit/${dish.id}" class="btn btn-sm btn-primary">✏ Редактировать</a>
                    <a href="/dishes/delete/${dish.id}" class="btn btn-sm btn-danger">🗑 Удалить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <a href="/dishes/add" class="btn btn-success mt-3">➕ Добавить блюдо</a>
</@main.layout>
