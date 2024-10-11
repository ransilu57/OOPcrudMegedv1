<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Scholarship Request Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	
	 <style>
	  body {
            background: linear-gradient(to bottom, #90a8cb, #b99ee0); 
            background-size: cover;
            height: 100vh;
            margin: 0;
            padding: 0;
        }

        .card {
            background: rgba(255, 255, 255, 0.2); 
            border-radius: 15px;                  
            backdrop-filter: blur(10px);         
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
            padding: 30px;
            border: 1px solid rgba(255, 255, 255, 0.3);
            color: white;
        }

        input.form-control {
            background-color: rgba(1, 0, 0, 0.3);
            border: none;
            border-radius: 10px;
            padding: 10px 15px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
            color: white;
            font-size: 16px;
            margin-bottom: 15px;
        }

        input::placeholder {
            color: rgba(255, 255, 255, 0.6); 
        }

        input.form-control:focus {
            outline: none;
            box-shadow: 0 0 10px rgba(30, 144, 255, 0.6);
        }

        button.btn-success {
            background-color: rgba(30, 144, 255, 0.7); 
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 20px;
            font-size: 16px;
            transition: background-color 0.3s ease;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2); 
        }

        button.btn-success:hover {
            background-color: rgba(30, 144, 255, 0.9);
        }

        h2 {
            color: rgba(255, 255, 255, 0.9); 
            font-weight: 500;
            margin-bottom: 20px;
        }


        h3 {
            color: rgba(255, 255, 255, 0.9); 
            font-weight: 500;
            margin-bottom: 20px;
        }
        .navbar {
            height: 85px;
            padding: 20px 1px 20px 90px;
        }

        .navbar-brand {
            font-size: 2em;
            text-align: center;
            flex-grow: 1;
        }

        .nav-link {
            font-size: 1.2em;
        }

        /* Table styling */
        table {
            background-color: rgba(210, 222, 224, 0.9);
            border-radius: 5px;
            overflow: hidden;
            width: 100%;
            margin-bottom: 1rem;
            color: #616467;
            box-shadow: 0 4px 8px rgba(211, 219, 224, 0.1);
        }

        th {
            background-color: #799ff9;
            color: rgb(8, 8, 8);
            text-align: center;
            padding: 10px;
        }

        td {
            padding: 10px;
            text-align: center;
        }

        

        tr:hover {
            background-color: #f3f3f6;
        }

        /* Edit and Delete button styling */
        .btn-edit {
            background-color: #040452;
            color: rgb(246, 247, 246);
            border-radius: 10px;
            padding: 5px 15px;
            border: none;
            transition: 0.3s;
        }

        .btn-edit:hover {
            background-color: #010c24;
        }

        .btn-delete {
            background-color: #dc3545;
            color: white;
            border-radius: 10px;
            padding: 5px 15px;
            border: none;
            transition: 0.3s;
        }

        .btn-delete:hover {
            background-color: #c82333;
        }
    </style>
	
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: rgb(5, 17, 35);">
			<div style="flex-grow: 1; text-align: center;"> 
				<span class="navbar-brand">Request Management Application</span>
			</div>

			<ul class="navbar-nav ml-auto">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Requests</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">

		<div class="container">
			<h3 class="text-center">List of Requests</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Request</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>SID</th>
						<th>TYPE</th>
						<th>DESCRIPTION</th>
						<th>DATE</th>
						<th>STATUS</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="request" items="${listRequest}">
						<tr>
							<td><c:out value="${request.id}" /></td>
							<td><c:out value="${request.sid}" /></td>
							<td><c:out value="${request.type}" /></td>
							<td><c:out value="${request.description}" /></td>
							<td><c:out value="${request.req_date}" /></td>
							<td><c:out value="${request.status}" /></td>
							<td>
								<a href="edit?id=<c:out value='${request.id}' />" class="btn-edit">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="delete?id=<c:out value='${request.id}' />" class="btn-delete">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
