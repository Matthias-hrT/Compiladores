import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArq {
    private BufferedReader leitor;   // Lê o arquivo texto, linha por linha
    private char[] buffer1;          // Armazena um bloco de caracteres do arquivo
    private int index;               // Índice atual no buffer
    private int bufferAtual;         // Número de caracteres lidos no buffer atual

    private static final int TAMANHO_BUFFER = 8; // Tamanho do buffer para leitura em blocos de 8 caracteres

    // Construtor que inicializa o leitor e carrega o primeiro buffer do arquivo
    public LeitorArq(String arq) {
        try {
            leitor = new BufferedReader(new FileReader(arq));
            buffer1 = new char[TAMANHO_BUFFER];
            index = 0;
            bufferAtual = 0;
            carregarProximoBuffer(); // Carrega os primeiros caracteres do arquivo
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o erro caso o arquivo não possa ser lido
        }
    }

    // Carrega o próximo bloco de caracteres no buffer
    private void carregarProximoBuffer() {
        try {
            bufferAtual = leitor.read(buffer1, 0, TAMANHO_BUFFER); // Lê até 8 caracteres no buffer
            index = 0;  // Reinicia o índice para o início do novo buffer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lê o próximo caractere do buffer atual
    public int lerProxCaractere() {
        // Se o índice está no final do buffer, carrega um novo bloco
        if (index >= bufferAtual) {
            carregarProximoBuffer();
            if (bufferAtual == -1) { // Verifica se chegou ao fim do arquivo
                return -1;           // Retorna -1 para indicar o final do arquivo
            }
        }
        // Retorna o próximo caractere e incrementa o índice
        return buffer1[index++];
    }

    // Retorna ao caractere anterior no buffer
    public void caractereAnterior() {
        // Verifica se o índice está além do início do buffer
        if (index > 0) {
            index--; // Decrementa o índice para retroceder um caractere
        }
    }

    // Fecha o arquivo e libera recursos associados ao leitor
    public void fecharArquivo() {
        try {
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o erro caso o arquivo não possa ser fechado corretamente
        }
    }
}
