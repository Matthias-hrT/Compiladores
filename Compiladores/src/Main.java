
public class Main {
    public static void main(String[] args) {

        AnalLexico lex =  new AnalLexico("C:\\Users\\Hirata\\IdeaProjects\\Compiladores\\programa0.gyh");
        Token t = lex.proxToken();
        while(t != null){
            System.out.println(t.toString());
            t = lex.proxToken();
        }

    }
}