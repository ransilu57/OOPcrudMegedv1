<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
    body {
        background: linear-gradient(to bottom, #6c8ec2, #b390e5); 
        background-size: cover;
        height: 100vh;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: Arial, sans-serif;
    }

    .login-container {
        background: rgba(255, 255, 255, 0.1); 
        border-radius: 15px;
        padding: 40px;
        width: 380px;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.37); 
        backdrop-filter: blur(10px); 
        border: 1px solid rgba(255, 255, 255, 0.18); 
        text-align: center;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .login-container h1 {
        color: #ffffff;
        margin-bottom: 30px;
        font-size: 2.5em;
        font-weight: 600;
    }

    .login-container form {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .login-container input[type="text"],
    .login-container input[type="password"] {
        width: 85%;
        padding: 15px;
        margin: 15px 0;
        border: none;
        border-radius: 10px;
        background: rgba(255, 255, 255, 0.2); 
        color: white;
        font-size: 1.1em;
        box-shadow: inset 0 4px 6px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
    }

    .login-container input[type="text"]::placeholder,
    .login-container input[type="password"]::placeholder {
        color: rgba(255, 255, 255, 0.7);
        font-size: 1.1em;
    }

    .login-container input[type="text"]:focus,
    .login-container input[type="password"]:focus {
        outline: none;
        box-shadow: 0 0 10px rgba(255, 255, 255, 0.8);
    }

    .login-container input[type="submit"] {
        width: 50%;
        padding: 15px;
        border: none;
        border-radius: 25px;
        background-color: #1e90ff;
        color: white;
        font-size: 1.2em;
        cursor: pointer;
        margin-top: 20px;
        transition: background-color 0.3s ease;
    }

    .login-container input[type="submit"]:hover {
        background-color: #104e8b;
    }
</style>
</head>
<body>
    <div class="login-container">
        <h1>Log In</h1>
        <form action="log" method="post">
            <input type="text" name="sid" placeholder="Student ID" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" name="submit" value="Login">
        </form>
    </div>
</body>
</html>
