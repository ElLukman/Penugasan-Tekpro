// File: Clothing.java

public class Clothing extends AbstractItem<String, String, Double, ProductCategory> {
    private String size;
    private String color;
    private String material;

    public Clothing(String id, String name, Double price, int quantity,
                    String size, String color, String material) 
    {
        super(id, name, price, quantity, ProductCategory.CLOTHING);
        this.size = size;
        this.color = color;
        this.material = material;
    }

    public String getSize() 
    {
        return size;
    }

    public String getColor() 
    {
        return color;
    }

    public String getMaterial() 
    {
        return material;
    }

    @Override
    public String toString() 
    {
        return super.toString() +
                ", Size: " + size +
                ", Color: " + color +
                ", Material: " + material;
    }
}
