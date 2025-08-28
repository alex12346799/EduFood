
<#import "../layout.ftlh" as main>

<@main.layout>
    <div class="container mt-5">
        <h2 class="mb-4 text-center">Меню</h2>
        <h4 class="text-muted mb-4">${restaurant.name}</h4>

        <#if dishes?? && (dishes?size > 0)>
            <div class="row g-4">
                <#list dishes as dish>
                    <div class="col-md-3">
                        <div class="card shadow-sm h-100 border-0 rounded-4 dish-card">

                            <!-- Фото блюда -->
                            <img src="${dish.imageUrl!'/images/default-dish.jpg'}"
                                 class="card-img-top rounded-top-4"
                                 alt="${dish.name}">

                            <div class="card-body d-flex flex-column">
                                <!-- Название блюда -->
                                <h5 class="card-title">${dish.name}</h5>
                                <!-- Название ресторана -->
                                <p class="text-muted mb-2">${dish.restaurant.name}</p>
                                <!-- Цена -->
                                <p class="mb-3 fw-bold">${dish.price} сом</p>

                                <!-- Форма добавления в корзину -->
                                <form action="/cart/add/${dish.id}" method="post" class="d-flex gap-2 mt-auto">
                                    <#if _csrf??>
                                        <input type="hidden"
                                               name="${(_csrf.parameterName)!'csrf-param-name'}"
                                               value="${(_csrf.token)!'csrf-token'}"/>
                                    </#if>
                                    <input type="number" name="quantity" value="1" min="1" class="form-control w-50">
                                    <button type="submit" class="btn btn-success flex-grow-1">Добавить</button>
                                </form>

                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <p class="text-muted text-center">Блюда не найдены.</p>
        </#if>
    </div>

    <style>
        .dish-card {
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
            overflow: hidden;
        }

        .dish-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.2);
        }

        .dish-card img {
            height: 180px;
            object-fit: cover;
        }

        .card-title {
            font-weight: 600;
            font-size: 1.1rem;
        }

        .btn-success {
            border-radius: 30px;
            padding: 0.4rem 1.2rem;
            font-size: 0.9rem;
        }
    </style>
</@main.layout>
