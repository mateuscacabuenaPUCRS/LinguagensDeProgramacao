import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReconhecedorDeComentario {
    public static BufferedReader in_fp;
    public static char proximoCaractere;
    public static int estadoAtual;
    public static char[] especial = {'[', '!', '@', '#', '$', '%', '&', '*', '(', ')', '_', '+', '=', '|', '<', '>', '?', '{', '}', '\\', '[', ']', '~', '-'};

    public static void main(String[] args) {

        try {
            in_fp = new BufferedReader(new FileReader("src\\entrada2.txt"));
            String line;
            while ((line = in_fp.readLine()) != null) {
                System.out.println("Testando: " + line);
                if (reconhecerComentario(line)) {
                    System.out.println("Comentario valido: " + line);
                } else {
                    System.out.println("Comentario invalido: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
        }

    }

    private static boolean isSpecial(char caractere) {
        for (char c : especial) {
            if (c == caractere) {
                return true;
            }
        }
        return false;
    }

    private static boolean reconhecerComentario(String line) {
        estadoAtual = 0;
        for (int i = 0; i < line.length(); i++) {
            char caractere = line.charAt(i);
            if (i + 1 < line.length()) {
                char proximoCaractere = line.charAt(i + 1);
                transicao(caractere, proximoCaractere);
            }
        }
        return estadoAtual == 4; // Estado FINAL
    }

    private static void transicao(char caractere, char proximoCaractere) {

        switch (estadoAtual) {
            case 0:
                if (caractere == '/') {
                    estadoAtual = 1;
                } else {
                    estadoAtual = -1; // Estado inválido
                }
                break;
            case 1:
                if (caractere == '*') {
                    estadoAtual = 2;
                } else {
                    estadoAtual = -1; // Estado inválido
                }
                break;
            case 2:
            if (caractere == '*' && proximoCaractere == '/') {
                   estadoAtual = 4;
                } else if (Character.isDigit(caractere) || Character.isLetter(caractere) || caractere == ' '
                        || caractere == '\t' || isSpecial(caractere) ) {
                    estadoAtual = 2;
                } else {
                    estadoAtual = -1;
                }
                break;
            case 3:
                if (caractere == '*') {
                    if (proximoCaractere == '/') {
                        estadoAtual = 4;
                    } else if (Character.isDigit(proximoCaractere)) {
                        estadoAtual = 2;
                    } else {
                        estadoAtual = -1;
                    }
                } else {
                    estadoAtual = -1;
                }
                break;
            default:
                estadoAtual = -1;
        }
    }
}
