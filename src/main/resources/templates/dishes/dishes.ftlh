<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Блюда ресторана</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-3">

<h2>Блюда ресторана: ${restaurant.name}</h2>

<div class="list-group">
    <#list dishes as dish>
        <div class="list-group-item">
            <strong>${dish.name}</strong> — ${dish.price} сом
        </div>
    </#list>
</div>

<a href="/restaurants" class="btn btn-secondary mt-3">Назад к ресторанам</a>

</body>
</html>
