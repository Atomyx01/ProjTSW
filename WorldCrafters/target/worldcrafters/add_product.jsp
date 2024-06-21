<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="it.unisa.bean.Product" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<title>WorldCrafters</title>
	<%@ include file="templates/head.html" %>
    <link rel="stylesheet" type="text/css" href="styles/add_product.css">
    <script src="scripts/admin.js"></script>
</head>
<body>

    <%@ include file="templates/header.jsp"%>
	<%@ include file="templates/sidebar.jsp"%>
	<%@ include file="templates/userbar.jsp" %>
	<%@ include file="templates/presentationBg.jsp" %>
	

    <main>

			<div id="addProductContainer">
		
				<h2>Aggiungi prodotto</h2>
					    	
			    	<form method="POST" action="addProduct">
			    	
			    		<div class="form-group">
							<label for="name">Name:</label> 
							<textarea name="name" required id="name" rows="4"></textarea>
						</div>
						
						<div class="form-group">
							<label for="description">Description:</label> 
							<textarea name="description" required id="description" rows="4"></textarea>
						</div>
						
						<div class="form-group">
							<label for="price">Price:</label> 
							<input type="text" name="price" id="price" required/>
						</div>
						
						<div class="form-group">
							<label for="quantity">Quantity:</label> 
							<input type="text" name="quantity" required id="quantity"/>
						</div>
						
						<div class="form-group">
							<label for="imgSrc">Image source:</label> 
							<input type="text" name="imgSrc" required id="imgSrc"/>
						</div>
						
						<div class="form-group">
							<label for="seller">Seller:</label> 
							<input type="text" name="seller" required id="seller"/>
						</div>
						
						<div class="form-group">
							<label for="listingDate">Listing date:</label> 
							<input type="text" name="listingDate" required id="listingDate"/>
						</div>
	
						<div class="form-group">
							<label for="category">Category:</label> 
							<select name="category" id="category" required>
								<option value="ABBIGLIAMENTO_BAMBINO">Abbigliamento Bambino</option>
							    <option value="ABBIGLIAMENTO_UOMO">Abbigliamento Uomo</option>
							    <option value="ABBIGLIAMENTO_DONNA">Abbigliamento Donna</option>
							    <option value="ACCESSORI">Accessori</option>
							    <option value="ANELLI">Anelli</option>
							    <option value="ANIMALI_DOMESTICI">Animali Domestici</option>
							    <option value="BORSE">Borse</option>
							    <option value="BRACCIALI">Bracciali</option>
							    <option value="CASA">Casa</option>
							    <option value="CINEMA">Cinema</option>
							    <option value="COLLANE">Collane</option>
							    <option value="COLLEZIONE">Collezione</option>
							    <option value="ELETTRONICA">Elettronica</option>
							    <option value="FOTOGRAFIA">Fotografia</option>
							    <option value="GIOCHI">Giochi</option>
							    <option value="LIBRI">Libri</option>
							    <option value="MUSICA">Musica</option>
							    <option value="ORECCHINI">Orecchini</option>
							    <option value="PITTURA">Pittura</option>
							    <option value="SCULTURA">Scultura</option>
							    <option value="VETRO">Vetro</option>
							    <option value="VINTAGE">Vintage</option>
							</select>
						</div>
						
						<div class="form-group">
							<label for="status">Status:</label> 
							<select name="status" required id="status">
									<option value="true" selected>true</option>
					                <option value="false">false</option>	
							</select>
						</div>
				
						<div class="form-group form-button">
							<input type="submit" name="submit" id="submit" value="Add product"/>
						</div>
			    	
			    	</form>
			</div>
	
	</main>
    
    <%@ include file="templates/footer.jsp"%>
    
</body>
</html>