import java.util.ArrayList;
import java.util.List;

public class TabelaSimbolos {
    private List<Token> tokens;

    public TabelaSimbolos() {
        tokens = new ArrayList<>();
    }

    // Add token
    public void adicionarToken(Token token) {
        tokens.add(token);
    }

    // Printa tokens
    public void exibirTokens() {
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }
}
