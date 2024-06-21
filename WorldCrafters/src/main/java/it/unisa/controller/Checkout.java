package it.unisa.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(Checkout.class.getName());
    	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Object userId = null;
		if(session!= null) {
			userId = session.getAttribute("userId");
		}	
		if (userId!=null) {
        	if((int) session.getAttribute("userId") != -1) {   		
        		try {
        			request.getRequestDispatcher("checkout.jsp").forward(request, response);
            	} catch (ServletException se) {
            		logger.log(Level.WARNING, se.getMessage());
            	} catch (IOException e){
            		logger.log(Level.WARNING, e.getMessage());
            	}	
        	}
        } else {
        	try {
        		response.sendRedirect("login.jsp");
        	} catch (IOException e){
        		logger.log(Level.WARNING, e.getMessage());
        	}
        	
        }
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
    	} catch (ServletException se) {
    		logger.log(Level.WARNING, se.getMessage());
    	} catch (IOException e){
    		logger.log(Level.WARNING, e.getMessage());
    	}	
	}

}