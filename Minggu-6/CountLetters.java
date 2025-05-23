import java.util.Scanner;

public class CountLetters 
{
    public static void main(String[] args) 
    {
        int[] counts = new int[26];
        Scanner scan = new Scanner(System.in);

        // Meminta input dari pengguna
        System.out.print("Enter a single word (letters only, please): ");
        String word = scan.nextLine();

        // Mengubah ke huruf besar semua
        word = word.toUpperCase();

        // Menghitung frekuensi setiap huruf dalam string
        for (int i = 0; i < word.length(); i++) 
        {
            try 
            {
                counts[word.charAt(i) - 'A']++;
            } 
            catch (ArrayIndexOutOfBoundsException e) 
            {
                System.out.println("Not a letter: " + word.charAt(i));
            }
        }

        // Mencetak frekuensi
        System.out.println();
        for (int i = 0; i < counts.length; i++) 
        {
            if (counts[i] != 0) {
                System.out.println((char) (i + 'A') + ": " + counts[i]);
            }
        }
        scan.close();
    }
}