package database;

/**
 * Class defines an objects
 * that store data about gifts.
 */
public final class Gift {
    private String name;

    private double price;

    private String category;

    public Gift(final String name, final double price, final String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

}
