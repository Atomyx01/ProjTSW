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

@WebServlet(urlPatterns = { "", "/home" })
public class Home extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Home.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductDAO productDAO = new ProductDAO();
		List<Product> products;
		HttpSession session = request.getSession();

		if (session.getAttribute("isAdmin") != null && (Boolean) session.getAttribute("isAdmin")) {
			products = productDAO.getFavoritesProductsForAdmin();
		} else {
			// Chiama il metodo getFavoritesProducts() per ottenere la lista di prodotti
			products = productDAO.getFavoritesProducts();

		}
		request.setAttribute("products", products);

		try {
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (ServletException se) {
			logger.log(Level.WARNING, se.getMessage());
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (ServletException se) {
			logger.log(Level.WARNING, se.getMessage());
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

}
