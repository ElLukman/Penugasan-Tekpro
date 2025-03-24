package code;
public class Penonton 
{
    // Deklarasi
    private String id;
    private String nama;
    private String email;
    private String noTelepon;
    private boolean memberVIP;
    
    // Konstruktor
    public Penonton (String id, String nama, String email, String noTelepon, boolean memberVIP)
    {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.noTelepon = noTelepon;
        this.memberVIP = memberVIP;
    }

    // Method
    public String getId()
    {
        return id;
    }

    public String getnama()
    {
        return nama;
    }
    
    public String getemail()
    {
        return email;
    }
    
    public String getnoTelepon()
    {
        return noTelepon;
    }
    
    public boolean IsmemberVIP()
    {
        return memberVIP;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setNoTelepon(String noTelepon)
    {
        this.noTelepon = noTelepon;
    }

    public void setMemberVIP(boolean memberVIP)
    {
        this.memberVIP = memberVIP;
    }

    @Override
    public String toString()
    {
        return "Penonton [ID: ]" + id + ", Nama: " + nama + ", Email: " + email + ", No.Telepon: "
        + noTelepon + ", Status: " + (memberVIP ? "VIP" : "Regular") + "]";
    }
}
