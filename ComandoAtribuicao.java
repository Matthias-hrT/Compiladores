public class ComandoAtribuicao extends Comando{
    private String id;
    private String exp;

    @Override
    public String geraCodigo() {
        return "\t" + id + " = " + exp + ";\n";
    }

    public ComandoAtribuicao(String id, String exp) {
        this.id = id;
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
