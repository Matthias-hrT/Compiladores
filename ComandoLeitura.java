public class ComandoLeitura extends Comando {
    private String id;
    public static final int REAL = 0;
    public static final int INT = 1;


    @Override
    public String geraCodigo(){
        Simbolo simbolo = TabelaSimbolos.get(id);
        if (simbolo.getTipo() == INT){
            return "\tscanf(\"%d\", &"+ id + ");\n";
        } else if (simbolo.getTipo() == REAL) {
            return "\tscanf(\"%f\", &"+ id + ");\n";
        }
        return "";
    }

    public ComandoLeitura(String id) {
        this.id = id;
        System.out.println(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
