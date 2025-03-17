import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BolaApp {
    // 4. Concurrent Collections 
    static ConcurrentHashMap<String, Boolean> tribunTersedia = new ConcurrentHashMap<>();
    
    // 5. Queue 
    static Queue<String> antrianPemesanan = new LinkedList<>();
    
    // 5. Dequeue 
    static Deque<String> riwayatNavigasi = new ArrayDeque<>();
    
    // 1. List - ArrayList 
    static List<Pertandingan> daftarPertandingan = new ArrayList<>();
    
    static Scanner scanner = new Scanner(System.in);
    
    // 6. Immutable Map
    static Map<String, Integer> hargaTiket = Map.of(
        "WC 2026 Qualifiers", 300000,
        "BRI Liga 1", 120000
    );
    
    // Map of stadiums for each competition
    static Map<String, String> stadionMap = Map.of(
        "WC 2026 Qualifiers", "Sydney Stadium",
        "BRI Liga 1", "Jakarta International Stadium"
    );

    public static void main(String[] args) {
        // 6. Immutable Collections 
        List<String> waktuTersedia = List.of("15:00", "19:00", "20:30");
        Set<String> kompetisiTersedia = Set.of("WC 2026 Qualifiers", "BRI Liga 1");

        // Initialize match data
        daftarPertandingan.add(new Pertandingan("Australia", "Indonesia", "WC 2026 Qualifiers", 7, "20/03/2025", waktuTersedia.get(0)));
        daftarPertandingan.add(new Pertandingan("Indonesia", "Bahrain", "WC 2026 Qualifiers", 8, "25/03/2025", waktuTersedia.get(2)));
        daftarPertandingan.add(new Pertandingan("Persib", "Persija", "BRI Liga 1", 20, "20/04/2025", waktuTersedia.get(1)));

        // Initialize tribune availability
        tribunTersedia.put("UTARA", true);
        tribunTersedia.put("SELATAN", true);
        tribunTersedia.put("BARAT", true);
        tribunTersedia.put("TIMUR", true);

        while (true) {
            clearScreen();            
            System.out.println("\n== MENU ==");
            System.out.println("1. Lihat Daftar Pertandingan");
            System.out.println("2. Pesan Tiket");
            System.out.println("3. Exit");
            System.out.print("Pilih: ");
            
            // Handle invalid input
            int pilihan;
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear the invalid input
                System.out.println("Input tidak valid. Masukkan angka.");
                waitForEnter();
                continue;
            }

            switch (pilihan) {
                case 1:
                    clearScreen();
                    lihatDaftarPertandingan();
                    break;
                case 2:
                    clearScreen();
                    pesanTiket();
                    break;
                case 3:
                    clearScreen();                
                    System.out.println("Terima kasih telah menggunakan BolaApp!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                    waitForEnter();
            }
        }
    }

    static void lihatDaftarPertandingan() {
        System.out.println("\n== DAFTAR PERTANDINGAN ==");
        for (int i = 0; i < daftarPertandingan.size(); i++) {
            System.out.println((i + 1) + ". " + daftarPertandingan.get(i).getNamaPertandingan());
        }
        
        System.out.print("\nPilih pertandingan untuk melihat detail (0 untuk kembali): ");
        try {
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            if (pilihan == 0) {
                return;
            }
            
            // 3. Optional
            Optional<Pertandingan> optPertandingan = cariPertandingan(pilihan);
            if (optPertandingan.isPresent()) {
                tampilkanInfoPertandingan(optPertandingan.get());
            } else {
                System.out.println("Pertandingan tidak ditemukan.");
            }
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear the invalid input
            System.out.println("Input tidak valid. Masukkan angka.");
        }
        waitForEnter();
    }
    
    // Method baru untuk menampilkan info pertandingan dengan format yang diinginkan
    static void tampilkanInfoPertandingan(Pertandingan p) {
        String kompetisi = p.getKompetisi();
        String stadion = stadionMap.get(kompetisi);
        int harga = hargaTiket.get(kompetisi);
        
        System.out.println("\n=========================");
        System.out.printf("     %s vs %s\n", p.getTim1(), p.getTim2());
        System.out.printf("           %s\n", kompetisi);
        System.out.printf("           Matchday - %d\n", p.getMatchday());
        System.out.printf("     Tanggal: %s\n", p.getTanggal());
        System.out.printf("     Waktu: %s\n", p.getWaktu());
        System.out.printf("     Stadion: %s\n", stadion);
        System.out.printf("     Price: IDR %,d\n", harga);
        System.out.println("=========================");
    }

    static void pesanTiket() {
        System.out.println("\n== PESAN TIKET ==");
        System.out.println("(Daftar Pertandingan)");
        for (int i = 0; i < daftarPertandingan.size(); i++) {
            Pertandingan p = daftarPertandingan.get(i);
            System.out.println((i + 1) + ". " + p.getNamaPertandingan() + " - Matchday " + p.getMatchday() + 
                " - Harga: Rp " + String.format("%,d", hargaTiket.get(p.getKompetisi())));
        }

        System.out.print("\nPilih pertandingan (0 untuk kembali): ");
        int pilihan;
        try {
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            if (pilihan == 0) {
                return;
            }
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear the invalid input
            System.out.println("Input tidak valid. Masukkan angka.");
            waitForEnter();
            return;
        }

        // 3. Optional
        Optional<Pertandingan> optPertandingan = cariPertandingan(pilihan);
        if (optPertandingan.isEmpty()) {
            System.out.println("Pertandingan tidak valid.");
            waitForEnter();
            return;
        }

        Pertandingan p = optPertandingan.get();
        tampilkanInfoPertandingan(p);
        
        // User dapat memilih tribun hingga berhasil
        boolean tribunDipilih = false;
        String tribun = "";
        
        while (!tribunDipilih) {
            // Show available tribunes
            System.out.println("\nTribun yang tersedia:");
            for (Map.Entry<String, Boolean> entry : tribunTersedia.entrySet()) {
                System.out.println("- " + entry.getKey() + (entry.getValue() ? " (Tersedia)" : " (Tidak Tersedia)"));
            }
            
            System.out.print("\nPilih Tribun (UTARA/SELATAN/BARAT/TIMUR) atau ketik 'BATAL' untuk membatalkan: ");
            tribun = scanner.nextLine().toUpperCase();
            
            if (tribun.equals("BATAL")) {
                System.out.println("Pemesanan dibatalkan.");
                waitForEnter();
                return;
            }

            if (!tribunTersedia.containsKey(tribun)) {
                System.out.println("Tribun tidak valid. Silakan pilih tribun yang tersedia.");
                continue;
            }
            
            if (!tribunTersedia.get(tribun)) {
                System.out.println("Tribun " + tribun + " tidak tersedia. Silakan pilih tribun lain.");
                continue;
            }
            
            tribunDipilih = true;
        }

        System.out.print("\nPesan Tiket? (Y/N): ");
        String konfirmasi = scanner.nextLine().toUpperCase();
        if (konfirmasi.equals("Y")) {
            tribunTersedia.put(tribun, false);
            
            // 2. Record 
            Tiket tiket = new Tiket(
                UUID.randomUUID().toString(), 
                p.getNamaPertandingan(), 
                tribun, 
                p.getTanggal() + " " + p.getWaktu()
            );
            
            antrianPemesanan.add("Pemesanan: " + p.getNamaPertandingan());
            
            // 5. Deque
            riwayatNavigasi.push("Pesan Tiket");
            
            System.out.println("\nPemesanan Berhasil!");
            System.out.println("====================");
            System.out.println(tiket);
            System.out.println("====================");
        } else {
            System.out.println("Pemesanan dibatalkan.");
        }
        waitForEnter();
    }

    // 3. Optional
    static Optional<Pertandingan> cariPertandingan(int pilihan) {
        if (pilihan > 0 && pilihan <= daftarPertandingan.size()) {
            return Optional.of(daftarPertandingan.get(pilihan - 1));
        }
        return Optional.empty();
    }

    static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing screen: " + e.getMessage());
        }
    }

    static void waitForEnter() {
        System.out.println("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }
}