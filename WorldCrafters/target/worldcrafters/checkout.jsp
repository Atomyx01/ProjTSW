<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<title>WorldCrafters</title>
	<%@ include file="templates/head.html" %>
    <link rel="stylesheet" type="text/css" href="styles/checkout.css">
    <script src="scripts/checkout.js"></script>
</head>
<body>

	<%@ include file="templates/header.jsp" %>
    <%@ include file="templates/sidebar.jsp" %>
    <%@ include file="templates/userbar.jsp" %>
    <%@ include file="templates/presentationBg.jsp" %>
    
    <%
		String fullName = (String)request.getAttribute("fullName");
		if(fullName == null) fullName = "";
		
		String email = (String)request.getAttribute("email");
		if(email == null) email = "";
		
		String address = (String)request.getAttribute("address");
		if(address == null) address = "";
		
		String city = (String)request.getAttribute("city");
		if(city == null) city = "";
		
		String state = (String)request.getAttribute("state");
		if(state == null) state = "";
		
		String zipCode = (String)request.getAttribute("zipCode");
		if(zipCode == null) zipCode = "";
		
		String accountholder = (String)request.getAttribute("accountholder");
		if(accountholder == null) accountholder = "";
		
		String cardNumber = (String)request.getAttribute("cardNumber");
		if(cardNumber == null) cardNumber = "";
		
		String expMonth = (String)request.getAttribute("expMonth");
		if(expMonth == null) expMonth = "";
		
		String expYear = (String)request.getAttribute("expYear");
		if(expYear == null) expYear = "";
		
		String cvv = (String)request.getAttribute("cvv");
		if(cvv == null) cvv = "";
	%>
	
	<main id="checkoutContainer" onclick="closeAll()">
		<div class="row">
			<div class="col-75">
		    	<div class="container">
		      		
		      		<form id="checkout-form" name="checkout-form" method="POST" action="CheckoutForm" novalidate>
		
			        	<div class="row">
			          		<div class="col-50">
					            <h3>Indirizzo di fatturazione</h3><br/>
					            <label for="fullName"><i class="fa fa-user"></i> Nome e cognome</label>
					            <input type="text" id="fullName" name="fullName" value="<%=fullName %>" required data-validation="fullName">
					            <label for="email"><i class="fa fa-envelope"></i> Email</label>
					            <input type="text" id="email" name="email" value="<%=email %>" required data-validation="email">
					            <label for="address"><i class="fa fa-address-card-o"></i> Indirizzo</label>
					            <input type="text" id="address" name="address" value="<%=address %>"  required data-validation="address">
					            <label for="city"><i class="fa fa-institution"></i> Città</label>
					            <input type="text" id="city" name="city" value="<%=city %>"  required data-validation="alphaString">
			
					            <div class="row">
					              	<div class="col-50">
						                <label for="state">Stato</label>
						                <input type="text" id="state" name="state" value="<%=state %>" required data-validation="alphaString">
					              	</div>
					             	<div class="col-50">
					              		<label for="zipCode">Codice Postale</label>
					                	<input type="text" id="zipCode" name="zipCode" value="<%=zipCode %>" placeholder="00000" required data-validation="zipCode">
					              	</div>
					            </div>
			          		</div>
			
						<div class="col-50">
							<h3>Pagamento</h3><br/>
				          	<label>Carte accettate</label>
				            <div class="icon-container">
				            	<i class="fa fa-cc-visa fa-lg" style="color:navy;"></i>
				             	<i class="fa fa-cc-mastercard fa-lg" style="color:orangered;"></i>
				            </div>
				            <label for="accountholder">Intestatario carta</label>
				            <input type="text" id="accountholder" name="accountholder" value="<%=accountholder %>" required data-validation="fullName">
				            <label for="cardNumber">Numero carta</label>
				            <input type="text" id="cardNumber" name="cardNumber" placeholder="0000-0000-0000-0000" value="<%=cardNumber %>" required data-validation="cardNumber">
				
				            <div class="row">
				            	<div class="col-50">
				                	<label for="expDate">Data di scadenza</label>
				            		<div id="expDate">
				            			<input type="text" id="expDate" name="expMonth" placeholder="00" value="<%=expMonth %>" required data-validation="expMonth">
				            			<input type="text" id="expDate" name="expYear" placeholder="0000" value="<%=expYear %>" required data-validation="expYear">
				            		</div>
				              	</div>
				              	
				              	<div class="col-50">
				                	<label for="cvv">CVV</label>
				                	<input type="text" id="cvv" name="cvv" placeholder="000" value="<%=cvv %>" required data-validation="cvv">
				              	</div>
				            </div>
						</div>
			
					</div>
			        <input type="submit" value="Procedi con il pagamento" class="btn">
				</form>
				
				<%
		        String error = (String)request.getAttribute("error");
				if(error != null) {
				%>
				<div class="error-message"><%=error %></div>
				<%
				}
				%>
			</div>
		</div>

		</div>
	</main>

	<%@ include file="templates/footer.jsp" %>
		
</body>
</html>