import java.util.ArrayList;

public class ComandoRepeticao extends Comando {
    private final String condicao;
    private final ArrayList<Comando> listaCmd;

    public ComandoRepeticao(String condicao, ArrayList<Comando> listaCmd) {
        this.condicao = condicao;
        this.listaCmd = listaCmd;
    }

    @Override
    public String geraCodigo() {
        String str = "\twhile (" + condicao + ") {\n";
        for (Comando cmd: listaCmd) {
            str += "\t" + cmd.geraCodigo();
        }
        str += "\t}\n";
        return str;
    }
}
