import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        GyhLexer lexer = new GyhLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GyhParser parser = new GyhParser(tokens);

        System.out.println("Compilação - Lexico OK");
        parser.programa();

        parser.geraComando();

    }
}