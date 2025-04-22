class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-15s $%-8.2f %-5s %-3d $%-8.2f", 
                product.getId(), product.getName(), product.getPrice(), 
                product.getSize(), quantity, getTotalPrice());
    }
}