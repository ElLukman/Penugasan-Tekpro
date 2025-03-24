package code;
public class SistemTiket {
    // Deklarasi
    private Pertandingan[] daftarPertandingan;
    private Penonton[] daftarPenonton;
    private Tiket[] daftarTiket;
    private int jumlahPertandingan;
    private int jumlahPenonton;
    private int jumlahTiket;

    // Konstruktor
    public SistemTiket(int kapasitasPertandingan, int kapasitasPenonton, int kapasitasTiket) {
        daftarPertandingan = new Pertandingan[kapasitasPertandingan];
        daftarPenonton = new Penonton[kapasitasPenonton];
        daftarTiket = new Tiket[kapasitasTiket];
        jumlahPertandingan = 0;
        jumlahPenonton = 0;
        jumlahTiket = 0;
    }

    // Pertandingan
    public void tambahPertandingan(Pertandingan pertandingan) 
    {
        if (jumlahPertandingan < daftarPertandingan.length) 
        {
            daftarPertandingan[jumlahPertandingan] = pertandingan;
            jumlahPertandingan ++;
            System.out.println("Pertandingan berhasil ditambahkan: " + pertandingan.getTimHome() + " vs "
            + pertandingan.getTimAway());
        } 
        else
        {
            System.out.println("Kapasitas pertandingan penuh!");
        }
    }

    public Pertandingan cariPertandingan(String id)
    {
        for (int i = 0; i < jumlahPertandingan; i++)
        {
            if (daftarPertandingan[i].getId().equals(id))
            {
                return daftarPertandingan[i];
            }
        }
        return null;
    }

    public void tampilkanSemuaPertandingan()
    {
        System.out.println("\n==== Daftar Pertandingan ====");
        for (int i = 0; i < jumlahPertandingan; i++)
        {
            System.out.println(daftarPertandingan[i]);
        }
    }

    // Penonton
    public void tambahPenonton(Penonton penonton)
    {
        if (jumlahPenonton < daftarPenonton.length)
        {
            daftarPenonton[jumlahPenonton] = penonton;
            jumlahPenonton ++;
            System.out.println("Penonton berhasil ditambahkan " + penonton.getnama());
        }
        else 
        {
            System.out.println("Kapasitas penonton penuh!");
        }
    }

    public Penonton cariPenonton(String id)
    {
        for (int i = 0; i < jumlahPenonton; i++)
        {
            if (daftarPenonton[i].getId().equals(id))
            {
                return daftarPenonton[i];
            }
        }
        return null;
    }

    public void tampilkanSemuaPenonton()
    {
        System.out.println("\n=== Daftar Penonton ===");
        for (int i = 0; i < jumlahPenonton; i++)
        {
            System.out.println(daftarPenonton[i]);
        }
    }

    // Metode mengelola tiket
    public Tiket buatTiket(String idPertandingan, String idPenonton, String kategoriTiket)
    {
        Pertandingan pertandingan = cariPertandingan(idPertandingan);
        Penonton penonton = cariPenonton(idPenonton);

        if (pertandingan != null && penonton != null)
        {
            if (pertandingan.gettiketTersedia() > 0)
            {
                if (jumlahTiket < daftarTiket.length)
                {
                    String idTiket = "T" + (jumlahTiket + 1) + "-" + pertandingan.getId() + "-" + penonton.getId();
                    Tiket tiket = new Tiket(idTiket, pertandingan, penonton, kategoriTiket, 0, false);
                    daftarTiket[jumlahTiket] = tiket; // FIXED: Add ticket to array
                    jumlahTiket++;
                    
                    pertandingan.kurangiTiketTersedia(1);
                
                    System.out.println("Tiket berhasil dibuat: " + tiket.getIdTiket());
                    return tiket;
                }
                else
                {
                    System.out.println("Kapasitas tiket dalam sistem penuh!");
                }
            }
            else
            {
                System.out.println("Maaf, tiket untuk pertandingan ini sudah habis!");
            }   
        }
        else 
        {
            System.out.println("Pertandingan atau penonton tidak ditemukan!");
        }

        return null;
    }

    public Tiket cariTiket(String idTiket)
    {
        for (int i = 0; i < jumlahTiket; i++)
        {
            if(daftarTiket[i] != null && daftarTiket[i].getIdTiket().equals(idTiket))
            {
                return daftarTiket[i];
            }
        }
        return null;
    }

    public void bayarTiket(String idTiket)
    {
        Tiket tiket = cariTiket(idTiket);
        if (tiket != null)
        {
            if (!tiket.isDibayar())
            {
                tiket.setDibayar(true);
                System.out.println("Pembayaran Tiket Berhasil: " + tiket.getIdTiket());
                System.out.println("Total: Rp " + tiket.getHarga());
            }
            else
            {
                System.out.println("Tiket sudah dibayar sebelumnya");
            }
        }
        else
        {
            System.out.println("Tiket tidak ditemukan!");
        }
    }

    public void batalkanTiket(String idTiket)
    {
        Tiket tiket = cariTiket(idTiket);   
        if (tiket != null && !tiket.isDibayar())
        {
            for (int i = 0; i < jumlahTiket; i++)
            {
                if (daftarTiket[i] != null && daftarTiket[i].getIdTiket().equals(idTiket))
                {
                    tiket.getPertandingan().tambahTiketTersedia(1);

                    for (int j = i; j < jumlahTiket - 1; j++)
                    {
                        daftarTiket[j] = daftarTiket[j+1];
                    }

                    daftarTiket[jumlahTiket-1] = null;
                    jumlahTiket--;

                    System.out.println("Tiket berhasil dibatalkan: " + idTiket);
                    return;
                }
            }
        }
        else if (tiket != null && tiket.isDibayar())
        {
            System.out.println("Tiket yang sudah dibayar tidak dapat dibatalkan!");
        }
        else
        {
            System.out.println("Tiket tidak ditemukan");
        }
    }

    public void tampilkanSemuaTiket()
    {
        System.out.println("\n=== Daftar Tiket ===");
        for (int i = 0; i < jumlahTiket; i++)
        {
            System.out.println(daftarTiket[i]);
        }
    }

    public void tampilkanTiketBelumDibayar()
    {
        System.out.println("\n=== Daftar Tiket Belum Dibayar ===");
        int hitungTiketBelumDibayar = 0;
        for (int i = 0; i < jumlahTiket; i++)
        {
            if (daftarTiket[i] != null && !daftarTiket[i].isDibayar())
            {
                System.out.println(daftarTiket[i]);
                hitungTiketBelumDibayar++;
            }
        }
        System.out.println("Total tiket belum dibayar: " + hitungTiketBelumDibayar);
    }
}