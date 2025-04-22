
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingSystem {
    private List<Category> categories;
    private ShoppingCart cart;
    private Scanner scanner;

    public ShoppingSystem() {
        this.categories = new ArrayList<>();
        this.cart = new ShoppingCart();
        this.scanner = new Scanner(System.in);
        initializeProducts();
    }

    private void initializeProducts() {
        // Clothing Category
        Category clothing = new Category("Clothing");
        
        // Men's Clothing
        clothing.addProduct(new Product("M001", "Men's Jeans", 49.99, "32"));
        clothing.addProduct(new Product("M002", "Men's Shirt", 29.99, "L"));
        clothing.addProduct(new Product("M003", "Men's T-Shirt", 19.99, "M"));
        clothing.addProduct(new Product("M004", "Men's Pants", 39.99, "34"));
        clothing.addProduct(new Product("M005", "Men's Jacket", 79.99, "XL"));

        // Women's Clothing
        clothing.addProduct(new Product("W001", "Women's Jeans", 44.99, "28"));
        clothing.addProduct(new Product("W002", "Women's Blouse", 34.99, "M"));
        clothing.addProduct(new Product("W003", "Women's T-Shirt", 24.99, "S"));
        clothing.addProduct(new Product("W004", "Women's Skirt", 29.99, "M"));
        clothing.addProduct(new Product("W005", "Women's Dress", 59.99, "L"));

        // Electronics Category
        Category electronics = new Category("Electronics");
        electronics.addProduct(new Product("E001", "Smartphone", 699.99, "N/A"));
        electronics.addProduct(new Product("E002", "Laptop", 999.99, "N/A"));
        electronics.addProduct(new Product("E003", "Headphones", 149.99, "N/A"));
        electronics.addProduct(new Product("E004", "Smart Watch", 199.99, "N/A"));
        electronics.addProduct(new Product("E005", "Tablet", 349.99, "N/A"));

        // Sports Category
        Category sports = new Category("Sports");
        sports.addProduct(new Product("S001", "Running Shoes", 89.99, "9"));
        sports.addProduct(new Product("S002", "Yoga Mat", 24.99, "N/A"));
        sports.addProduct(new Product("S003", "Dumbbells", 39.99, "N/A"));
        sports.addProduct(new Product("S004", "Tennis Racket", 79.99, "N/A"));
        sports.addProduct(new Product("S005", "Basketball", 29.99, "N/A"));

        categories.add(clothing);
        categories.add(electronics);
        categories.add(sports);
    }

    public void displayCategories() {
        System.out.println("\nAvailable Categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
    }

    public void displayProducts(String categoryName) {
        Category category = categories.stream()
                .filter(c -> c.getName().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElse(null);

        if (category == null) {
            System.out.println("Category not found!");
            return;
        }

        System.out.println("\nProducts in " + category.getName() + ":");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-5s\n", "ID", "Name", "Price", "Size");
        System.out.println("----------------------------------------------------------------");
        
        for (Product product : category.getProducts()) {
            System.out.println(product);
        }
    }

    public void addToCart() {
        System.out.print("Enter product ID to add to cart: ");
        String productId = scanner.nextLine();
        
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = findProductById(productId);
        if (product != null) {
            cart.addItem(new CartItem(product, quantity));
            System.out.println(quantity + " x " + product.getName() + " added to cart!");
        } else {
            System.out.println("Product not found!");
        }
    }

    private Product findProductById(String id) {
        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getId().equalsIgnoreCase(id)) {
                    return product;
                }
            }
        }
        return null;
    }

    public void run() {
        System.out.println("Welcome to the Shopping System!");
        
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Browse Categories");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    browseCategories();
                    break;
                case 2:
                    cart.displayCart();
                    cartManagement();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    System.out.println("Thank you for shopping with us!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void browseCategories() {
        displayCategories();
        System.out.print("Enter category number to view products (0 to go back): ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (categoryChoice == 0) return;
        
        if (categoryChoice > 0 && categoryChoice <= categories.size()) {
            Category selectedCategory = categories.get(categoryChoice - 1);
            displayProducts(selectedCategory.getName());
            
            System.out.print("Would you like to add items to cart? (y/n): ");
            String addChoice = scanner.nextLine();
            
            if (addChoice.equalsIgnoreCase("y")) {
                addToCart();
            }
        } else {
            System.out.println("Invalid category number!");
        }
    }

    private void cartManagement() {
        System.out.println("\nCart Options:");
        System.out.println("1. Remove Item");
        System.out.println("2. Continue Shopping");
        System.out.println("3. Checkout");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                System.out.print("Enter product ID to remove: ");
                String productId = scanner.nextLine();
                cart.removeItem(productId);
                System.out.println("Item removed from cart.");
                break;
            case 2:
                // Continue shopping - will return to main menu
                break;
            case 3:
                checkout();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void checkout() {
        if (cart.getSubtotal() == 0) {
            System.out.println("Your cart is empty!");
            return;
        }
        
        cart.displayCart();
        
        System.out.print("\nProceed to checkout? (y/n): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("y")) {
            System.out.println("\nThank you for your purchase!");
            System.out.println("Your order has been placed successfully.");
            cart.clearCart();
        }
    }

    public static void main(String[] args) {
        ShoppingSystem system = new ShoppingSystem();
        system.run();
    }
}