package it.unisa.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.bean.Product;
import it.unisa.dao.CartDAO;
import it.unisa.dao.CartItemDAO;
import it.unisa.dao.PaymentDAO;
import it.unisa.dao.ProductDAO;
import it.unisa.dao.PurchaseDAO;
import it.unisa.dao.PurchaseItemDAO;


@WebServlet("/CheckoutForm")
public class CheckoutForm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(CheckoutForm.class.getName());
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");	
		try {
			dispatcher.forward(request, response);
        } catch (ServletException se) {
        	logger.log(Level.WARNING, se.getMessage());
    	} catch (IOException e) {
    		logger.log(Level.WARNING, e.getMessage());
    	}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Ottenere i valori dei campi del modulo
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zipCode");
        String accountholder = request.getParameter("accountholder");
        String cardNumber = request.getParameter("cardNumber");
        String expMonth = request.getParameter("expMonth");
        String expYear = request.getParameter("expYear");
        String cvv = request.getParameter("cvv");
        String error = "";
        String wordsRegex = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";

        error += validateField(fullName, "fullName", request, "Inserisci nome valido", wordsRegex);
        error += validateField(accountholder, "accountholder", request, "Inserisci nome valido", wordsRegex);
        error += validateField(city, "city", request, "Inserisci città valida", wordsRegex);
        error += validateField(state, "state", request, "Inserisci stato valido", wordsRegex);
        error += validateField(email, "email", request, "Inserisci email valida", "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
        error += validateField(address, "address", request, "Inserisci indirizzo valido", "^[A-Za-z\\s]+\\s\\d+$");
        error += validateField(zipCode, "zipCode", request, "Inserisci codice postale valido", "^\\d{5}$");
        error += validateField(cardNumber, "cardNumber", request, "Inserisci numero carta valido", "^\\d{4}-?\\d{4}-?\\d{4}-?\\d{4}$");
        error += validateField(expMonth, "expMonth", request, "Inserisci mese valido", "^(0[1-9]|1[0-2])$");
        error += validateField(expYear, "expYear", request, "Inserisci anno valido", "^\\d{4}$");
        error += validateField(cvv, "cvv", request, "Inserisci cvv valido", "^\\d{3}$");

        if (!error.equals("")) {
            request.setAttribute("error", error);     
            try {
            	request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            } catch (ServletException se) {
            	logger.log(Level.WARNING, se.getMessage());
        	} catch (IOException e) {
        		logger.log(Level.WARNING, e.getMessage());
        	}
        } else {
            // I campi sono validi, puoi inviare i dati al server
        	HttpSession session = request.getSession();
        	int userId = (int) session.getAttribute("userId");
        	@SuppressWarnings("unchecked")
        	List<Product> productList = (List<Product>) session.getAttribute("productList");
        	ProductDAO productDAO = new ProductDAO();
        	double amount = 0.0;
        	
        	//Svuoto il carrello
        	emptyCart(userId, productList);
        	
        	//Aggiorno quantity e favorites di product
        	for(Product item : productList) {
        		productDAO.sellProduct(item.getId(),item.getSelectedQuantity());
        		amount += item.getSelectedQuantity() * item.getPrice();
        	}
        	
        	//Aggiungo il metodo di pagamento
        	int generatedPaymentId = createPayment(expMonth, expYear, cvv, amount, userId, accountholder, cardNumber);
        	
        	//Creo un purchase
        	long currentTimeMillis = System.currentTimeMillis();
            Date currentDate = new Date(currentTimeMillis);
            Time currentTime = new Time(currentTimeMillis);
            
            PurchaseDAO purchaseDAO = new PurchaseDAO();
            int generatedPurchaseId =  purchaseDAO.setPurchase(currentDate, currentTime, amount, userId, generatedPaymentId, fullName, address, city, state, zipCode);
        	
        	//Aggiungo gli orderItem nel db e li collego all'order
        	PurchaseItemDAO purchaseItemDAO = new PurchaseItemDAO();
        	for(Product item : productList) {
        		purchaseItemDAO.setOrderItem(item.getSelectedQuantity(), item.getPrice(), item.getId(), generatedPurchaseId);
        	}
        	

            session.removeAttribute("productList");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkout-success.jsp");      
            try {
            	dispatcher.forward(request, response);
            } catch (ServletException se) {
            	logger.log(Level.WARNING, se.getMessage());
        	} catch (IOException e) {
        		logger.log(Level.WARNING, e.getMessage());
        	}
        }
        
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
	
	private void emptyCart(int userId, List<Product> productList) {
		
		//Elimino i cartItem associati al cart dell'utente dal db
    	CartItemDAO cartItemDAO = new CartItemDAO();
    	CartDAO cartDAO = new CartDAO();
    	ProductDAO productDAO = new ProductDAO();
    	int cartId = cartDAO.getCartByUserId(userId);
    	
    	for(Product item : productList) {
    		productDAO.sellProduct(item.getId(),item.getSelectedQuantity());
    		cartItemDAO.deleteCartItem(cartId, item.getId());
    	}
    	
    	//Elimino il cart dell'utente dal db
    	cartDAO.deleteCart(cartId);
	}
	
	private int createPayment(String expMonth, String expYear, String cvv, double amount, int userId, String accountholder, String cardNumber) {
		
		//Creo un payment
    	long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        Time currentTime = new Time(currentTimeMillis);
    	
    	PaymentDAO paymentDAO = new PaymentDAO();
    	int expMonthInt = -1;
    	int expYearInt = -1;
    	int cvvInt = -1;
    	try {
    		expMonthInt = Integer.parseInt(expMonth);
    		expYearInt = Integer.parseInt(expYear);
    		cvvInt = Integer.parseInt(cvv);
    	} catch (NumberFormatException e) {
    		logger.log(Level.WARNING, e.getMessage());
    	}
    	return paymentDAO.setPayment(currentDate, currentTime, amount, userId, accountholder, cardNumber, expMonthInt, expYearInt, cvvInt);
		
	}
	

}
