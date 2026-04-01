package product.productToCart;

import product.Product;

public class ProductToCart {
    private Product product;
    private int quantity;

    public ProductToCart(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity can't be lower than 0");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{ id " + product.getId() + "; " + product.getPrice() + ", quantity " + quantity + "}";
    }
}
