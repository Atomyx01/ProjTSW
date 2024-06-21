package it.unisa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unisa.bean.Category;
import it.unisa.bean.Product;
import it.unisa.utils.DriverManagerConnectionPool;

public class CartDAO {
	
	private static final Logger logger = Logger.getLogger(CartDAO.class.getName());
	
	public int getCartByUserId(int userId) {
			
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    int id=-1;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id FROM cart WHERE (userId = ?);";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, userId);
	        resultSet = statement.executeQuery();

	        if(resultSet.next()) {
	        	id = resultSet.getInt("id");
	        }
	        
	    } catch (SQLException e) {
	        logger.log(Level.WARNING, e.getMessage());
	    } finally {
	        try {
	        	if (statement != null) {
	                statement.close();
	            }
	    		if (resultSet != null) {
	                resultSet.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            logger.log(Level.WARNING, e.getMessage());
	        }
	    }

	    return id;
	
	}
	
	

	public int setCart(int userId) {
			
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    int generatedId = -1; // Inizializziamo con un valore di errore

	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "INSERT INTO cart (userId) VALUES (?);";

	        // Passiamo il flag Statement.RETURN_GENERATED_KEYS al PreparedStatement
	        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        statement.setInt(1, userId);
	        statement.executeUpdate();

	        // Otteniamo l'ID generato
	        resultSet = statement.getGeneratedKeys();
	        if (resultSet.next()) {
	            generatedId = resultSet.getInt(1);
	        }

	        connection.commit();
	    } catch (SQLException e) {
	        logger.log(Level.WARNING, e.getMessage());
	    } finally {
	        try {
	        	if (statement != null) {
	                statement.close();
	            }
	    		if (resultSet != null) {
	                resultSet.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            logger.log(Level.WARNING, e.getMessage());
	        }
	    }

	    return generatedId;
	}
	
	
	public List<Product> getAllCartProducts(int cartId) {
		
		List<Product> productList = new ArrayList<>();
		
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query =  "SELECT P.id, P.name, P.price, P.seller, P.imgSrc, P.quantity, I.selectedQuantity, P.favorites, P.ListingDate, P.description, P.category " +
	                		"FROM cart C " +
	                		"JOIN cart_item I ON C.id = I.cartId " +
	                		"JOIN product P ON I.productId = P.id " +
	                		"WHERE C.id = ?";
	        
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, cartId);
	        resultSet = statement.executeQuery();

	        while(resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble("price");
	            String seller = resultSet.getString("seller");
	            String imgSrc = resultSet.getString("imgSrc");
	            int quantity = resultSet.getInt("quantity");
	            int selectedQuantity = resultSet.getInt("selectedQuantity");
	            int favorites = resultSet.getInt("favorites");
	            Date listingDate = resultSet.getDate("listingDate");
	            String description = resultSet.getString("description");
	            Category category = Category.valueOf(resultSet.getString("category"));
	            
	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setSelectedQuantity(selectedQuantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            
	            productList.add(product);
	        }
	        
	    } catch (SQLException e) {
	        logger.log(Level.WARNING, e.getMessage());
	    } finally {
	        try {
	        	if (statement != null) {
	                statement.close();
	            }
	    		if (resultSet != null) {
	                resultSet.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            logger.log(Level.WARNING, e.getMessage());
	        }
	    }

	    return productList;
		
	}
	
	public void deleteCart(int cartId) {
		
		Connection connection = null;
	    PreparedStatement statement = null;


	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "DELETE FROM cart WHERE id = ?;";

	        // Passiamo il flag Statement.RETURN_GENERATED_KEYS al PreparedStatement
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, cartId);
	        statement.executeUpdate();

	        connection.commit();
	    } catch (SQLException e) {
	        logger.log(Level.WARNING, e.getMessage());
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            logger.log(Level.WARNING, e.getMessage());
	        }
	    }

	}
		
	
}
