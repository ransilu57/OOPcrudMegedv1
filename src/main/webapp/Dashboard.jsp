<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - School Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .title-container {
            background-color: #1a2b49; 
            width: 100%;
            padding: 20px 0;
            text-align: center;
        }
        .title-container h1 {
            color: white;
            font-size: 2.5rem;
            font-weight: bold;
            margin: 0;
        }
        .dashboard-container {
            flex-grow: 1;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .dashboard {
            display: grid;
            grid-template-columns: repeat(3, 200px);
            gap: 20px;
        }
        .tile {
            background-color: #356abc;
            color: white;
            width: 200px;
            height: 150px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 10px;
            text-align: center;
            font-size: 1.2rem;
            font-weight: bold;
            text-decoration: none;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }
        .tile:hover {
            background-color: #285493;
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.3);
        }
        footer {
            background-color: #356abc;
            color: white;
            padding: 10px;
            text-align: center;
            font-size: 0.9rem;
            box-shadow: 0 -4px 8px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
    
    <div class="title-container">
        <h1>School Management System</h1>
    </div>

  
    <div class="dashboard-container">
        <div class="dashboard">
            <a href="<%=request.getContextPath()%>/list" class="tile">Scholarship Requests</a>
            <!--<a href="PaymentDashBoard.jsp" class="tile">Payments</a>-->
            <a href="#" class="tile">Enrollment</a>
            <a href="#" class="tile">Complaints</a>
        </div>
    </div>

   
    <footer>
        &copy; 2024 School Management System | Designed for educational purposes
    </footer>
</body>
</html>
