import java.util.Scanner;
import java.util.Random;

public class TFfilipo {

    public static void MostraMatriz(char[][] M, int linhas, int colunas) {
        for (int L = 0; L < linhas; L++) {
            for (int C = 0; C < colunas; C++) {
                System.out.printf(" %c ", M[L][C]);
            }
            System.out.printf("\n");
        }
    }

    // método feito em sala para achar valores exclusivos
    // alteração para representar matriz que não sao quadradas (n = linhas * colunas)
    static int SorteiaValorExclusivo(int[] v, int n, int pos) {
        Random random = new Random();
        int nro;
        boolean achei;
        do {
            achei = false;
            nro = random.nextInt(n); // ao invês de n*n
            for (int i = 0; i < pos; i++) {
                if (v[i] == nro) {
                    achei = true; 
                    break;
                }
            }
        } while (achei);
        return nro;
    }

    // liga pontos x,y de inicio e x,y de fim
    static void Caminho(char[][] M, int linI, int colI, int linF, int colF, char simbolo) {
        while (linI != linF) {
            if (linF > linI) linI++;
            else linI--;
            if (M[linI][colI] == '.') M[linI][colI] = simbolo;
        }
        while (colI != colF) {
            if (colF > colI) colI++;
            else colI--;
            if (M[linI][colI] == '.') M[linI][colI] = simbolo;
        }
    }

    //recebe as dimensoes
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Digite a altura da matriz: ");
        int Ylinhas = teclado.nextInt();

        System.out.println("Digite a largura da matriz: ");
        int Xcolunas = teclado.nextInt();

        if (Ylinhas <= 3 || Xcolunas <= 3) {
            System.out.println("As dimensões da matriz devem ser maior que 3");
            return;
        }

        char[][] M = new char[Ylinhas][Xcolunas];

        //prenche a matriz com pontos
        for (int L = 0; L < Ylinhas; L++) {
            for (int C = 0; C < Xcolunas; C++) {
                M[L][C] = '.';
            }
        }

        // alteração no método de sorteio para matrizes não quadradas
        int n = Ylinhas * Xcolunas;
        int[] ponto = new int[4];
        for (int i = 0; i < 4; i++) {
            //alguns valores estavam dando falhas!
            ponto[i] = SorteiaValorExclusivo(ponto, n, i);
        }

        // transforma o numero random em coordenadas
        int linA = ponto[0] / Xcolunas;
        int colA = ponto[0] % Xcolunas;
        int linB = ponto[1] / Xcolunas;
        int colB = ponto[1] % Xcolunas;
        int linC = ponto[2] / Xcolunas;
        int colC = ponto[2] % Xcolunas;
        int linD = ponto[3] / Xcolunas;
        int colD = ponto[3] % Xcolunas;

        // marca os pontos na matriz
        M[linA][colA] = 'A';
        M[linB][colB] = 'B';
        M[linC][colC] = 'C';
        M[linD][colD] = 'D';

        // liga os ponto A,B e C com + e C e D com *.
        Caminho(M, linA, colA, linB, colB, '+'); 
        Caminho(M, linB, colB, linC, colC, '+'); 
        Caminho(M, linC, colC, linD, colD, '*'); 
           
        System.out.println("\nMatriz com os pontos interligados:");
        MostraMatriz(M, Ylinhas, Xcolunas);
    }
}
