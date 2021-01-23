<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update</title>
</head>
<body>
<form name="user" action="/update" method="post">
    Id
    <input title="id" type="number" name="id" value="${user.id}" readonly>
    Firstname
    <input title="firstname" type="text" name="firstname" value="${user.firstname}">
    Email
    <input title="email" type="email" name="email" value="${user.email}">
    Lastname
    <input title="lastname" type="text" name="lastname" value="${user.lastname}">
    <input type="submit" value="submit">
</form>
<br>
<a href="/users">Back</a>
</body>
</html>