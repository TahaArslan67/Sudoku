package dosya;

import java.util.Scanner;

public class Sudoku {
    public static int[][] sudoku = new int[9][9];
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
    	int zorluk=0;
    	while (true) {
            System.out.println("Zorluk seviyesini seçiniz:");
            System.out.println("1. Kolay");
            System.out.println("2. Orta");
            System.out.println("3. Zor");
            zorluk = input.nextInt();
            if (zorluk < 1 || zorluk > 3) {
                System.out.println("Geçersiz zorluk seviyesi. Lütfen 1 ile 3 arasında bir değer giriniz.");
            }else break;
        }
        
        
        if (zorluk == 1) {
            NewSudokuZorluk1.diziolustur(sudoku);
        } else if (zorluk == 2) {
            NewSudokuZorluk2.diziolustur(sudoku);
        } else if (zorluk == 3) {
            NewSudokuZorluk3.diziolustur(sudoku);
        }
        SudokuYazdir.olustur(sudoku);
        int hataliGirisSayisi = 0;
        Boolean oyundevamediyor = true;

        while (oyundevamediyor) {
            System.out.print("Satırı giriniz: ");
            int satir = input.nextInt();
            satir--;
            System.out.print("Sütunu giriniz: ");
            int sutun = input.nextInt();
            sutun--;
            System.out.print("Girmek istediğiniz sayıyı giriniz: ");
            int sayi = input.nextInt();

            if (isValidMove(satir, sutun, sayi)) {
                sudoku[satir][sutun] = sayi;
                SudokuYazdir.olustur(sudoku);

                if (isWin()) {
                    System.out.println("Tebrikler! Kazandınız.");
                    oyundevamediyor = false;
                }
            } else {
                System.out.println("Hatalı giriş! Lütfen tekrar deneyin.");
                hataliGirisSayisi++;

                if (hataliGirisSayisi == 3) {
                    System.out.println("Üzgünüz! 3 kez hatalı giriş yaptınız. Kaybettiniz.");
                    oyundevamediyor = false;
                }
            }
        }
    }

    public static boolean isValidMove(int satir, int sutun, int sayi) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[satir][i] == sayi || sudoku[i][sutun] == sayi) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWin() {
        // Her satırı kontrol et
        for (int i = 0; i < 9; i++) {
            boolean[] satirKontrol = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] != 0 && satirKontrol[sudoku[i][j]]) {
                    return false;
                }
                satirKontrol[sudoku[i][j]] = true;
            }
        }

        // Her sütunu kontrol et
        for (int j = 0; j < 9; j++) {
            boolean[] sutunKontrol = new boolean[10];
            for (int i = 0; i < 9; i++) {
                if (sudoku[i][j] != 0 && sutunKontrol[sudoku[i][j]]) {
                    return false;
                }
                sutunKontrol[sudoku[i][j]] = true;
            }
        }

        // Tabloda sıfır olmadığı kontrol edilir, yani her hücre dolu demektir.
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
