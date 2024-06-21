function updateCart() {
    $.ajax({
        type: "GET",
        url: "update-cart",
        dataType: "json",
        success: function(response) {
            
            let cartItems = response;

            // Ora puoi utilizzare cartItems per aggiornare il contenuto del carrello nel DOM
            let cartList = $("#cartList");
            let cartPayment = $("#cartPayment");
            let totalPrice = 0;
            let totalQuantity = 0;
            let quantityHeader;

            cartList.empty();
            cartPayment.empty();

            if (cartItems.length > 0) {
                let listHtml = "";
                let paymentHtml = "<h2>Riepilogo</h2>";
                
                paymentHtml += `
                    <div class="cart-payment-item">
                    	<strong class="product-name">Prodotto</strong>
                    	<strong class="product-quantity">Quantità</strong>
                    	<strong class="product-price">Prezzo</strong>
                    </div>   
                `;

                $.each(cartItems, function (index, item) {
                    
                    let priceForQuantity = item.price * item.selectedQuantity;
                    let maxOptions = Math.min(10, item.quantity);
                    
                    // Utilizza template string per creare l'HTML dinamico per cartList
                    listHtml += `
                        <div class="cart-list-item">
                            <img src="${item.imgSrc}" />
                            <p class="product-name">${item.name}</p>
                            <p class="product-price">€ ${item.price.toFixed(2)}</p>   
                            <select class="quantity" data-product-id="${item.id}">
                    `;
                    
                    for (let i = 1; i <= maxOptions; i++) {
				        if(i==item.selectedQuantity) {
							listHtml += `
                        		<option value=${i} selected>${i}</option>
                    		`;
						} else {
							listHtml += `
                        		<option value=${i}>${i}</option>
                    		`;
						}        
				    } 
                                    
                    listHtml += `   
                            </select>
                            <button onclick="removeFromCart('${item.id}')"><i class="fa fa-close fa-xl"></i></button><br>
                        </div>
                    `;


					
                    // Utilizza template string per creare l'HTML dinamico per cartPayment
                    paymentHtml += `
                        <div class="cart-payment-item">
                        	<p class="product-name">${item.name}</p>
                        	<p class="product-quantity">${item.selectedQuantity}</p>
                        	<p class="product-price">€ ${priceForQuantity.toFixed(2)}</p>
                        </div>   
                    `;
                    
                    //Aggiorna il totale del prezzo a ogni ciclo
                    totalPrice += priceForQuantity;
                    totalQuantity += item.selectedQuantity;
                    
                });
                
                paymentHtml += `
                    <br/>
                    <div class="cart-payment-item" id="total">
                    	<strong class="product-name">Totale</strong>
                    	<strong class="product-price">€ ${totalPrice.toFixed(2)}</strong>
                    </div>
                `;
                
                paymentHtml += `
                    <br>
		            <a id="checkout" href="checkout">Vai al checkout</a>
	                <br><br>
	                <button id="back" onclick="goBack()">Continua gli acquisti</button>
                `;

                cartList.append(listHtml);
                cartPayment.append(paymentHtml);
                
                quantityHeader = document.getElementById("cartQuantity");
                quantityHeader.innerHTML = totalQuantity;
                
            } else {
                cartList.append("<p>Il carrello è vuoto</p>");
                quantityHeader = document.getElementById("cartQuantity");
                quantityHeader.innerHTML = "";
            }
        },
        error: function (xhr, status, error) {
            console.log("Errore durante il recupero dei prodotti dal carrello: " + error);
        }
    });
}

window.onload = function() {
    // Chiama la funzione updateCart quando il documento è pronto
    updateCart();
}