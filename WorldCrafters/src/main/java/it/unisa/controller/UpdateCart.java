package it.unisa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.bean.Product;
import it.unisa.dao.CartDAO;


@WebServlet("/updateCart")
public class UpdateCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UpdateCart.class.getName());
       
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PRODUCT_LIST = "productList";
		Gson gson = new Gson();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        int userId;
        int cartId;
        List<Product> productList;
        
		if(session.getAttribute("userId")!=null) {
					
				userId = (int) session.getAttribute("userId");
				CartDAO cartDAO = new CartDAO();
				cartId = cartDAO.getCartByUserId(userId);

				productList = cartDAO.getAllCartProducts(cartId);
				
				session.setAttribute(PRODUCT_LIST, productList);
		}
		
        productList = (List<Product>) session.getAttribute(PRODUCT_LIST);
        
        if (productList == null || productList.isEmpty()) {

        	productList = new ArrayList<>();
        	session.setAttribute(PRODUCT_LIST, productList);
        	String json = gson.toJson(productList);
        	try {
            	response.getWriter().write(json);
        	} catch (IOException e){
        		logger.log(Level.WARNING, e.getMessage());
        	}
            return;
        }


        String json = gson.toJson(productList);    
        try {
        	response.getWriter().write(json);
    	} catch (IOException e){
    		logger.log(Level.WARNING, e.getMessage());
    	}
         
	}
	
}
