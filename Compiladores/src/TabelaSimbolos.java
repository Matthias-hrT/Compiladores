import java.util.ArrayList;
import java.util.List;

public class TabelaSimbolos {
    // Lista para armazenar os tokens analisados
    private List<Token> tokens;

    // Construtor que inicializa a lista de tokens
    public TabelaSimbolos() {
        tokens = new ArrayList<>();  // Usa ArrayList para armazenar os tokens
    }

    // Metodo para adicionar um token à lista de tokens
    public void adicionarToken(Token token) {
        tokens.add(token);  // Adiciona o token recebido à lista
    }

    // Metodo para exibir todos os tokens armazenados na tabela de símbolos
    public void exibirTokens() {
        for (Token token : tokens) {  // Itera sobre cada token na lista
            System.out.println(token.toString());  // Chama o metodo toString do token para exibir sua representação
        }
    }
}
