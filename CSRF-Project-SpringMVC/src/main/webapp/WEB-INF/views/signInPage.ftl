<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SignIn</title>
</head>
<body>
<form method="post">
    <input name="email">
    <input type="hidden" value="${_csrf_token}" name="_csrf_token">
    <input type="password" name="password">
    <input type="submit" value="SignIn">
</form>
</body>
</html>