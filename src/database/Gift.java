package database;

public class Gift {
    private String name;

    private int price;

    private String category;

    public Gift(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
