import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class LeitorArq {

    public BufferedReader leitor;
    private String buffer;
    private int index;

    public LeitorArq(String arq){
        try {
            leitor = new BufferedReader(new FileReader(arq));
            buffer = "";
            index = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int lerProxCaractere(){
        if (index < buffer.length()){
            return buffer.charAt(index++);
        }
        try {
            int c = leitor.read();
            if (c != -1){
                buffer += (char) c;
                return lerProxCaractere();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public void caractereAnterior(){
        if (index > 0){
            index --;
        }
    }

    public void fecharArquivo(){
        try {
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
