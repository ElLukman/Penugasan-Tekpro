// File: Transaction.java
import java.time.LocalDateTime;

/**
 * Kelas untuk merekam transaksi inventaris dengan multiple generic type parameters
 * @param <ID> Tipe dari ID item
 * @param <TID> Tipe dari ID transaksi
 * @param <Deskripsi> Tipe dari deskripsi
 */

public class Transaction<ID, TID, Deskripsi> 
{
    public enum TransactionType 
    {
        INCOMING, OUTGOING
    }
    
    private TID transactionId;
    private ID itemId;
    private int quantity;
    private TransactionType type;
    private LocalDateTime timestamp;
    private Deskripsi description;
    
    public Transaction(TID transactionId, ID itemId, int quantity, 
                      TransactionType type, Deskripsi description) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.description = description;
    }
    
    public TID getTransactionId() 
    {
        return transactionId;
    }
    
    public ID getItemId() 
    {
        return itemId;
    }
    
    public int getQuantity() 
    {
        return quantity;
    }
    
    public TransactionType getType() 
    {
        return type;
    }
    
    public LocalDateTime getTimestamp() 
    {
        return timestamp;
    }
    
    public Deskripsi getDescription() 
    {
        return description;
    }
    
    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + 
               ", Item ID: " + itemId + 
               ", Quantity: " + quantity + 
               ", Type: " + type + 
               ", Time: " + timestamp + 
               ", Description: " + description;
    }
}
