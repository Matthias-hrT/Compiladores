import java.util.ArrayList;
import java.util.List;

public class ListaTokens {
    // Lista para armazenar os tokens analisados
    private List<Token> tokens;
    private int posicao;

    // Construtor que inicializa a lista de tokens
    public ListaTokens() {
        tokens = new ArrayList<>();  // Usa ArrayList para armazenar os tokens
        posicao = 0;
    }

    // Metodo para adicionar um token à lista de tokens
    public void adicionarToken(Token token) {
        tokens.add(token);  // Adiciona o token recebido à lista
    }

    public Token atual(){
        if(posicao < tokens.size()){
            return tokens.get(posicao);
        }
        return null;
    }

    public Token avancar(){
        if (posicao < tokens.size()){
            return tokens.get(posicao++);
        }
        return null;
    }

    public void retroceder(){
        if (posicao > 0){
            posicao--;
        }
    }

    public boolean temProximo(){
        return posicao < tokens.size();
    }

    public int getPosicao(){
        return posicao;
    }

    // Metodo para exibir todos os tokens armazenados na tabela de símbolos
    public void exibirTokens() {
        for (Token token : tokens) {  // Itera sobre cada token na lista
            System.out.println(token.toString());  // Chama o metodo toString do token para exibir sua representação
        }
    }
}
