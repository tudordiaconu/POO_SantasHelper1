package michelaneous;

import enums.Category;

public class Gift {
    private String productName;
    private Double price;
    private Category category;

    public Gift() {
        this.productName = null;
        this.price = (double) 0;
        this.category = null;
    }

    public Gift(final String productName, final Double price, final Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }


    /** getter for the product name */
    public String getProductName() {
        return productName;
    }


    /** setter for the product name */
    public void setProductName(final String productName) {
        this.productName = productName;
    }


    /** getter for the price */
    public Double getPrice() {
        return price;
    }


    /** setter for the price */
    public void setPrice(final Double price) {
        this.price = price;
    }


    /** getter for the category */
    public Category getCategory() {
        return category;
    }


    /** setter for the category */
    public void setCategory(final Category category) {
        this.category = category;
    }
}
