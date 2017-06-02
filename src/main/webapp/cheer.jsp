<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Boot Demo</title>
</head>
<body>
    <h3>Three cheers for ${name}!</h3><br>
    <input type="button" onclick="cheerAgain()" value="Return">
    <script language="javascript">
        function cheerAgain() {
            window.location.href= "/";
        }
    </script>
</body>
</html>
