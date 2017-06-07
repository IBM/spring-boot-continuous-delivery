<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create a new account</title>
</head>
<body>
    <h2>Create a new account</h2>
    <p>Enter your name and click submit to open a new account.</p>
    <form method="post" action="/account">
	    <label for="name">Name:</label>
	    <input type="text" name="name" id="name"><br><br>
	    <input type="submit" value="Submit">
    </form>
</body>
</html>
