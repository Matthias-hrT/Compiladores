
public class Simbolo {
    private String nome;
    private int tipo;
    private String valor;

    public static final int REAL = 0;
    public static final int INT = 1;

    public String geraCodigo(){
        String str;
        if (tipo == REAL) str = "\tdouble " + nome + ";\n"; // real nome;
        else str = "\tint " + nome + ";\n";
        return str;
    }

    public Simbolo(String nome, String tipo, String valor) {
        this.nome = nome;
        if (tipo.equals("INT")) this.tipo = INT;
        else this.tipo = REAL;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() { return tipo; }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Simbolo{" +
                "nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", valor='" + valor + '\'' +
                '}';
    }
}
