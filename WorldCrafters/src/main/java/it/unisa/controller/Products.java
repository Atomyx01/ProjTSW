package it.unisa.controller;

import java.io.IOException;
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
import it.unisa.dao.ProductDAO;



@WebServlet(urlPatterns = {"/products", "/search"})
// @WebServlet("/products")
public class Products extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Products.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");
        String search = request.getParameter("search");
        ProductDAO productDAO = new ProductDAO();
        HttpSession session = request.getSession();
        List<Product> products;

        if (session.getAttribute("isAdmin") != null && (Boolean) session.getAttribute("isAdmin")) {
        	
        	if (category != null) {
                // Se è presente il parametro "category", ottieni prodotti in base alla categoria
                products = productDAO.getProductsByCategoryForAdmin(category);
            } else if (search != null) {
                // Se è presente il parametro "search", ottieni prodotti in base al testo di ricerca
                products = productDAO.getProductsBySearchForAdmin(search);
            } else {
                // Se nessun parametro è presente, ottieni tutti i prodotti
                products = productDAO.getAllProductsForAdmin();
            }
        } else {
        	
        	if (category != null) {
                // Se è presente il parametro "category", ottieni prodotti in base alla categoria
                products = productDAO.getProductsByCategory(category);
            } else if (search != null) {
                // Se è presente il parametro "search", ottieni prodotti in base al testo di ricerca
                products = productDAO.getProductsBySearch(search);
            } else {
                // Se nessun parametro è presente, ottieni tutti i prodotti
                products = productDAO.getAllProducts();
            }
        }
        
        request.setAttribute("products", products);
        try {
        	request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (ServletException se) {
        	logger.log(Level.WARNING, se.getMessage());
    	} catch (IOException e) {
    		logger.log(Level.WARNING, e.getMessage());
    	}
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

}