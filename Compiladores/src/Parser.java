public class Parser {
    private final ListaTokens listaTokens;

    public Parser(ListaTokens tokens) {
        this.listaTokens = tokens;
    }

    public void analPrograma(){
        if (listaTokens.atual().getTipo() == TipoToken.Delim){
            listaTokens.avancar();
            System.out.println("chegou aqui");
        } else {
            System.out.println("Erro: ':' esperado");
        }
        if(listaTokens.atual().getTipo() == TipoToken.Comentario){
            listaTokens.avancar();
            System.out.println("chegou aqui");
        } else {
            System.out.println("Erro: 'Comentario' esperado");
        }
    }

}
