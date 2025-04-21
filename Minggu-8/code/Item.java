// File : Item.java
/*
 * Interface untuk semua jenis item dalam inventaris
 * @param <ID> Tipe dari ID untuk item 
 * @param <Name> Tipe dari nama untuk item
 * @param <Price> Tipe dari harga untuk item
 * @param <Category> Tipe dari kategori untuk item
 */

public interface Item <ID, Name, Price, Category>
{
    ID getId();
    Name getName();
    Price getPrice();
    int getQuantity();
    void updateQuantity(int change);
    Category getCategory();
}
