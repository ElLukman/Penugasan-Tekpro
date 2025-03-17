public class Pertandingan {
    private String tim1;
    private String tim2;
    private String kompetisi;
    private int matchday;
    private String tanggal;
    private String waktu;

    public Pertandingan(String tim1, String tim2, String kompetisi, int matchday, String tanggal, String waktu) {
        this.tim1 = tim1;
        this.tim2 = tim2;
        this.kompetisi = kompetisi;
        this.matchday = matchday;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    public String getTim1() { return tim1; }
    public String getTim2() { return tim2; }
    public String getKompetisi() { return kompetisi; }
    public int getMatchday() { return matchday; }
    public String getTanggal() { return tanggal; }
    public String getWaktu() { return waktu; }

    public String getNamaPertandingan() {
        return tim1 + " vs " + tim2;
    }
}