<#ftl encoding="UTF-8"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Form</title>
</head>
<body>
<form action="${form.action}" method="${form.action}">
    <#list inputs as listInput>
        <input type="${listInput.type}" name="${listInput.name}" placeholder="${listInput.placeholder}">
    </#list>
</form>
</body>
</html>