package it.unisa.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.bean.Product;
import it.unisa.dao.CartDAO;
import it.unisa.dao.CartItemDAO;



@WebServlet("/removeFromCart")
public class RemoveFromCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RemoveFromCart.class.getName());
        
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        int productId = -1;
        
        try {
        	productId = Integer.parseInt(request.getParameter("productId"));
    	} catch (NumberFormatException e){
    		logger.log(Level.WARNING, e.getMessage());
    	}
        CartItemDAO cartItemDAO= new CartItemDAO();
		
		HttpSession session = request.getSession();
		int userId;
		int cartId;
		
		if(session.getAttribute("userId") != null) {
			
			userId = (int) session.getAttribute("userId");
			//salvare nel database
			CartDAO cartDAO = new CartDAO();
			
			cartId = cartDAO.getCartByUserId(userId);
			
			cartItemDAO.deleteCartItem(cartId, productId);
			

		} else {
			//sessione non persistente

	        List<Product> productList = (List<Product>) session.getAttribute("productList");
	        if (productList == null) {
	            // Se la lista non esiste nella sessione, non c'è niente da rimuovere
	            return;
	        }
	        
	        for (Product item : productList) {  
	        	if (item.getId() == productId) {    
	        		productList.remove(item);
	        		break;	   
	            }
	        }

		}
	}

}
