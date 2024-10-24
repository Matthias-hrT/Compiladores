import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        AnalLexico lex = new AnalLexico("C:\\Users\\Hirata\\IdeaProjects\\Compiladores\\programa2.gyh");
        List<Token> tokens = new ArrayList<>();
        Token t = lex.proxToken();

        while(t != null) {
            tokens.add(t);  // Adiciona o token à lista
            t = lex.proxToken();
        }

        // Exibe todos os tokens da lista
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }
}
