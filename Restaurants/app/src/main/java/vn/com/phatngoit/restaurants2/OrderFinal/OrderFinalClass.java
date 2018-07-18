package vn.com.phatngoit.restaurants2.OrderFinal;

public class OrderFinalClass {
    private String nameOfDish;
    private Integer quantity;
    private Double price;

    public OrderFinalClass() {
    }

    public OrderFinalClass(String nameOfDish, Integer quantity, Double price) {

        this.nameOfDish = nameOfDish;
        this.quantity = quantity;
        this.price = price;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public void setNameOfDish(String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
