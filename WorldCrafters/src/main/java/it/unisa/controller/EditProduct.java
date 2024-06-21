package it.unisa.controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.bean.Product;
import it.unisa.dao.ProductDAO;
import it.unisa.bean.Category;


@WebServlet("/editProduct")
public class EditProduct extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String seller = request.getParameter("seller");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date listingDate = Date.valueOf(request.getParameter("listingDate"));
        String description = request.getParameter("description");
        Category category = Category.valueOf(request.getParameter("category"));
        Boolean status = Boolean.valueOf(request.getParameter("status"));
        String imgSrc = request.getParameter("imgSrc");
        
        //TODO fare check dei campi
        

        ProductDAO productDAO = new ProductDAO();
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setSeller(seller);
        product.setQuantity(quantity);
        product.setListingDate(listingDate);
        product.setDescription(description);
        product.setCategory(category);
        product.setStatus(status);
        product.setImgSrc(imgSrc);
        
        productDAO.editProduct(product);
        
		response.sendRedirect("product?id=" + id);
			
	}

}
