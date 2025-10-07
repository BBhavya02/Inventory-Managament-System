package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductValidationTest {

    @Test
    public void testValidateProductCreation() {
        Product p = new Product(1, "Mouse", "Electronics", 20, 500.0);

        assertEquals(1, p.getProductId());
        assertEquals("Mouse", p.getProductName());
        assertEquals("Electronics", p.getProductType());
        assertEquals(20, p.getAvailableQty());
        assertEquals(500, p.getPrice());
    }

    @Test
    public void testInvalidPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(2, "Keyboard", "Electronics", 5, -200.0);
        });
    }
}

