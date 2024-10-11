

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>School Information Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	
	
	 <style>
	 body {
            background: linear-gradient(to bottom, #a6b8d2, #b39cd3); 
            background-size: cover;
            height: 100vh;
            margin: 0;
            padding: 0;
        }

		
    input.readonly-sid {
    background-color: rgba(255, 255, 255, 0.5); 
    border: 2px solid rgba(30, 144, 255, 0.7); 
    font-weight: bold; 
    color: #000; 
    cursor: default; 
      }


    input.readonly-sid:focus {
    background-color: rgba(30, 144, 255, 0.2); 
    border-color: rgba(30, 144, 255, 0.9); 
    outline: none; 
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
            background-color: rgba(255, 255, 255, 0.3);
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
    </style>
	
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: rgb(5, 17, 35);">
			<div style="flex-grow: 1; text-align: center;"> 
				<span class="navbar-brand">School Information Management System</span>
			</div>

			<ul class="navbar-nav ml-auto">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Requests</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${request != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${request == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${request != null}">
            			Edit Request
            		</c:if>
            		
            	
            		
						<c:if test="${request == null}">
            			Add New Request
            		</c:if>
					</h2>
				</caption>

				<c:if test="${request != null}">
					<input type="hidden" name="id" value="<c:out value='${request.id}' />" />
					<input type="hidden" name="req_date" value="<c:out value='${request.req_date}' />" />
					<input type="hidden" name="status" value="<c:out value='${request.status}' />" />
				</c:if>
				

				<fieldset class="form-group">
					<label>Request Sid</label> 
					<input type="text"
						    value="<c:out value='${sessionScope.sid}' />" class="form-control readonly-sid"
						   name="sid" required="required" readonly>
				</fieldset>

				<fieldset class="form-group">
				<label>Request Type</label>
				<select name="type" class="form-control" required>
					<option value="" disabled <c:if test="${empty request.type}">selected</c:if>>Select Type</option>
					<option value="Merit-Based" <c:if test="${request.type == 'Merit-Based'}">selected</c:if>>Merit-Based</option>
					<option value="Need-Based" <c:if test="${request.type == 'Need-Based'}">selected</c:if>>Need-Based</option>
					<option value="International" <c:if test="${request.type == 'International'}">selected</c:if>>International</option>
				</select>
			</fieldset>

				<fieldset class="form-group">
					<label>Request Description</label> <input type="text"
						value="<c:out value='${request.description}' />" class="form-control"
						name="description">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>