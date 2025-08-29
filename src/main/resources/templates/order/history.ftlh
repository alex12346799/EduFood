<#--<#import "../layout.ftlh" as maim>-->
<#--<@maim.layout>-->
<#--    <h2>Личный кабинет</h2>-->
<#--    <p>Добро пожаловать, ${user.name} ${user.surname}!</p>-->

<#--    <h3>История заказов:</h3>-->

<#--    <table class="table table-striped">-->
<#--        <thead>-->
<#--        <tr>-->
<#--            <th>Дата</th>-->
<#--            <th>Сумма</th>-->
<#--            <th>Заведение</th>-->
<#--            <th>Блюда</th>-->
<#--            <th>Детали</th>-->
<#--        </tr>-->
<#--        </thead>-->
<#--        <tbody>-->
<#--        <#list orders as order>-->
<#--            <tr>-->
<#--                <td>${order.createTime}</td>-->
<#--                <td>${order.totalPrice}</td>-->
<#--                <td>${order.restaurantId}</td>-->
<#--                <td>-->
<#--                    <#list order.items as item>-->
<#--                        ${item.id} (x${item.quantity})<br/>-->
<#--                    </#list>-->
<#--                </td>-->
<#--                <td>-->
<#--                    <a href="/orders/${order.id}">Посмотреть</a>-->
<#--                </td>-->
<#--            </tr>-->
<#--        </#list>-->
<#--        </tbody>-->
<#--    </table>-->

<#--</@maim.layout>-->

<#import "../layout.ftlh" as maim>
<@maim.layout>
    <h2>Личный кабинет</h2>
    <p>Добро пожаловать, ${user.name} ${user.surname}!</p>

    <h3>История заказов:</h3>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Дата</th>
            <th>Сумма</th>
            <th>Заведение</th>
            <th>Блюда</th>
            <th>Детали</th>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr>
                <td>${order.createTime}</td>
                <td>${order.totalPrice}</td>
                <td>${order.restaurantName}</td>
                <td>
                    <#list order.items as item>
                        ${item.dishName} (x${item.quantity})<br/>
                    </#list>
                </td>

            </tr>
        </#list>
        </tbody>
    </table>
</@maim.layout>
