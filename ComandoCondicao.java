import java.util.ArrayList;

public class ComandoCondicao extends Comando{
    private final String condicao;
    private final ArrayList<Comando> listaTrue;
    private final ArrayList<Comando> listaFalse;

    public ComandoCondicao(String condicao, ArrayList<Comando> listaTrue, ArrayList<Comando> listaFalse) {
        this.condicao = condicao;
        this.listaTrue = listaTrue;
        this.listaFalse = listaFalse;
    }

    @Override
    public String geraCodigo(){
        String str = "\tif ( " + condicao + " ) {\n";
        for (Comando cmd: listaTrue) {
            str += "\t" + cmd.geraCodigo();
        }
        str += "\t}\n";

        if (!listaFalse.isEmpty()) {
            str += "\telse {\n";
            for (Comando cmd: listaFalse) {
                str += "\t" + cmd.geraCodigo();
            }
            str += "\t}\n";
        }
        return str;
    }

}
