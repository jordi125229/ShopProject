
import exceptions.NoProductException;
import manager.CartManager;
import manager.ProductManager;
import money.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import product.Computer;
import repository.Cart;
import repository.ProductRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductsTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    Cart cart;

    @InjectMocks
    ProductManager productManager;

    @InjectMocks
    CartManager cartManager;

    Computer testComputer;

    void setUpOfDevice() {
        Money price = Money.of("4000");
        testComputer = productManager.createComputer("1", "computer", price, 10);
    }

    @Test
    void shouldConfigurateComputer() {
        // Arrange
        setUpOfDevice();

        // Act
        testComputer.configuration("testProcessor", 16);

        // Assert
        assertEquals(16, testComputer.getRam());
        assertEquals("testProcessor", testComputer.getProcessor());
    }

    @Test
    void shouldAddProductToRepository() {
        // Arrange
        setUpOfDevice();

        // Assert
        verify(productRepository).add(testComputer);
    }

    @Test
    void shouldAddProductToCartInOrder() {
        // Arrange
        setUpOfDevice();
        int initialDeviceQuantity = testComputer.getQuantity();
        int randomQuantity = 1;
        when(productRepository.findProductById(testComputer.getId())).thenReturn(Optional.of(testComputer));

        // Act
        cartManager.addProductToCart(testComputer.getId(), randomQuantity);

        // Assert
        InOrder inOrder = inOrder(productRepository, cart);
        inOrder.verify(productRepository).findProductById(testComputer.getId());
        inOrder.verify(cart).addProduct(testComputer, randomQuantity);

        assertThat(testComputer.getQuantity()).isEqualTo(initialDeviceQuantity - randomQuantity);
    }

    @Test
    void shouldClearCart() {
        // Arrange
        setUpOfDevice();
        Cart cart = new Cart();
        cart.addProduct(testComputer, 1);

        // Act
        cart.clear();

        // Assert
        assertTrue(cart.findAll().isEmpty());
    }

    @Test
    void shouldCheckIfThrowsExceptionWhenQuantityIsWrong() {
        // Arrange
        setUpOfDevice();
        int randomQuantity = 11;
        when(productRepository.findProductById(testComputer.getId())).thenReturn(Optional.of(testComputer));

        // Assert
        assertThrows(NoProductException.class, () ->cartManager.addProductToCart(testComputer.getId(), randomQuantity));
    }
}
