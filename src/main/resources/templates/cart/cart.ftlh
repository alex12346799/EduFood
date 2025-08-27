<h2>Корзина</h2>
<#if cartItems?? && cartItems?size > 0>
    <table class="table">
        <thead>
        <tr>
            <th>Блюдо</th>
            <th>Количество</th>
            <th>Цена</th>
        </tr>
        </thead>
        <tbody>
        <#list cartItems as item>
            <tr>
                <td>${item.dishName}</td>
                <td>${item.quantity}</td>
                <td>${item.price}</td>

            </tr>
        </#list>
        </tbody>
    </table>
    <p>Итого: ${totalPrice}</p>
    <form action="/orders/create" method="post">
        <button type="submit" class="btn btn-primary">Оформить заказ</button>
    </form>
<#else>
    <p>Корзина пуста</p>
</#if>
