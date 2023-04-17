<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Creation</title>
</head>
<body>
<header> <h1> product creation</h1></header>
<header>
    <h1>Product Creation</h1>
</header>
<main>
    <form action="SaveProduct" method="post">
        <div>
            <label for="nameProduct">Product Name : </label>
            <input type="text" id="nameProduct" name="nameProduct">
        </div>
        <div>
            <label for="priceProduct">Price : </label>
            <input type="text" id="priceProduct" name="priceProduct">
        </div>
        <div>
            <label for="dateCreate">Creation Date : </label>
            <input type="date" id="dateCreate" name="date" value="${now}">
        </div>
        <div>
            <input type="submit" value="add">
        </div>
    </form>
</main>
<footer>
    <a href="ProductsList">Products List</a>
</footer>
</body>
</html>
