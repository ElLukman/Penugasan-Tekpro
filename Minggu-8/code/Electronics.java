// File : Electronics.java

public class Electronics extends AbstractItem <String, String, Double, ProductCategory>
{
    private String brand;
    private String model;
    private int warantyPeriodMonths;

    public Electronics (String id, String name, Double price, int quantity,
                        String brand, String model, int warantyPeriodMonths)
    {
        super(id, name, price, quantity, ProductCategory.ELECTRONICS);
        this.brand = brand;
        this.model = model;
        this.warantyPeriodMonths = warantyPeriodMonths;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public int getwarantyPeriodMonths()
    {
        return warantyPeriodMonths;
    }

    @Override
    public String toString()
    {
        return super.toString() + 
            ", Brand: " + brand +
            ", Model: " + model +
            ", Waranty: " + warantyPeriodMonths + " months";
    }
}
