// File: TransactionManager.java
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sistem untuk mengelola transaksi inventaris dengan multiple generic type parameters
 * @param <ID> Tipe dari ID item
 * @param <Name> Tipe dari nama item
 * @param <Price> Tipe dari harga item
 * @param <Category> Tipe dari kategori item
 * @param <TID> Tipe dari ID transaksi
 * @param <Deskripsi> Tipe dari deskripsi transaksi
 */

public class TransactionManager<ID, Name, Price extends Number, Category, TID, Deskripsi>
{
    private Inventory<ID, Name, Price, Category> inventory;
    private List<Transaction<ID, TID, Deskripsi>> transactions;
    private IdGenerator<TID> idGenerator;
    
    public TransactionManager(Inventory<ID, Name, Price, Category> inventory, IdGenerator<TID> idGenerator) 
    {
        this.inventory = inventory;
        this.transactions = new ArrayList<>();
        this.idGenerator = idGenerator;
    }
    
    public boolean receiveItems(ID itemId, int quantity, Deskripsi description) 
    {
        // Validasi item ada di inventaris
        if (inventory.findbyID(itemId).isPresent() && quantity > 0) 
        {
            // Generate ID transaksi
            TID transactionId = idGenerator.generateTransactionId("IN");
            
            // Buat transaksi
            Transaction<ID, TID, Deskripsi> transaction = new Transaction<>
            (
                transactionId, itemId, quantity, 
                Transaction.TransactionType.INCOMING, description
            );
            
            // Tambahkan ke daftar transaksi
            transactions.add(transaction);
            
            // Update stok
            inventory.updateStock(itemId, quantity);
            return true;
        }
        return false;
    }
    
    public boolean dispatchItems(ID itemId, int quantity, Deskripsi description) 
    {
        // Validasi item ada dan stok mencukupi
        var itemOpt = inventory.findbyID(itemId);
        if (itemOpt.isPresent() && itemOpt.get().getQuantity() >= quantity && quantity > 0) {
            // Generate ID transaksi
            TID transactionId = idGenerator.generateTransactionId("OUT");
            
            // Buat transaksi
            Transaction<ID, TID, Deskripsi> transaction = new Transaction<>(
                transactionId, itemId, quantity, 
                Transaction.TransactionType.OUTGOING, description
            );
            
            // Tambahkan ke daftar transaksi
            transactions.add(transaction);
            
            // Update stok (kurangi)
            inventory.updateStock(itemId, -quantity);
            return true;
        }
        return false;
    }
    
    public List<Transaction<ID, TID, Deskripsi>> getAllTransactions() 
    {
        return new ArrayList<>(transactions);
    }
    
    public List<Transaction<ID, TID, Deskripsi>> getTransactionsByType(Transaction.TransactionType type) 
    {
        return transactions.stream()
                .filter(t -> t.getType() == type)
                .collect(Collectors.toList());
    }
    
    public List<Transaction<ID, TID, Deskripsi>> getTransactionsByItem(ID itemId) 
    {
        return transactions.stream()
                .filter(t -> t.getItemId().equals(itemId))
                .collect(Collectors.toList());
    }
    
    public List<Transaction<ID, TID, Deskripsi>> getTransactionsByDateRange(LocalDateTime start, LocalDateTime end) 
    {
        return transactions.stream()
                .filter(t -> !t.getTimestamp().isBefore(start) && !t.getTimestamp().isAfter(end))
                .collect(Collectors.toList());
    }
    
    // Interface baru untuk menghasilkan ID
    public interface IdGenerator<T> 
    {
        T generateTransactionId(String prefix);
    }
}