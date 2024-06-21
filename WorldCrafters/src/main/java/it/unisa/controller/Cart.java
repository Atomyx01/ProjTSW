package it.unisa.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class Cart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Cart.class.getName());
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} catch (ServletException se) {
			logger.log(Level.WARNING, se.getMessage());
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		
	}

}
