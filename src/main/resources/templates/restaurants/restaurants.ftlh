<#import "../layout.ftlh" as main>
<#import "../pages/pages.ftlh" as p>
<@main.layout>
    <div class="container mt-5">
        <h2 class="mb-4 text-center">Наши рестораны</h2>


        <form method="get" class="mb-5 d-flex justify-content-center">
            <input type="text" class="form-control w-50 me-2" name="query"
                   placeholder="Поиск ресторана"
                   value="${query!}">
            <button type="submit" class="btn btn-primary">Поиск</button>
        </form>

        <@p.pager url page/>
        <#if restaurants?? && (restaurants?size > 0)>
            <div class="row g-4">
                <#list restaurants as restaurant>
                    <div class="col-md-3">
                        <div class="card shadow-sm h-100 border-0 rounded-4 restaurant-card">



                            <img src="${restaurant.imageUrl}" alt="Фото ресторана" class="card-img-top">




                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">${restaurant.name}</h5>
                                <p class="card-text text-muted mb-4">${restaurant.address!''}</p>
                                <a href="/restaurant/${restaurant.id}/dishes"
                                   class="btn btn-primary mt-auto">Посмотреть меню</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <p class="text-muted text-center">Рестораны не найдены.</p>
        </#if>
    </div>

    <style>
        .restaurant-card {
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
            overflow: hidden;
        }

        .restaurant-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.2);
        }

        .restaurant-card img {
            height: 180px;
            object-fit: cover;
        }

        .card-title {
            font-weight: 600;
            font-size: 1.1rem;
        }

        .card-text {
            font-size: 0.9rem;
        }

        .btn-primary {
            border-radius: 30px;
            padding: 0.4rem 1.2rem;
            font-size: 0.9rem;
        }
    </style>

</@main.layout>


