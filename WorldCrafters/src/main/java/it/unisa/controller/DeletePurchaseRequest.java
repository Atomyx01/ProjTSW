package it.unisa.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import it.unisa.dao.PurchaseDAO;


@WebServlet("/DeletePurchaseRequestServlet")
public class DeletePurchaseRequest extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeletePurchaseRequest.class.getName());
       
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		int purchaseId = -1;
		
		try {
			purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
    	} catch (NumberFormatException e){
    		logger.log(Level.WARNING, e.getMessage());
    	}
		
		purchaseDAO.setDeleteRequestStatus(purchaseId, true);
		
		JsonObject jsonResponse = new JsonObject();
	    jsonResponse.addProperty("success", true);
	    response.setContentType("application/json");
	    
	    try {
	    	response.getWriter().write(jsonResponse.toString());
    	} catch (IOException e){
    		logger.log(Level.WARNING, e.getMessage());
    	}    
	}

}