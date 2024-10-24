
public class Main {
    public static void main(String[] args) {

        AnalLexico lex =  new AnalLexico("C:\\Users\\Hirata\\IdeaProjects\\Compiladores\\programa1.gyh");
        TabelaDeSimbolos tabela = new TabelaDeSimbolos();
        Token t = lex.proxToken();

        while(t != null && !lex.temErro()){
            tabela.adicionarToken(t);
            t = lex.proxToken();
        }
        if(!lex.temErro()){
            tabela.exibirTokens();
        }

    }
}