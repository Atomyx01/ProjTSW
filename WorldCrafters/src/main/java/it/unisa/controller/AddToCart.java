package it.unisa.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import it.unisa.dao.ProductDAO;


@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String PRODUCT_LIST = "productList";
	private static final Logger logger = Logger.getLogger(AddToCart.class.getName());
       
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productId = -1;
		int selectedQuantity = -1;
		try {
		    productId = Integer.parseInt(request.getParameter("productId"));
		    selectedQuantity = Integer.parseInt(request.getParameter("selectedQuantity"));
		} catch (NumberFormatException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userId") != null) {
			//sessione persistente
			persistentSession(productId,selectedQuantity,session,response);
		} else {
			//sessione non persistente
			nonPersistentSession(productId,selectedQuantity,session,response);
		}
  
	}
	
	private void persistentSession(int productId, int selectedQuantity, HttpSession session, HttpServletResponse response) {
		
		int userId = (int) session.getAttribute("userId");
        //salvare nel database
        CartDAO cartDAO = new CartDAO();
        CartItemDAO cartItemDAO= new CartItemDAO();
        
        int cartId = cartDAO.getCartByUserId(userId);
        
        //Controllo se é già presente un carrello
        if(cartId == -1) {
            //creo nuovo carrello
            cartId = cartDAO.setCart(userId);
        }
        
        cartItemDAO.setCartItem(cartId, productId, selectedQuantity);
        
        //recuperare prodotti nel carrello
        List<Product> productList = cartDAO.getAllCartProducts(cartId);
        
        session.setAttribute(PRODUCT_LIST, productList);
        
        try {
            response.sendRedirect("cart");
        } catch (IOException e) {
        	logger.log(Level.WARNING, e.getMessage());
        }
	}
	
	private void nonPersistentSession(int productId, int selectedQuantity, HttpSession session, HttpServletResponse response) {
		
		ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);
  
        @SuppressWarnings("unchecked")
        List<Product> productList = (List<Product>) session.getAttribute(PRODUCT_LIST);
        if (productList == null) {
            // Se la lista non esiste ancora nella sessione, crea una nuova lista
            productList = new ArrayList<>();
        }
        
        boolean found = false;
        for (Product item : productList) {
            if (item.getId() == productId) {
                item.setSelectedQuantity(selectedQuantity);
                found = true;
                break;
            }
        }
        
        if(!found) {
            product.setSelectedQuantity(selectedQuantity);
            productList.add(product);
        }
        
        // Aggiorna la lista del carrello nella sessione
        session.setAttribute(PRODUCT_LIST, productList);
        
        try {
            response.sendRedirect("cart");
        } catch (IOException e) {
        	logger.log(Level.WARNING, e.getMessage());
        }
	}

}
