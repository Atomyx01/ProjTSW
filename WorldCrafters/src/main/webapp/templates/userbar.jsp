<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>WorldCrafters</title>
    <link rel="stylesheet" type="text/css" href="styles/userbar.css">
    <script src="scripts/sidebar-userbar.js"></script>
</head>
<body>
	
    <% HttpSession session1 = request.getSession();%>
    <% if(session1.getAttribute("userId")!=null) { %>
	    <% int userId = (int) session1.getAttribute("userId");%>
    	<div id="userbar">
	        <ul>
	            <li><a href="purchases">Ordini effettuati<br></a></li>
	            <li><a href="logout" onclick="invalidateSession()">Logout<br></a></li>
	        </ul>
	    </div>
	<% } else if(session1.getAttribute("isAdmin")!=null && session1.getAttribute("isAdmin").toString().equals("true")) { %>
		<div id="userbar">
	        <ul>
	            <li><a href="add_product.jsp">Add a product<br></a></li>
	            <li><a href="delete_requests">Delete requests<br></a></li>
	            <li><a href="logout" onclick="invalidateSession()">Logout<br></a></li>
	        </ul>
	    </div>
	<% } else { %>
    	<div id="userbar">
	        <ul>
	            <li><a href="login.jsp">Sign in</a></li>
	            <li><a href="register.jsp">Sign up</a></li>
	        </ul>
	    </div>
	<% } %>
	
</body>
</html>