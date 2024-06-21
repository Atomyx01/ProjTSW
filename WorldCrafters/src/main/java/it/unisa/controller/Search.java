package it.unisa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.unisa.bean.Product;
import it.unisa.dao.ProductDAO;


@WebServlet("/api/search")
public class Search extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Search.class.getName());
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = null;
        HttpSession session = request.getSession();
        List<Product> products;
        
        try {
        	out = response.getWriter();
    	} catch (IOException e){
    		logger.log(Level.WARNING, e.getMessage());
    	}

        // Qui dovresti ottenere la lista di prodotti dal tuo ProductDAO
        ProductDAO productDAO = new ProductDAO();
        
        if (session.getAttribute("isAdmin") != null && (Boolean) session.getAttribute("isAdmin")) {
        	products = productDAO.getAllProductsForAdmin();
		} else {
			products = productDAO.getAllProducts();
		}

        // Converti la lista di prodotti in formato JSON e inviali al client
        Gson gson = new Gson();
        String json = gson.toJson(products);
        
        if(out!=null) {
        	out.print(json);
        }
    }

}
