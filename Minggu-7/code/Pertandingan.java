package code;
public class Pertandingan 
{
    // Deklarasi Variabel
    private String id;
    private String timHome;
    private String timAway;
    private String tanggal;
    private String stadion;
    private int KapasitasStadion;
    private int tiketTersedia;

    // Konstruktor
    public Pertandingan (String id, String timHome, String timAway, String tanggal, String stadion, 
                        int KapasitasStadion, int tiketTersedia)
    {
        this.id = id;
        this.timHome = timHome;
        this.timAway = timAway;
        this.tanggal = tanggal;
        this.stadion = stadion;
        this.KapasitasStadion = KapasitasStadion;
        this.tiketTersedia = tiketTersedia;
    }

    // Method
    public String getId()
    {
        return id;
    }

    public String getTimHome()
    {
        return timHome;
    }

    public String getTimAway()
    {
        return timAway;
    }

    public String getTanggal()
    {
        return tanggal;
    }
    
    public String getStadion()
    {
        return stadion;
    }
    
    public int getKapasitasStadion()
    {
        return KapasitasStadion;
    }

    public int gettiketTersedia()
    {
        return tiketTersedia;
    }

    public void kurangiTiketTersedia(int jumlah)
    {
        if(jumlah <= tiketTersedia)
        {
            tiketTersedia = tiketTersedia - jumlah;
        }
        else
        {
            System.out.println("Error: Jumlah tiket yang diminta melebihi tiket yang tersedia!");
        }
    }

    public void tambahTiketTersedia(int jumlah)
    {
        int TotalSetelahPenambahan = tiketTersedia + jumlah;
        if (TotalSetelahPenambahan <= KapasitasStadion)
        {
            tiketTersedia = TotalSetelahPenambahan;
        }
        else
        {
            tiketTersedia = KapasitasStadion;
            System.out.println("Info: Tiket tersedia disesuaikan dengan kapasitas stadion");
        }
    }

    @Override
    public String toString()
    {
        return "Pertandingan [ID: ]" + id + ", " + timHome + " vs " + timAway + ", Tanggal: "
        + tanggal + ", Stadion: " + stadion + ", Tiket Tersedia: " + tiketTersedia + "/" + KapasitasStadion + "]";
    }
}