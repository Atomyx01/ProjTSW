<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>WorldCrafters</title>
    <link rel="stylesheet" type="text/css" href="styles/header.css">
    <script src="scripts/cart.js"></script>
    <script src="scripts/header.js"></script>
</head>
<body>
	
	<header>
        
        <a id="logoContainer" href="home">
        	<img src="images/logo2.png" alt="Logo">
        	<span>WorldCrafters</span>
        </a>
        
        <a class="link" id="categoriesButton" onclick="toggleSidebar()"><i class="fa fa-bars" aria-hidden="true"></i></a>
        
        <div id="searchbar">
		    <input type="text" id="searchInput" placeholder="Cerca...">
		    <ul id="searchResults"></ul>
		    <button id="searchButton"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
    	
        <a class="link" id="homeButton" href="home">Home</a>

        <div>
        	<a class="link" id="cartButton" href="cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i></a>
        	<span id="cartQuantity"></span>
        </div>
        <a class="link" id="userButton" onclick="toggleUserbar()"><i class="fa fa-user" aria-hidden="true"></i></a>
         
    </header>
    <div id="headerSpace"></div>
    
</body>
</html>