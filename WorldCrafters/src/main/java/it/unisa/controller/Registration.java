package it.unisa.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.dao.AdminDAO;
import it.unisa.dao.UserDAO;


@WebServlet("/registration")
public class Registration extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Registration.class.getName());

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		UserDAO userDAO = new UserDAO();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String error = "";
        
        error += checkEmailAvailability(email);
        error += validateField(firstName, "firstName", request, "Inserisci nome valido", "^[a-zA-Z\\s]+$");
        error += validateField(lastName, "lastName", request, "Inserisci cognome valido", "^[a-zA-Z\\s]+$");
        error += validateField(email, "email", request, "Inserisci email valida", "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
             
        if(!isValidPassword(password)) {
        	error += "La password deve contenere almeno 8 caratteri, una lettera maiuscola, una cifra e un simbolo<br>";
        } else {
        	if(!password.equals(confirmPassword)) {
            	error += "Le password non corrispondono<br>";
            }
        }
        
		if (!error.equals("")) {
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
			try {
				dispatcher.forward(request, response);
	    	} catch (ServletException se) {
	    		logger.log(Level.WARNING, se.getMessage());
	    	} catch (IOException e) {
	    		logger.log(Level.WARNING, e.getMessage());
	    	}
			
		} else {

			//Dopo il controllo viene registrato in database
			userDAO.registerUser(firstName, lastName, email, password);  
	        try {
	        	response.sendRedirect("login.jsp");
	    	} catch (IOException e) {
	    		logger.log(Level.WARNING, e.getMessage());
	    	}
		
		}
		
    } 
	
	private boolean isValidPassword(String password) {

	    boolean bool = true;
		if(!password.matches("^(?=.*[A-Z])(?=.*\\\\d)(?=.*[!@#$%^&*]).{8,}$")) {
	        bool = false;
	    }	
		return bool;

	}
	
	private String checkEmailAvailability(String email) {
		
		UserDAO userDAO = new UserDAO();
		AdminDAO adminDAO = new AdminDAO();
		String error = "";
		
		//Controllare che l'email non sia già presente in user o admin
		boolean userValid = userDAO.verifyEmail(email);
		boolean adminValid = adminDAO.verifyEmail(email);
		
		if(!(userValid && adminValid)) {
			error = "Email non disponibile<br>";
		}
		
		return error;
	}
	
	private String validateField(String input, String attribute, HttpServletRequest request, String errorMessage, String regex) {
		
		String error = "";		
		if (input == null || input.trim().equals("") || !Pattern.matches(regex, input)) {
            error = errorMessage + "<br>";
        } else {
            request.setAttribute(attribute, input);
        }	
		return error;	
	}

}
