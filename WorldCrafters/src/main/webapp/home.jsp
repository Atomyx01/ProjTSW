<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="it.unisa.bean.Product" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>WorldCrafters</title>
    <%@ include file="templates/head.html" %>
    <link rel="stylesheet" type="text/css" href="styles/home.css">
    <script src="scripts/home.js"></script>
</head>
<body>
    
    <%@ include file="templates/header.jsp" %>
    <%@ include file="templates/sidebar.jsp" %>
    <%@ include file="templates/userbar.jsp" %>
    
    <div id="presentationContainer" onclick="closeAll()">
    	<%@ include file="templates/presentationBg.jsp" %>
    	<p>Fatti ispirare dalle tradizioni<br>artigianali di tutto<br>il mondo</p>
    </div>

    <main onclick="closeAll()">

        <div id="showcaseContainer">
            <h1>Prodotti in evidenza</h1><br>
            <div id="showcase">
                
                <% @SuppressWarnings("unchecked")
                   List<Product> products = (List<Product>) request.getAttribute("products"); %>
				<% for (int i = 0; i < 18; i++) { %>
					<% Product product = products.get(i); %>
				        <div class="product">
	                        <div class="product-image">
	                            <a href="product?id=<%= product.getId() %>">
						            <img src="<%= product.getImgSrc()%>" alt="Product Image">
						        </a>
	                        </div>
	                        <div class="product-details">
	                            
	                            <% if(product.getStatus()) { %>
	                            	<a href="product?id=<%= product.getId() %>" class="product-name">
						            	<%= product.getName() %>
						        	</a>
	                            <% } else { %>
	                            	<a href="product?id=<%= product.getId() %>" class="product-name" style="color: red">
						            	<%= product.getName() %>
						        	</a>
	                            <% } %>
	                                
	                            <div class="product-price">
	                                € <%= String.format("%.2f", product.getPrice()) %>
	                            </div> 
	                        </div>  
                    	</div>
				<%} %>
				
            </div>
        </div>
    </main>
    
    <%@ include file="templates/footer.jsp" %>


</body>
</html>