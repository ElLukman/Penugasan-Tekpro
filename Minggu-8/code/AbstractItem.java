// File : AbstractItem.java

/*
 * Kelas abstrak yang mengimplementasikan interface Item dengan multiple generic types
 * @param <ID> Tipe dari ID untuk item 
 * @param <Name> Tipe dari nama untuk item
 * @param <Price> Tipe dari harga untuk item
 * @param <Category> Tipe dari kategori untuk item
*/
import java.time.LocalDate;

public abstract class AbstractItem  <ID, Name, Price, Category> implements Item <ID, Name, Price, Category>
{
    private ID id;
    private Name name;
    private Price price;
    private int quantity;
    private Category category;

    // Konstruktor
    public AbstractItem(ID id, Name name, Price price, int quantity, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    @Override
    public ID getId()
    {
        return id;
    }

    @Override
    public Name getName()
    {
        return name;
    }

    @Override
    public Price getPrice()
    {
        return price;
    }

    @Override
    public int getQuantity()
    {
        return quantity;
    }

    @Override
    public Category getCategory()
    {
        return category;
    }

    @Override
    public void updateQuantity(int change)
    {
        this.quantity += change;
        if (this.quantity < 0)
        {
            this.quantity = 0;
        }
    }

    public void setPrice(Price price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "ID: " + id +
               ", Name: " + name + 
               ", Price: " + price + 
               ", Quantity: " + quantity + 
               ", Category: " + category;
    }
    
}
