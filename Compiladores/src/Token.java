public class Token {
    // Campos que armazenam o lexema, tipo de token e a linha onde foi encontrado
    public String lexema;
    public TipoToken tipo;
    public int linha;

    // Construtor que inicializa o token com seu lexema, tipo e linha de ocorrência
    public Token(String lexema, TipoToken tipo, int linha){
        this.lexema = lexema;
        this.tipo = tipo;
        this.linha = linha;
    }

    // Sobrescreve o metodo toString() para representar o token no formato <tipo,"lexema",linha>
    @Override
    public String toString(){
        return "<" + tipo + ",\"" + lexema + "\"," + linha + ">";
    }

    // Getter e Setter para o lexema do token
    public String getLexema() {
        return lexema;
    }
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    // Getter e Setter para o tipo do token
    public TipoToken getTipo() {
        return tipo;
    }
    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    // Getter  e Setter para a linha onde o token foi encontrado
    public int getLinha() {
        return linha;
    }
    public void setLinha(int linha) {
        this.linha = linha;
    }
}
