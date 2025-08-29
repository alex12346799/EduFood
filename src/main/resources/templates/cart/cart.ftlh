
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
                    <th style="width: 200px;">Количество</th>
                    <th>Цена</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <#list dishesInCart?keys as dish>
                    <#if dish??>
                        <tr>
                            <td>${dish.name}</td>
                            <td>${dish.restaurant.name}</td>
                            <td>
                                <!-- Кнопка уменьшить -->
                                <form action="/cart/add/${dish.id}" method="post" style="display:inline;">
                                    <#if _csrf??>
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                               value="${(_csrf.token)!'csrf-token'}"/>
                                    </#if>
                                    <input type="hidden" name="quantity" value="-1"/>
                                    <button class="btn btn-warning btn-sm">-</button>
                                </form>

                                <span class="mx-2">${dishesInCart[dish]!0}</span>


                                <!-- Кнопка увеличить -->
                                <form action="/cart/add/${dish.id}" method="post" style="display:inline;">
                                    <#if _csrf??>
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                               value="${(_csrf.token)!'csrf-token'}"/>
                                    </#if>
                                    <input type="hidden" name="quantity" value="1"/>
                                    <button class="btn btn-primary btn-sm">+</button>
                                </form>
                            </td>
                            <td>${(dish.price)} сом</td>
                            <td>
                                <form action="/cart/remove/${dish.id}" method="post">
                                    <#if _csrf??>
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                               value="${(_csrf.token)!'csrf-token'}"/>
                                    </#if>
                                    <button class="btn btn-danger btn-sm">Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </#if>
                </#list>
                </tbody>
            </table>

            <div class="mt-3">
                <strong>Итог:</strong>
                <span>${totalPrice?string(",##0.00")} сом</span>
            </div>

            <form action="/orders/create" method="post" class="mt-3">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>
                <button class="btn btn-success">Оформить заказ</button>
            </form>
        <#else>
            <p>Корзина пуста.</p>
        </#if>
    </div>
</@main.layout>
