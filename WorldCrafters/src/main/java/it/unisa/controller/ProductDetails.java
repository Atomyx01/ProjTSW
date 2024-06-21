package it.unisa.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.bean.Product;
import it.unisa.dao.ProductDAO;



@WebServlet("/product")
public class ProductDetails extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(ProductDetails.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productIdStr = request.getParameter("id");
		int productId = -1;
		
		try {
			productId = Integer.parseInt(productIdStr);
		} catch (NumberFormatException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	    
		ProductDAO productDAO = new ProductDAO();

	    Product product = productDAO.getProductById(productId);
		
		request.setAttribute("product", product);
		
		try {
			request.getRequestDispatcher("product-details.jsp").forward(request, response);
        } catch (ServletException se) {
        	logger.log(Level.WARNING, se.getMessage());
    	} catch (IOException e) {
    		logger.log(Level.WARNING, e.getMessage());
    	}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
        } catch (ServletException se) {
        	logger.log(Level.WARNING, se.getMessage());
    	} catch (IOException e) {
    		logger.log(Level.WARNING, e.getMessage());
    	}
	}

}