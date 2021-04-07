public class Product {
    private int productId;
    private String productName;
    private int ProductCost;

    public Product() {
    }

    public Product(int productId, String productName, int productCost) {
        this.productId = productId;
        this.productName = productName;
        this.ProductCost = productCost;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCost() {
        return ProductCost;
    }

    public void setProductCost(int productCost) {
        ProductCost = productCost;
    }
}
