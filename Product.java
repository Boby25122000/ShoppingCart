class Product {
    private String id;
    private String name;
    private double price;
    private String size; // For clothing items

    public Product(String id, String name, double price, String size) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getSize() { return size; }

    @Override
    public String toString() {
        return String.format("%-5s %-15s $%-8.2f %-5s", id, name, price, size);
    }
}
