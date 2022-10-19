package lab3_kre17;

public class ElectricStove {
    
    int id;
    String model;
    double price;
    String color;
    
    public ElectricStove() {
        this.id = 0;
        this.model = "";
        this.price = 0.0;
        this.color = "";
    }
    
    public ElectricStove(String model, double price, String color) {
        this.id = 0;
        this.model = model;
        this.price = price;
        this.color = color;
    }
    
    public int getId() {
        return id;
    }
    
    public String getModel() {
        return model;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return String.format("Модель = %s, цена = %f, цвет = %s", model, price, color);
    }
}
