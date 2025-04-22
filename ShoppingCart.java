
import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        // Check if item already exists in cart
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getId().equals(item.getProduct().getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(String productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void displayCart() {
        System.out.println("\nYour Shopping Cart:");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-5s %-5s %-10s\n", 
                "ID", "Name", "Price", "Size", "Qty", "Total");
        System.out.println("----------------------------------------------------------------");
        
        for (CartItem item : items) {
            System.out.println(item);
        }
        
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%50s $%.2f\n", "Subtotal:", getSubtotal());
        System.out.printf("%50s $%.2f\n", "Tax (10%):", getTax());
        System.out.printf("%50s $%.2f\n", "Total:", getTotal());
        System.out.println("----------------------------------------------------------------");
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public double getTax() {
        return getSubtotal() * 0.10;
    }

    public double getTotal() {
        return getSubtotal() + getTax();
    }

    public void clearCart() {
        items.clear();
    }
}
