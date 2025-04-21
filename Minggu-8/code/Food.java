// File : Food.java
import java.time.LocalDate;

public class Food extends AbstractItem<String, String, Double, ProductCategory> implements Expirable
{
    private LocalDate productionDate;
    private LocalDate expirationDate;

    public Food(String id, String name, Double price, int quantity,
                LocalDate productionDate, LocalDate expirationDate)
    {
        super(id, name, price, quantity, ProductCategory.FOOD);
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }

    public LocalDate getProductionDate()
    {
        return productionDate;
    }

    @Override
    public LocalDate getExpiredDate()
    {
        return expirationDate;
    }

    @Override
    public boolean isExpired()
    {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public String toString()
    {
        return super.toString() +
            ", Production Date: " + productionDate +
            ", Expiration Date: " + expirationDate +
            ", Status: " + (isExpired() ? "Expired" : "Valid");
    }
}
