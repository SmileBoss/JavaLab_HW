<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
</head>
<body>
<form name="user" action="/addUser" method="post">
    Firstname
    <input title="firstname" type="text" name="firstname">
    Email
    <input title="email" type="email" name="email">
    Lastname
    <input title="lastname" type="text" name="lastname">
    <input type="submit" value="submit">
</form>
<br>
<a href="/users">Back</a>
</body>
</html>