<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="it.unisa.bean.Product" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>WorldCrafters</title>
    <%@ include file="templates/head.html" %>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
    <link rel="stylesheet" type="text/css" href="styles/cart.css">
    <script src="scripts/cart.js"></script>
</head>
<body>

	<%@ include file="templates/header.jsp" %>
    <%@ include file="templates/sidebar.jsp" %>
    <%@ include file="templates/userbar.jsp" %>
	<%@ include file="templates/presentationBg.jsp" %>
    
    <main onclick="closeAll()">
    	
    	<h1>Carrello</h1>
    	
    	<div id="cartContainer">
	    	<div id="cartList"></div>
	    	<div id="cartPayment"></div>
	   	</div>
    </main>
    

    <%@ include file="templates/footer.jsp" %>
    
</body>
</html>