package DAO;

import Models.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class ProductDAOTest {

    private static ProductDAOImpl DAO;

    @BeforeAll
    static void setup() {
        DAO = new ProductDAOImpl();
    }

    @Test
    public void testAddProduct() throws SQLException {
        // Create a new product
        Product product = new Product(201, "Mouse", "Electronics", 10, 300.0);

        // Add the product to the database
        DAO.AddProduct(product);

        // Retrieve the product by ID
        Product retrievedProduct = DAO.getProductById(201);

        // Validate the data
        assertNotNull(retrievedProduct, "Product should not be null");
        assertEquals(201, retrievedProduct.getProductId(), "Product ID should match");
        assertEquals("Mouse", retrievedProduct.getProductName(), "Product name should match");
        assertEquals("Electronics", retrievedProduct.getProductType(), "Product category should match");
        assertEquals(10, retrievedProduct.getAvailableQty(), "Product quantity should match");
        assertEquals(300.0, retrievedProduct.getPrice(), "Product price should match");
    }
}
