/*
 * Matheus Hirata Vanzela 2408848
 *
 * Thiago Cristovão de Souza 2368072
 */

public class Main {
    public static void main(String[] args) throws Exception {
        // Inicializa o analisador léxico com o caminho do arquivo fornecido como argumento
        AnalLexico lex =  new AnalLexico(args[0]);

        // Cria uma tabela de símbolos para armazenar os tokens
        ListaTokens tabela = new ListaTokens();

        // Obtém o primeiro token do analisador léxico
        Token t = lex.proxToken();

        // Continua a leitura de tokens enquanto não houver erro e não for o fim do arquivo
        while(t != null){
            tabela.adicionarToken(t); // Adiciona o token à tabela de símbolos
            t = lex.proxToken();      // Lê o próximo token
        }
        //tabela.exibirTokens();

        // Cria o parser com a lista de tokens
        Parser parser = new Parser(tabela);
        // Executa a análise sintática
        parser.analPrograma();



    }
}
