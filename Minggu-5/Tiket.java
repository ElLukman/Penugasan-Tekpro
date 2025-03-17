public record Tiket(String nomorTiket, String namaPertandingan, String tribun, String waktu) {
    @Override
    public String toString() {
        return "Nomor Tiket: " + nomorTiket + 
               "\nPertandingan: " + namaPertandingan + 
               "\nTribun: " + tribun + 
               "\nWaktu: " + waktu;
    }
}