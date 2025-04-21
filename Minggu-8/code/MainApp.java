// File: MainApp.java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MainApp {
    
    // Implementasi IdGenerator untuk String
    static class StringIdGenerator implements TransactionManager.IdGenerator<String> {
        private int counter = 0;
        
        @Override
        public String generateTransactionId(String prefix) 
        {
            return prefix + "-" + System.currentTimeMillis() + "-" + (++counter);
        }
    }
    
    public static void main(String[] args) 
    {
        // Inisialisasi sistem dengan multiple generic type parameters
        Inventory<String, String, Double, ProductCategory> inventory = new Inventory<>();
        TransactionManager<String, String, Double, ProductCategory, String, String> transactionManager = 
            new TransactionManager<>(inventory, new StringIdGenerator());
        
        // Tambahkan beberapa item
        Electronics smartphone = new Electronics(
            "E001", "Smartphone XYZ", 3500000.0, 15, 
            "Samsung", "Galaxy S21", 12
        );
        
        Electronics laptop = new Electronics(
            "E002", "Laptop Ultrabook", 12000000.0, 5, 
            "Lenovo", "ThinkPad X1", 24
        );
        
        Food snack = new Food(
            "F001", "Chocolate Bar", 10000.0, 100, 
            LocalDate.now().minusDays(30), 
            LocalDate.now().plusMonths(6)
        );
        
        Food milk = new Food(
            "F002", "Fresh Milk", 25000.0, 50, 
            LocalDate.now().minusDays(2), 
            LocalDate.now().plusDays(5)
        );
        
        Clothing tshirt = new Clothing(
            "C001", "Basic T-Shirt", 150000.0, 30, 
            "M", "Black", "Cotton"
        );
        
        // Tambahkan ke inventaris
        inventory.addItem(smartphone);
        inventory.addItem(laptop);
        inventory.addItem(snack);
        inventory.addItem(milk);
        inventory.addItem(tshirt);
        
        // Tampilkan semua item
        System.out.println("\n=== SEMUA ITEM DALAM INVENTARIS ===");
        inventory.getAllItems().forEach(System.out::println);
        
        // Lakukan beberapa transaksi
        System.out.println("\n=== TRANSAKSI INVENTARIS ===");
        
        // Transaksi masuk
        transactionManager.receiveItems("E001", 10, "Restock dari supplier");
        transactionManager.receiveItems("F001", 50, "Pengiriman mingguan");
        
        // Transaksi keluar
        transactionManager.dispatchItems("E002", 2, "Penjualan ke pelanggan");
        transactionManager.dispatchItems("C001", 5, "Pengiriman ke cabang toko");
        
        // Tampilkan stok setelah transaksi
        System.out.println("\n=== STOK SETELAH TRANSAKSI ===");
        inventory.getAllItems().forEach(item -> 
            System.out.println(item.getName() + " - Quantity: " + item.getQuantity())
        );
        
        // Tampilkan transaksi
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");
        transactionManager.getAllTransactions().forEach(System.out::println);
        
        // Cari item dengan nama tertentu
        System.out.println("\n=== PENCARIAN ITEM DENGAN NAMA 'LAPTOP' ===");
        inventory.findbyName("Laptop").forEach(System.out::println);
        
        // Cari item berdasarkan kategori
        System.out.println("\n=== ITEM KATEGORI FOOD ===");
        inventory.findByCategory(ProductCategory.FOOD).forEach(System.out::println);
        
        // Cari item dengan stok rendah
        System.out.println("\n=== ITEM DENGAN STOK RENDAH (<10) ===");
        inventory.getLowStockItems(10).forEach(System.out::println);
        
        // Tampilkan item yang kedaluwarsa
        System.out.println("\n=== ITEM YANG KEDALUWARSA ===");
        List<Item<String, String, Double, ProductCategory>> expiredItems = inventory.getExpiredItems();
        if (expiredItems.isEmpty()) {
            System.out.println("Tidak ada item yang kedaluwarsa");
        } else {
            expiredItems.forEach(System.out::println);
        }
        
        // Lakukan pencarian khusus dengan generics
        System.out.println("\n=== PENCARIAN KHUSUS: ELECTRONIC DENGAN GARANSI >12 BULAN ===");
        // Pertama filter untuk mendapatkan semua item Electronics
        List<Electronics> allElectronics = inventory.getAllItems().stream()
            .filter(item -> item instanceof Electronics)
            .map(item -> (Electronics) item)
            .toList();
        
        // Kemudian gunakan findByCustomCriteria untuk mencari berdasarkan kriteria kustom
        List<Electronics> longWarrantyElectronics = inventory.findByCustomCriteria(
            allElectronics,
            e -> e.getwarantyPeriodMonths() > 12
        );
        longWarrantyElectronics.forEach(System.out::println);
        
        // Tampilkan statistik inventaris
        System.out.println("\n=== STATISTIK INVENTARIS ===");
        System.out.println("Total jenis item: " + inventory.getTotalItemCount());
        System.out.println("Total kuantitas item: " + inventory.getTotalQuantity());
        System.out.println("Total nilai inventaris: Rp " + String.format("%,.2f", inventory.getTotalValue()));
    }
}