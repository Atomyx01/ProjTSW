function editProduct(productId) {
    
    $.ajax({
        type: "POST",
        url: "edit",
        data: { productId: productId },
        success: function(response) {
			window.location.href = "product?id=" + productId;
        },
        error: function(xhr, status, error) {
            console.log("Errore: " + error);
        }
    });
}

function deletePurchase(purchaseId) {
    
    $.ajax({
        type: "POST",
        url: "deletePurchase",
        data: { purchaseId: purchaseId },
        success: function(response) {
			window.location.href = "delete_requests";
        },
        error: function(xhr, status, error) {
            console.log("Errore: " + error);
        }
    });
}