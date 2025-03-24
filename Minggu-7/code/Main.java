package code;
public class Main 
{
    public static void main(String[] args)
    {
        // Inisialisasi sistem tiket
        SistemTiket sistemTiket = new SistemTiket(50, 10000, 50000);
        
        // Tambah pertandingan
        sistemTiket.tambahPertandingan(new Pertandingan("P001", "Persib Bandung", "Persija Jakarta",
        "2025-04-15", "GBLA", 20000, 15000));
        sistemTiket.tambahPertandingan(new Pertandingan("P002", "Arema FC", "PSIS Semarang", 
        "2025-04-16", "Kanjuruhan", 10000, 8000));
        sistemTiket.tambahPertandingan(new Pertandingan("P003", "Persebaya", "Dewa Utd", 
        "2025-04-19", "GBT", 40000, 35000));
        System.out.println("");

        // Tambah Penonton
        sistemTiket.tambahPenonton(new Penonton("N001", "Lukman Ahmad", "lukmanahmad@gmail.com",
        "081221005141", false));
        sistemTiket.tambahPenonton(new Penonton("N002", "Budi", "budi@gmail.com", 
        "081251004141", true));
        sistemTiket.tambahPenonton(new Penonton("N003", "Yusuf", "yusuf@gmail.com",
        "082141410009", false));
        System.out.println("");

        // Buat Tiket
        Tiket tiket1 = sistemTiket.buatTiket("P001", "N001", "Regular");
        Tiket tiket2 = sistemTiket.buatTiket("P002", "N002", "VVIP");
        Tiket tiket3 = sistemTiket.buatTiket("P003", "N003", "VIP");
        System.out.println("");

        // Tampilkan Semua Tiket
        sistemTiket.tampilkanSemuaTiket();
        System.out.println("");

        // Bayar Tiket
        sistemTiket.bayarTiket(tiket1.getIdTiket());
        sistemTiket.bayarTiket(tiket2.getIdTiket());

        // Batalkan Tiket Yang Belum Dibayar
        sistemTiket.batalkanTiket(tiket3.getIdTiket());

        // Tampilkan Tiket Yang Belum Dibayar
        sistemTiket.tampilkanTiketBelumDibayar();

        // Tampilkan pertandingan setelah pembelian tiket
        sistemTiket.tampilkanSemuaPertandingan();
        System.out.println("");

    }    
}