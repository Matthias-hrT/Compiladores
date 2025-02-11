public class ComandoLeitura extends Comando {
    private String id;

    @Override
    public String geraCodigo(){
        return "\tscanf(\"%d\", &"+ id + ");\n";
    }

    public ComandoLeitura(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
