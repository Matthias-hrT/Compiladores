
public class Main {
    public static void main(String[] args) {

        AnalLexico lex =  new AnalLexico(args[0]);
        Token t = lex.proxToken();
        while(t != null){
            System.out.println(t.toString());
            t = lex.proxToken();
        }

    }
}