package code;
public class Tiket
{
    // Deklarasi
    private String idTiket;
    private Pertandingan pertandingan;
    private Penonton penonton;
    private String kategoriTiket;
    private double harga;
    private boolean dibayar;

    // Konstruktor 
    public Tiket (String idTiket, Pertandingan pertandingan, Penonton penonton, String kategoriTiket, double harga,
    boolean dibayar)
        {
        this.idTiket = idTiket;
        this.pertandingan = pertandingan;
        this.penonton = penonton;
        this.kategoriTiket = kategoriTiket;
        this.harga = hitungHarga();
        this.dibayar = false;
        }
    
    public Tiket (String idTiket, Pertandingan pertandingan, Penonton penonton, String kategoriTiket)
        {
        this.idTiket = idTiket;
        this.pertandingan = pertandingan;
        this.penonton = penonton;
        this.kategoriTiket = kategoriTiket;
        this.harga = hitungHarga();
        this.dibayar = false;
        }

    public String getIdTiket()
    {
        return idTiket;
    }

    public Pertandingan getPertandingan()
    {
        return pertandingan;
    }

    public Penonton getPenonton()
    {
        return penonton;
    }

    public String getKategoriTiket()
    {
        return kategoriTiket;
    }

    public double getHarga()
    {
        return harga;
    }

    public boolean isDibayar()
    {
        return dibayar;
    }

    public void setDibayar(boolean dibayar)
    {
        this.dibayar = dibayar;
    }

    private double hitungHarga()
    {
        double hargaDasar;

        switch(kategoriTiket)
        {
            case "VVIP":
                hargaDasar = 500000;
                break;
            case "VIP":
                hargaDasar = 300000;
                break;
            default:
                hargaDasar = 100000;
                break;
        }

        if (penonton.IsmemberVIP())
        {
            return hargaDasar = hargaDasar * 0.9;
        }

        return hargaDasar;
    }

    @Override
    public String toString()
    {
        return "Tiket [ID: " + idTiket + ", " + pertandingan.getTimHome() + " vs " + pertandingan.getTimAway() + ", Tanggal: "
                + pertandingan.getTanggal() + ", Stadion: " + pertandingan.getStadion() + ", Kategori: " + kategoriTiket
                + ", Penonton: " + penonton.getnama() + ", Harga: Rp " + harga + ", Status: " + 
                (dibayar ? "Sudah dibayar" : "Belum dibayar") + "]";
    }
}
 