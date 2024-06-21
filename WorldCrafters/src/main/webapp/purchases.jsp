<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="it.unisa.bean.Purchase" %>
<%@ page import="it.unisa.bean.Payment" %>
<%@ page import="it.unisa.bean.Product" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>WorldCrafters</title>
    <%@ include file="templates/head.html" %>
    <link rel="stylesheet" type="text/css" href="styles/purchases.css">
</head>
<body>
    
    <%@ include file="templates/header.jsp" %>
    <%@ include file="templates/sidebar.jsp" %>
    <%@ include file="templates/userbar.jsp" %>
    
    <div id="presentationContainer" onclick="closeAll()">
    	<%@ include file="templates/presentationBg.jsp" %>
    </div>
    
    <main onclick="closeAll()">

		<h1>Ordini effettuati</h1>
		
		<% @SuppressWarnings("unchecked")
		List<Purchase> purchases = (List<Purchase>) request.getAttribute("purchases"); %>
		<% @SuppressWarnings("unchecked")
		List<Payment> payments = (List<Payment>) request.getAttribute("payments"); %>
		<% @SuppressWarnings("unchecked")
		Map<Integer, List<Product>> productsByPurchase = (Map<Integer, List<Product>>) request.getAttribute("productsByPurchase"); %>
		
		<div id="purchasesList">

			<!-- Creare tanti cart-list-item quanti sono gli ordini nel database orders con id utente della sessione corrente -->
			<% if(purchases.size()!=0) { %>
				<% for (int i = 0; i < purchases.size(); i++) { %>
					<% Purchase purchase = purchases.get(i); %>
					<% Payment payment = payments.get(i); %>
					<% List<Product> products = productsByPurchase.get(purchase.getId()); %>
					
					<div class="purchase-img-container">
				    	<% for(Product product:products) { %>
				    		<img class="purchase-img" src="<%= product.getImgSrc()%>" alt="Purchase Item">
				    	<% } %>
			    	</div>
					
					<div class="purchase-details">
				    
				    	<% SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy"); %>
				    	<% String date = sdfDate.format(purchase.getDate()); %>
				    	<% SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm"); %>
				    	<% String time = sdfTime.format(purchase.getTime()); %>
				    	<% SimpleDateFormat sdfEstimatedDate = new SimpleDateFormat("dd/MM/yyyy"); %>
				    	<% String estimatedDate = null; %>
				    	
				    	<% Object estimatedDateObj = purchase.getEstimatedDate(); %>
				    	<% if(estimatedDateObj!=null) { %>
				    		<% estimatedDate = sdfEstimatedDate.format(purchase.getEstimatedDate()); %>
				    	<% } %>
				
				
				    	<p>Id: <%= purchase.getId() %> </p>
				    	<p>Data: <%= date %> </p>
				    	<p>Ore: <%= time %></p>
				    	<p>Spesa: € <%= String.format("%.2f", purchase.getAmount()) %></p>
				    	<p>Metodo di pagamento: carta che termina con <%= payment.getCardNumber().substring(15, 19) %></p>
				    	<p>Stato:  <%= purchase.getStatus() %></p>
				    	
				    		
				    	<% if(estimatedDateObj!=null) { %>
				    		<p>Data di consegna stimata: <%= estimatedDate %></p>
				    	<% } %>
				    	
				    	<% String status = purchase.getStatus().toString(); %>
				    	<% boolean deleteRequest = purchase.getDeleteRequest(); %>
				    	<% if(deleteRequest==false) {%>
				    		<% if(status!="CONSEGNATO" && status!="ANNULLATO") { %>
				    			<p id="deleteRequest" onclick="deleteRequest('<%=purchase.getId()%>')">Annulla ordine</p>
				    		<% } %>
				    	<% } else {%>
				    		<p>Richiesta di annullamento effettuata</p>
				    	<% } %>
				    	
			    	</div>

				    <br>
				<% } %>
				
			</div>
			<% } else { %>
		    	<div id="noPurchase">
                   	<p>Nessun ordine effettuato</p>
                   </div>
			<% } %>

    </main>
    
    <%@ include file="templates/footer.jsp" %>
    
    <script>
	    function deleteRequest(purchaseId) {
	    	$.ajax({
		        url: "DeletePurchaseRequestServlet", // URL della tua servlet
		        type: "POST",
		        data: { purchaseId: purchaseId }, // Dati da inviare
		        success: function(response) {
		            // Gestisci la risposta JSON dal server
		            if (response.success) {
		                // Operazione completata con successo
		                alert("La richiesta è stata effettuata");
		                $("#deleteRequest").hide();
		                
		            } else {
		                // Operazione fallita
		                alert("Si è verificato un errore durante l'annullamento dell'ordine.");
		            }
		        },
		        error: function() {
		            // Gestisci gli errori di connessione o altri errori
		            alert("Si è verificato un errore durante la richiesta.");
		        }
		    });
	    }
    </script>
    
</body>
</html>