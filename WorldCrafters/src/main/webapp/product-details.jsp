<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="it.unisa.bean.Product" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<title>WorldCrafters</title>
	<%@ include file="templates/head.html" %>
    <link rel="stylesheet" type="text/css" href="styles/product-details.css">
    <script src="scripts/cart.js"></script>
    <script src="scripts/admin.js"></script>
</head>
<body>


    <%@ include file="templates/header.jsp"%>
	<%@ include file="templates/sidebar.jsp"%>
	<%@ include file="templates/userbar.jsp" %>
	<%@ include file="templates/presentationBg.jsp" %>
	

    <main>

	    <div class="product-container" onclick="closeAll()">
	
		    <% Product product = (Product) request.getAttribute("product"); %>
		
		    <div class="product-details">
		      	<div class="product-image">
		        	<img src="<%= product.getImgSrc()%>" alt="<%= product.getName()%>">
		      	</div>
		      	<div class="product-info">
			        <h1><%= product.getName() %></h1>
			        <p class="price">€ <%= String.format("%.2f", product.getPrice()) %></p>
			        <p>Venditore: <%= product.getSeller() %></p>
			        <p>Quantità disponibile: <%= product.getQuantity() %></p>
			        <p>Data di inserimento: <%= product.getListingDate() %></p>
			        <div class="description">
			          	<p><%= product.getDescription() %></p>
			        </div>
			        
			        <% HttpSession session2 = request.getSession();%>
    				<% if(session2.getAttribute("isAdmin") != null && (Boolean) session2.getAttribute("isAdmin")) { %>
				        <p>Status: <%= product.getStatus() %></p>
				    <% } else {%>
				    	<span>Quantità:</span>
		                <select id="quantity">
		                <% int maxOptions = Math.min(10, product.getQuantity()); %>
		                    <% for (int i = 1; i <= maxOptions; i++) { %>
		                        <option value="<%= i %>"><%= i %></option>
		                    <% } %>
		                </select>
				        <button class="add-to-cart" onclick="addToCart('<%= product.getId() %>')">Aggiungi al carrello</button>
				    <% } %>
			        
		      	</div>
			</div>
			
			<% if(session2.getAttribute("isAdmin") != null && (Boolean) session2.getAttribute("isAdmin")) { %>
			
			<div id="editProductContainer">
		
				<h2>Modifica prodotto</h2>
					    	
			    	<form method="POST" action="editProduct">
			    	
			    		<div class="form-group">
							<label for="name">Name:</label> 
							<textarea name="name" required id="name" rows="4"><%=product.getName()%></textarea>
						</div>
						
						<div class="form-group">
							<label for="description">Description:</label> 
							<textarea name="description" required id="description" rows="4"><%=product.getDescription()%></textarea>
						</div>
						
						<div class="form-group">
							<label for="price">Price:</label> 
							<input type="text" name="price" id="price" required value="<%=product.getPrice()%>" />
						</div>
						
						<div class="form-group">
							<label for="quantity">Quantity:</label> 
							<input type="text" name="quantity" required id="quantity" value="<%=product.getQuantity()%>"/>
						</div>
						
						<div class="form-group">
							<label for="imgSrc">Image source:</label> 
							<input type="text" name="imgSrc" required id="imgSrc" value="<%=product.getImgSrc()%>"/>
						</div>
						
						<div class="form-group">
							<label for="seller">Seller:</label> 
							<input type="text" name="seller" required id="seller" value="<%=product.getSeller()%>"/>
						</div>
						
						<div class="form-group">
							<label for="listingDate">Listing date:</label> 
							<input type="text" name="listingDate" required id="listingDate" value="<%=product.getListingDate()%>"/>
						</div>
	
						<div class="form-group">
							<label for="category">Category:</label> 
							<input type="text" name="category" required id="category" value="<%=product.getCategory()%>"/>
						</div>
						
						<div class="form-group">
							<label for="status">Status:</label> 
							<select name="status" required id="status">
								
								<% if(product.getStatus()) { %>
									<option value="true" selected>true</option>
					                <option value="false">false</option>
								<% } else {%>
									<option value="true">true</option>
					                <option value="false" selected>false</option>
								<% } %>
								
							</select>
						</div>
						
						<input type="hidden" name="id" required id="id" value="<%=product.getId()%>"/>
						
						<div class="form-group form-button">
							<input type="submit" name="submit" id="submit" value="Edit product"/>
						</div>
			    	
			    	</form>
			</div>
			<% } %>
		</div>
	
	</main>
    
    <%@ include file="templates/footer.jsp"%>
    
</body>
</html>