import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class ProgramaGyh {
    private TabelaSimbolos tabelaVar;
    private ArrayList<Comando> comando;

    public TabelaSimbolos getTabelaVar() {
        return tabelaVar;
    }

    public void setTabelaVar(TabelaSimbolos tabelaVar) {
        this.tabelaVar = tabelaVar;
    }

    public ArrayList<Comando> getComando() {
        return comando;
    }

    public void setComando(ArrayList<Comando> comando) {
        this.comando = comando;
    }

    public void geradorC(){
        StringBuilder str = new StringBuilder();

        str.append("#include <stdio.h>\n" +
                "#include <stdlib.h>\n" +
                "#include <string.h>\n" +
                "#include <stdbool.h>\n\n");
        str.append("int main() {\n");

        for (Simbolo simbolo: tabelaVar.gottaCatchemAll()) {
            str.append(simbolo.geraCodigo());
        }

        for (Comando cmd: comando) {
            str.append(cmd.geraCodigo());
        }

        str.append("return 0;\n}");

        try {
            FileWriter arquivo = new FileWriter(new File("Gyh.c"));
            arquivo.write(str.toString());
            arquivo.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
