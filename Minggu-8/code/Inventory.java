// File : Inventory.java

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Locale.Category;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * Sistem Inventaris yang menggunakan generic programming dengan multiple generic types
 * @param <ID> Tipe dari ID untuk item
 * @param <Name> Tipe dari nama untuk item
 * @param <Price> Tipe dari harga untuk item
 * @param <Category> Tipe dari kategori untuk item
 */

public class Inventory <ID, Name, Price extends Number, Category> 
{
    private List <Item <ID, Name, Price, Category>> items;

    // Konstruktor
    public Inventory() 
    {
        this.items = new ArrayList<>();
    }

    // Menambahkan item ke dalam inventaris
    public void addItem(Item<ID, Name, Price, Category> item) 
    {
        items.add(item);
    }

    // Menghapus item dari inventaris berdasarkan ID
    public void removeItem(ID id) 
    {
        items.removeIf(item -> item.getId().equals(id));
    }

    // Mencari item berdasarkan ID
    public Optional <Item<ID, Name, Price, Category>> findbyID(ID id)
    {
        return items.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    // Mencari item berdasarkan Nama
    public List <Item<ID, Name, Price, Category>> findbyName(Name name) 
    {
        return items.stream().filter(item -> item.getName().equals(name)).collect(Collectors.toList());
    }

    // Mencari item berdasarkan Kategori
    public List <Item<ID, Name, Price, Category>> findByCategory(Category category)
    {
        return items.stream().filter(item -> item.getCategory().equals(category)).collect(Collectors.toList());
    }

    // Generic search dengan kriteria kustom
    public <E extends Item<ID, Name, Price, Category>> List<E> findByCustomCriteria(List<E> sourceList, Predicate<E> criteria)
    {
        return sourceList.stream().filter(criteria).collect(Collectors.toList());
    }

    public boolean updateStock (ID id, int quantityChange)
    {
        Optional<Item<ID, Name, Price, Category>> itemOpt = findbyID(id);
        if (itemOpt.isPresent())
        {
            Item<ID, Name, Price, Category> item = itemOpt.get();
            item.updateQuantity(quantityChange);
            return true;
        }
        return false;
    }

    public List<Item<ID, Name, Price, Category>> getLowStockItems(int threshold)
    {
        return items.stream().filter(item -> item.getQuantity() < threshold).collect(Collectors.toList());
    }

    public List <Item<ID, Name, Price, Category>> getExpiredItems()
    {
        List <Item<ID, Name, Price, Category>> expiredItems = new ArrayList<>();

        for (Item <ID, Name, Price, Category> item : items) 
        {
            if (item instanceof Expirable && ((Expirable) item).isExpired())
            {
                expiredItems.add(item);
            }
        }

        return expiredItems;
    }

    public List <Item<ID, Name, Price, Category>> getAllItems()
    {
        return new ArrayList<>(items);
    }

    public int getTotalItemCount()
    {
        return items.size();
    }

    public int getTotalQuantity()
    {
        return items.stream().mapToInt(Item::getQuantity).sum();
    }

    public double getTotalValue()
    {
        return items.stream().mapToDouble(item -> item.getPrice().doubleValue()
         * item.getQuantity()).sum();
    }
}
