/*
 * Matheus Hirata Vanzela 2408848
 *
 * Thiago Cristovão de Souza 2368072
 */

public class Main {
    public static void main(String[] args) {
        // Inicializa o analisador léxico com o caminho do arquivo fornecido como argumento
        AnalLexico lex =  new AnalLexico(args[0]);

        // Cria uma tabela de símbolos para armazenar os tokens
        TabelaSimbolos tabela = new TabelaSimbolos();

        // Obtém o primeiro token do analisador léxico
        Token t = lex.proxToken();

        // Continua a leitura de tokens enquanto não houver erro e não for o fim do arquivo
        while(t != null){
            tabela.adicionarToken(t); // Adiciona o token à tabela de símbolos
            t = lex.proxToken();      // Lê o próximo token
        }
        tabela.exibirTokens();

    }
}
