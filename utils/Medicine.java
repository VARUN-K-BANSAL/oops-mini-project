package utils;

public abstract class Medicine<T> {
    private String name;
    private String batchNumber;
    private String expiryDate;
    private double price;
    private T category;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBatchNumber() {
        return batchNumber;
    }
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public T getCategory() {
        return category;
    }
    public void setCategory(T category) {
        this.category = category;
    }
    public Medicine(String name, String batchNumber, String expiryDate, double price, T category) {
        this.name = name;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.price = price;
        this.category = category;
    }
}
