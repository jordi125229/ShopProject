
import manager.CartManager;
import money.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import product.Computer;
import product.ProductToCart;
import repository.Cart;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiscountTest {

    @Mock
    private Cart cartRepository;

    @InjectMocks
    private CartManager cartManager;

    private Computer computer;

    void setUpProductToTests() {
        computer = Computer.builder()
                .id("001")
                .name("Test computer")
                .price(Money.of("5000"))
                .quantity(10).build();
    }

    @Test
    void shouldApplyDiscountWhenAboveThreshold() {
        // Arrange
        int quantityAboveThreshold = 4;
        setUpProductToTests();
        ProductToCart productToCart = new ProductToCart(computer, quantityAboveThreshold);
        when(cartRepository.findAll()).thenReturn(List.of(productToCart));

        // Act
        Money result = cartManager.calculateTotalPrice();

        // Assert
        assertEquals(new BigDecimal("17000.00"), result.getAmount());
    }

    @Test
    void shouldNotApplyDiscountWhenBelowThreshold() {
        // Arrange:
        int quantityBelowThreshold = 2;
        setUpProductToTests();
        ProductToCart item = new ProductToCart(computer, quantityBelowThreshold);

        when(cartRepository.findAll()).thenReturn(List.of(item));

        // Act
        Money result = cartManager.calculateTotalPrice();

        // Assert
        assertEquals(new BigDecimal("10000"), result.getAmount());
    }
}
