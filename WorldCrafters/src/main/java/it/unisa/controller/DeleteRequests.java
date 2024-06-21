package it.unisa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.bean.Payment;
import it.unisa.bean.Product;
import it.unisa.bean.Purchase;
import it.unisa.dao.PaymentDAO;
import it.unisa.dao.ProductDAO;
import it.unisa.dao.PurchaseDAO;


@WebServlet("/delete_requests")
public class DeleteRequests extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeleteRequests.class.getName());


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
	    ProductDAO productDAO = new ProductDAO();
		PurchaseDAO purchaseDAO = new PurchaseDAO();
	    PaymentDAO paymentDAO = new PaymentDAO();
	    Map<Integer, List<Product>> productsByPurchase = new HashMap<>();
	    
	    List<Purchase> purchasesWithDelReq = purchaseDAO.getPurchasesWithDeleteRequest();
    	List<Payment> payments = new ArrayList<>();
	    
	    
	    for(Purchase purchase : purchasesWithDelReq) {
	    	//Prelevo il metodo di pagamento
	    	int paymentId = purchase.getPaymentId();
	    	Payment payment = paymentDAO.getPaymentByPaymentId(paymentId);
	    	payments.add(payment);
	    	
	    	//Prelevo i prodotti associati all'ordine
	    	List<Product> products = productDAO.getProductsByPurchaseId(purchase.getId());
	    	productsByPurchase.put(purchase.getId(), products);
	    			
	    }
		     
	    request.setAttribute("purchasesWithDelReq", purchasesWithDelReq);
		request.setAttribute("payments", payments);
		request.setAttribute("productsByPurchase", productsByPurchase);
			
		try {
			request.getRequestDispatcher("delete_requests.jsp").forward(request, response);
    	} catch (ServletException se) {
    		logger.log(Level.WARNING, se.getMessage());
    	} catch (IOException e) {
    		logger.log(Level.WARNING, e.getMessage());
    	}
	    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
