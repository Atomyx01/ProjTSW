<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<title>WorldCrafters</title>
	<%@ include file="templates/head.html" %>
    <link rel="stylesheet" type="text/css" href="styles/log-reg.css">
    <script src="scripts/log.js"></script>
</head>
<body>
	
	<%
		String email = (String)request.getAttribute("email");
		if(email == null) email = "";
	%>
	
	
	<!-- Aggiungi il wrapper per la finestra di login -->
	<div class="modal-overlay">
		<div class="modal-content">
			<button class="close-button" onclick="closeModal()">X</button>
			<!-- Aggiorna il testo del pulsante di chiusura -->
			<h2 class="form-title">Sign in</h2>
			<!-- Aggiorna il testo del titolo -->
			<form method="post" action="login" id="login-form">
				<div class="form-group">
					<label for="email">Email:</label> 
					<input type="text" name="email" id="email" required value="<%=email %>" data-validation="email" placeholder="La tua email" />
					<div id="errorMessageEmail"></div>
				</div>
				<div class="form-group">
					<label for="password">Password:</label> 
					<input type="password" name="password" required id="password" data-validation="password" placeholder="Password" />
				</div>
				<%
		        String error = (String)request.getAttribute("error");
				if(error != null) {
				%>
					<div class="error-message"><%=error %></div>
					<br/>
				<%
				}
				%>
						
				<div class="form-group form-button">
					<input type="submit" name="signin" id="signin" value="Accedi" disabled/>
				</div>
			</form>
			
		</div>
	
	</div>
		
</body>
</html>
