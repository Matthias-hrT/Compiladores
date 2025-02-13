public class ComandoSaida extends Comando {
    private String id;
    public static final int REAL = 0;
    public static final int INT = 1;

    @Override
    public String geraCodigo(){
        if (id.startsWith("\"") && id.endsWith("\"")){
            return "\tprintf(" + id + ");\n";
        } else {
            Simbolo simbolo = TabelaSimbolos.get(id);
            if (simbolo.getTipo() == INT) {
                return "\tprintf(\"%d\", " + id + ");\n";
            } else if (simbolo.getTipo() == REAL) {
                return "\tprintf(\"%f\", " + id + ");\n";
            }
        }
        return "";
    }

    public ComandoSaida(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
