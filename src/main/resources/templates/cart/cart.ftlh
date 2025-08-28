<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2>Корзина</h2>

        <#if dishesInCart?? && (dishesInCart?size > 0)>
            <table class="table">
                <thead>
                <tr>
                    <th>Блюдо</th>
                    <th>Заведение</th>
                    <th>Количество</th>
                    <th>Цена</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <#list dishesInCart?keys as dish>
                    <tr>
                        <td>${dish.name}</td>
                        <td>${dish.restaurant.name}</td>
                        <td>${dishesInCart[dish]}</td>
                        <td>${dish.price * dishesInCart[dish]}</td>
                        <td>
                            <form action="/cart/remove/${dish.id}" method="post">
                                <button class="btn btn-danger btn-sm">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>

            <div>
                <strong>Итог:</strong>
                <span>${totalPrice}</span>
            </div>

            <form action="/orders/create" method="post">
                <#if _csrf??>
                    <input
                            type="hidden"
                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/>
                </#if>
                <button class="btn btn-success mt-3">Оформить заказ</button>
            </form>
        <#else>
            <p>Корзина пуста.</p>
        </#if>
    </div>
</@main.layout>
