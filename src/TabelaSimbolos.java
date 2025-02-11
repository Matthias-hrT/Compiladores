import java.util.ArrayList;
import java.util.HashMap;

public class TabelaSimbolos {
    private static HashMap<String, Simbolo> tabela;

    public ArrayList<Simbolo> gottaCatchemAll(){
        ArrayList<Simbolo> list = new ArrayList<Simbolo>();
        for (Simbolo simbolo: tabela.values()){
            list.add(simbolo);
        }
        return list;
    }

    public TabelaSimbolos() {
        tabela = new HashMap<String, Simbolo>();
    }

    public void add(Simbolo symbol) {
        tabela.put(symbol.getNome(), symbol);
    }

    public boolean contains(String nome) {
        return tabela.get(nome)!=null;
    }

    public static Simbolo get(String nomeSimbolo) {
        return tabela.get(nomeSimbolo);
    }

}
