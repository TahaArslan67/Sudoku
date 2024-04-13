package dosya;

import java.util.Random;

public class NewSudokuZorluk2 {
    public static void diziolustur(int[][] sudoku) {
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Rastgele bir sayı üret (0 veya 1)
                int randomNumber = r.nextInt(2);

                // Eğer rastgele sayı 1 ise, bu hücreye rastgele bir rakam ata
                if (randomNumber == 1) {
                    int randomNumberForCell = r.nextInt(9) + 1;
                    sudoku[i][j] = randomNumberForCell;

                    // Eğer bu rakam, bu satırda veya sütunda daha önce kullanıldıysa, hücreyi sıfırla
                    if (!isValid(sudoku, i, j, randomNumberForCell)) {
                        sudoku[i][j] = 0;
                    }
                }
            }
        }
    }

    public static boolean isValid(int[][] sudoku, int row, int col, int num) {
        // Aynı rakamın aynı satırda başka bir hücrede olup olmadığını kontrol et
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == num && i != col) {
                return false;
            }
        }

        // Aynı rakamın aynı sütunda başka bir hücrede olup olmadığını kontrol et
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == num && i != row) {
                return false;
            }
        }

        return true;
    }
}
