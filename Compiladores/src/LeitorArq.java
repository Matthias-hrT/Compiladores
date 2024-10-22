import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArq {
    private BufferedReader leitor;
    private char[] buffer1;
    private int index;
    private int bufferAtual;

    private static final int TAMANHO_BUFFER = 8;

    public LeitorArq(String arq) {
        try {
            leitor = new BufferedReader(new FileReader(arq));
            buffer1 = new char[TAMANHO_BUFFER];
            index = 0;
            bufferAtual = 0;
            carregarProximoBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarProximoBuffer() {
        try {
            bufferAtual = leitor.read(buffer1, 0, TAMANHO_BUFFER);
            index = 0;  // Reinicializa o índice para o novo buffer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int lerProxCaractere() {
        // troca o buffer apos terminar de ler o atual
        if (index >= bufferAtual) {
            carregarProximoBuffer();
            if (bufferAtual == -1) {
                return -1;
            }
        }
        // Retorna o prox caractere do buffer
        return buffer1[index++];
    }

    public void caractereAnterior() {
        // Retrocede o índice
        if (index > 0) {
            index--;
        }
    }

    public void fecharArquivo() {
        try {
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
