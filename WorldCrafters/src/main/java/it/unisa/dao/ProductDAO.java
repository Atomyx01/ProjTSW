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

public class ProductDAO {
	
	
	private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

	private static final String PRICE = "price";
	private static final String SELLER = "seller";
	private static final String IMG_SRC = "imgSrc";
	private static final String CATEGORY = "category";
	private static final String QUANTITY = "quantity";
	private static final String FAVORITES = "favorites";
	private static final String LISTING_DATE = "listingDate";
	private static final String DESCRIPTION = "description";
	private static final String STATUS = "status";
	
	
	public List<Product> getAllProducts() {
        
        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE quantity>0 AND status='true'";
	        statement = connection.prepareStatement(query);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }

    public List<Product> getFavoritesProducts() {
        
        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE quantity>0 AND status='true' ORDER BY favorites DESC";
	        statement = connection.prepareStatement(query);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }

    public List<Product> getProductsByCategory(String chosenCategory) { 

        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE quantity>0 AND status='true' AND (category=? OR category=? OR category=? OR category=?)";
	        statement = connection.prepareStatement(query);
	        
	        if (chosenCategory.equals("arte")) {
	            statement.setString(1, "fotografia");
	            statement.setString(2, "pittura");
	            statement.setString(3, "scultura");
	            statement.setString(4, "vetro");
	        } else if (chosenCategory.equals("abbigliamento")) {
	            statement.setString(1, "abbigliamento_uomo");
	            statement.setString(2, "abbigliamento_donna");
	            statement.setString(3, "abbigliamento_bambino");
	            statement.setString(4, "borse");
	        } else if (chosenCategory.equals("gioielli")) {
	            statement.setString(1, "anelli");
	            statement.setString(2, "bracciali");
	            statement.setString(3, "collane");
	            statement.setString(4, "orecchini");
	        } else if (chosenCategory.equals("intrattenimento")) {
	            statement.setString(1, "cinema");
	            statement.setString(2, "libri");
	            statement.setString(3, "musica");
	            statement.setString(4, "giochi");
	        } else {
	            statement.setString(1, chosenCategory);
	            statement.setString(2, "0");
	            statement.setString(3, "0");
	            statement.setString(4, "0");
	        }
	        
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }
    
    public Product getProductById(int productId) {
        
        Product product = new Product();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE id = ?";
	        statement = connection.prepareStatement(query);
	        
	        statement.setInt(1, productId);
	        
	        resultSet = statement.executeQuery();

	        if(resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

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

	    return product;
    }
    
    public List<Product> getProductsByName(String searchTerm) { 

        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE quantity>0 AND status='true' AND name LIKE ?";
	        statement = connection.prepareStatement(query);

	        statement.setString(1, searchTerm);
	        
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }
    
    public List<Product> getProductsBySearch(String search) {
        
        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE quantity>0 AND status='true' AND (LOWER(name) LIKE ? OR LOWER(category) LIKE ? OR LOWER(description) LIKE ?)";
	        statement = connection.prepareStatement(query);
	        String searchParam = "%" + search.toLowerCase() + "%";
	        statement.setString(1, searchParam);
	        statement.setString(2, searchParam);
	        statement.setString(3, searchParam);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }
    
    public void sellProduct(int id, int selectedQuantity) {
        
        Connection connection = null;
	    PreparedStatement statement = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "UPDATE product SET quantity=quantity-?, favorites=favorites+? WHERE id=?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, selectedQuantity);
	        statement.setInt(2, selectedQuantity);
	        statement.setInt(3, id);
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

	public List<Product> getProductsByPurchaseId(int purchaseId) {
		
		List<Product> products = new ArrayList<>();
	    
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query =  "SELECT P.id, P.name, P.price, P.seller, P.imgSrc, P.quantity, P.favorites, P.ListingDate, P.description, P.category, P.status " +
	    			"FROM product P " +
	    			"JOIN purchase_item ON P.id = purchase_item.productId " +
	    			"JOIN purchase ON  purchase_item.purchaseId = purchase.id " +
	    			"WHERE purchase.id = ?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, purchaseId);
	        resultSet = statement.executeQuery();
	
	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);
	
	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);
	
	            products.add(product);
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
	
	    return products;
	    
	}
	
	//ADMIN///////////////////////////////////////////////////////////////////////////
	
	public List<Product> getAllProductsForAdmin() {
        
        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product";
	        statement = connection.prepareStatement(query);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }

    public List<Product> getFavoritesProductsForAdmin() {
        
        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product ORDER BY favorites DESC";
	        statement = connection.prepareStatement(query);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }

    public List<Product> getProductsByCategoryForAdmin(String chosenCategory) { 

        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE (category=? OR category=? OR category=? OR category=?)";
	        statement = connection.prepareStatement(query);
	        
	        if (chosenCategory.equals("arte")) {
	            statement.setString(1, "fotografia");
	            statement.setString(2, "pittura");
	            statement.setString(3, "scultura");
	            statement.setString(4, "vetro");
	        } else if (chosenCategory.equals("abbigliamento")) {
	            statement.setString(1, "abbigliamento_uomo");
	            statement.setString(2, "abbigliamento_donna");
	            statement.setString(3, "abbigliamento_bambino");
	            statement.setString(4, "borse");
	        } else if (chosenCategory.equals("gioielli")) {
	            statement.setString(1, "anelli");
	            statement.setString(2, "bracciali");
	            statement.setString(3, "collane");
	            statement.setString(4, "orecchini");
	        } else if (chosenCategory.equals("intrattenimento")) {
	            statement.setString(1, "cinema");
	            statement.setString(2, "libri");
	            statement.setString(3, "musica");
	            statement.setString(4, "giochi");
	        } else {
	            statement.setString(1, chosenCategory);
	            statement.setString(2, "0");
	            statement.setString(3, "0");
	            statement.setString(4, "0");
	        }
	        
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }
    
    public List<Product> getProductsByNameForAdmin(String searchTerm) { 

        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE name LIKE ?";
	        statement = connection.prepareStatement(query);

	        statement.setString(1, searchTerm);
	        
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }
    
    public List<Product> getProductsBySearchForAdmin(String search) {
        
        List<Product> products = new ArrayList<>();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

        try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "SELECT id, name, price, seller, imgSrc, category, quantity, favorites, listingDate, description, status FROM product WHERE (LOWER(name) LIKE ? OR LOWER(category) LIKE ? OR LOWER(description) LIKE ?)";
	        statement = connection.prepareStatement(query);
	        String searchParam = "%" + search.toLowerCase() + "%";
	        statement.setString(1, searchParam);
	        statement.setString(2, searchParam);
	        statement.setString(3, searchParam);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String name = resultSet.getString("name");
	            double price = resultSet.getDouble(PRICE);
	            String seller = resultSet.getString(SELLER);
	            String imgSrc = resultSet.getString(IMG_SRC);
	            Category category = Category.valueOf(resultSet.getString(CATEGORY).toUpperCase());
	            int quantity = resultSet.getInt(QUANTITY);
	            int favorites = resultSet.getInt(FAVORITES);
	            Date listingDate = resultSet.getDate(LISTING_DATE);
	            String description = resultSet.getString(DESCRIPTION);
	            Boolean status = resultSet.getBoolean(STATUS);

	            Product product = new Product();
	            product.setId(id);
	            product.setName(name);
	            product.setPrice(price);
	            product.setSeller(seller);
	            product.setImgSrc(imgSrc);
	            product.setCategory(category);
	            product.setQuantity(quantity);
	            product.setFavorites(favorites);
	            product.setListingDate(listingDate);
	            product.setDescription(description);
	            product.setStatus(status);

	            products.add(product);
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

	    return products;
    }
    
    public void changeProductStatus(int productId) {
		
		Connection connection = null;
	    PreparedStatement statement = null;
	
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "UPDATE product " +
	                "SET status = CASE " +
	                "    WHEN status = 'true' THEN 'false' " +
	                "    WHEN status = 'false' THEN 'true' " +
	                "    ELSE status " +
	                "END " +
	                "WHERE id = ?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, productId);
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
    
    public void editProduct(Product product) {
    	
    	int id = product.getId();
		String name = product.getName();
        double price = product.getPrice();
        String seller = product.getSeller();
        int quantity = product.getQuantity();
        Date listingDate = (Date) product.getListingDate();
        String description = product.getDescription();
        Category category = product.getCategory();
        boolean status = product.getStatus();
        String imgSrc = product.getImgSrc();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "UPDATE product SET name=?,price=?,seller=?,quantity=?,listingDate=?,description=?,category=?,status=?,imgSrc=? WHERE id=?;";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, name);
	        statement.setDouble(2, price);
	        statement.setString(3, seller);
	        statement.setInt(4, quantity);
	        statement.setString(5, String.valueOf(listingDate));
	        statement.setString(6, description);
	        statement.setString(7, String.valueOf(category));
	        statement.setString(8, String.valueOf(status));
	        statement.setString(9, imgSrc);
	        statement.setInt(10, id);
	        
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
    
    public int addProduct(Product product) {
    	
    	ResultSet resultSet = null;
	    int generatedId = -1;
    	
		String name = product.getName();
        double price = product.getPrice();
        String seller = product.getSeller();
        int quantity = product.getQuantity();
        Date listingDate = (Date) product.getListingDate();
        String description = product.getDescription();
        Category category = product.getCategory();
        boolean status = product.getStatus();
        String imgSrc = product.getImgSrc();
        
        Connection connection = null;
	    PreparedStatement statement = null;
	
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String query = "INSERT INTO product (name, price, seller, quantity, listingDate, description, category, status, imgSrc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        statement.setString(1, name);
	        statement.setDouble(2, price);
	        statement.setString(3, seller);
	        statement.setInt(4, quantity);
	        statement.setString(5, String.valueOf(listingDate));
	        statement.setString(6, description);
	        statement.setString(7, String.valueOf(category));
	        statement.setString(8, String.valueOf(status));
	        statement.setString(9, imgSrc);
	        
	        statement.executeUpdate();
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
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            logger.log(Level.WARNING, e.getMessage());
	        }
	    }
	    
	    return generatedId;
    }

}

