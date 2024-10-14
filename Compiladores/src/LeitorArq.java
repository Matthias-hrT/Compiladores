import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArq {
    private BufferedReader leitor;
    private char[] buffer1;
    private char[] buffer2;
    private int index;
    private int bufferAtual;
    private boolean usandoBuffer1;

    private static final int TAMANHO_BUFFER = 1024;

    public LeitorArq(String arq) {
        try {
            leitor = new BufferedReader(new FileReader(arq));
            buffer1 = new char[TAMANHO_BUFFER];
            buffer2 = new char[TAMANHO_BUFFER];
            index = 0;
            bufferAtual = 0;
            usandoBuffer1 = true;
            carregarProximoBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarProximoBuffer() {
        try {
            if (usandoBuffer1) {
                bufferAtual = leitor.read(buffer1, 0, TAMANHO_BUFFER);
            } else {
                bufferAtual = leitor.read(buffer2, 0, TAMANHO_BUFFER);
            }
            index = 0;  // Reinicializa o índice para o novo buffer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int lerProxCaractere() {
        // troca o buffer apos terminar de ler o atual
        if (index >= bufferAtual) {
            if (bufferAtual == -1) {
                return -1;
            }
            usandoBuffer1 = !usandoBuffer1;  // Alterna os buffers
            carregarProximoBuffer();
        }

        // Retorna o prox caractere do buffer
        if (usandoBuffer1) {
            return buffer1[index++];
        } else {
            return buffer2[index++];
        }
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
