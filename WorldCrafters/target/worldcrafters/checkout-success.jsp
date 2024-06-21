<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>WorldCrafters</title>
    <%@ include file="templates/head.html" %>
    <link rel="stylesheet" type="text/css" href="styles/checkout-success.css">
</head>
<body>
	
	<%@ include file="templates/header.jsp" %>
    <%@ include file="templates/sidebar.jsp" %>
    <%@ include file="templates/userbar.jsp" %>
	<%@ include file="templates/presentationBg.jsp" %>
    

    <main onclick="closeAll()">
        <div id="background"></div>
        
        <div id="messageContainer">
        	<h1>Ordine effettuato con successo<br/>Puoi visualizzare lo stato dell'ordine nell'apposita sezione</h1>
        	<br><br>
        	<div id="linkContainer">
        		<a href="home">Torna alla home</a>
        	</div>
        </div>
    </main>

	<%@ include file="templates/footer.jsp" %>

</body>
</html>