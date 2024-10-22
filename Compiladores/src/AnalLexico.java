
public class AnalLexico {
    LeitorArq ldat;
    int linhaAtual;

    public AnalLexico(String arq){
        this.ldat = new LeitorArq(arq);
        this.linhaAtual = 1;
    }

    public Token proxToken(){
        int c;
        char ch;
        int proxChar;

        while((c = ldat.lerProxCaractere()) != -1){
            ch = (char) c;

            // Espaço
            if (ch == ' ' || ch == '\t' || ch == '\r'){
                continue;
            }

            // Prox Linha
            if (ch == '\n'){
                linhaAtual++;
                continue;
            }

            // == Igual
            switch(ch){
                //mais de uma verificação
                case '=': {
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == '=') {
                        return new Token("==", TipoToken.OpRelIgual, linhaAtual);
                    }
                    System.out.println("Erro Léxico: < " + ch + " > linha " + linhaAtual);
                    return null;
                }

                // <  <=  Menor/Menor Igual
                case '<': {
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == '=') {
                        return new Token("<=", TipoToken.OpRelMenorIgual, linhaAtual);
                    }
                    return new Token("<", TipoToken.OpRelMenor, linhaAtual);
                }

                // >  >=  Maior/Maior Igual
                case '>': {
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == '=') {
                        return new Token(">=", TipoToken.OpRelMaiorIgual, linhaAtual);
                    }
                    return new Token(">", TipoToken.OpRelMaior, linhaAtual);
                }

                // :  :=   Delimitador/Atribuição
                case ':': {
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == '=') {
                        return new Token(":=", TipoToken.Atrib, linhaAtual);
                    }
                    ldat.caractereAnterior();
                    return new Token(":", TipoToken.Delim, linhaAtual);
                }

                // != Diferente
                case '!': {
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == '=') {
                        return new Token("!=", TipoToken.OpRelDif, linhaAtual);
                    }
                    System.out.println("Erro Léxico: < " + ch + " > linha " + linhaAtual);
                    return null;
                }

                // CADEIA
                case '"':{
                    StringBuilder cadeia = new StringBuilder();

                    while ((proxChar = ldat.lerProxCaractere()) != -1){
                        if (proxChar == '"'){
                            return new Token(cadeia.toString(), TipoToken.Cadeia, linhaAtual);
                       }
                        cadeia.append((char)proxChar);
                    }
                    System.out.println("Erro Léxico: desconhecido caractere "+ ch + " linha " + linhaAtual);
                    return null;
                }

                // # Comentário
                case '#':{
                    StringBuilder comentario = new StringBuilder();

                    while((proxChar = ldat.lerProxCaractere()) != -1){
                        if (proxChar == '\n'){
                            ldat.caractereAnterior();
                            return new Token(comentario.toString(), TipoToken.Comentario, linhaAtual);
                        }
                    }
                    return new Token(comentario.toString(), TipoToken.Comentario, linhaAtual);
                }

                // +  Soma
                case '+': return new Token("+", TipoToken.OpAritSoma, linhaAtual);

                // - Subtração
                case '-': return new Token("-", TipoToken.OpAritSub, linhaAtual);

                // *  Multiplicação
                case '*': return new Token("*", TipoToken.OpAritMult, linhaAtual);

                // /  Divisão
                case '/': return new Token("/", TipoToken.OpAritDiv, linhaAtual);

                // (  Abre Parentesis
                case '(': return new Token("(", TipoToken.AbrePar, linhaAtual);

                // )  Fecha Parentesis
                case ')': return new Token(")", TipoToken.FechaPar, linhaAtual);

                // VAR, NumInt, NumReal
                default:{
                    StringBuilder buffer = new StringBuilder();
                    buffer.append(ch);

                    if (Character.isLowerCase(ch)){
                        while ((proxChar = ldat.lerProxCaractere()) != -1 &&
                        Character.isLetterOrDigit(proxChar)){
                            buffer.append((char)proxChar);
                        }
                        ldat.caractereAnterior();
                        return new Token(buffer.toString(), TipoToken.Var, linhaAtual);
                    }
                    else if (Character.isDigit(ch)){
                        boolean isReal = false;
                        while ((proxChar = ldat.lerProxCaractere()) != -1  && (Character.isDigit(proxChar) || proxChar == '.')){
                            buffer.append((char)proxChar);
                            if (proxChar == '.') {
                                isReal = true;
                            }
                        }
                        ldat.caractereAnterior();
                        return isReal ? new Token(buffer.toString(), TipoToken.NumReal, linhaAtual) :
                                new Token(buffer.toString(), TipoToken.NumInt, linhaAtual);
                    }
                    else if (Character.isUpperCase(ch)){
                        while ((proxChar = ldat.lerProxCaractere()) != -1 && Character.isLetterOrDigit(proxChar)){
                            buffer.append((char)proxChar);
                        }
                        ldat.caractereAnterior();
                    }

                    String palavra = buffer.toString();
                    return switch (palavra) {
                        case "ENTAO" -> new Token(palavra, TipoToken.PCEntao, linhaAtual);
                        case "ENQTO" -> new Token(palavra, TipoToken.PCEnqto, linhaAtual);
                        case "DEC" -> new Token(palavra, TipoToken.PCDec, linhaAtual);
                        case "PROG" -> new Token(palavra, TipoToken.PCProg, linhaAtual);
                        case "SE" -> new Token(palavra, TipoToken.PCSe, linhaAtual);
                        case "INI" -> new Token(palavra, TipoToken.PCIni, linhaAtual);
                        case "INT" -> new Token(palavra, TipoToken.PCInt, linhaAtual);
                        case "IMPRIMIR" -> new Token(palavra, TipoToken.PCImprimir, linhaAtual);
                        case "FIM" -> new Token(palavra, TipoToken.PCFim, linhaAtual);
                        case "REAL" -> new Token(palavra, TipoToken.PCReal, linhaAtual);
                        case "LER" -> new Token(palavra, TipoToken.PCLer, linhaAtual);
                        case "E" -> new Token(palavra, TipoToken.OpBoolE, linhaAtual);
                        case "OU" -> new Token(palavra, TipoToken.OpBoolOu, linhaAtual);
                        default -> {
                            System.out.println("Erro Léxico: desconhecido < " + palavra + " > linha " + linhaAtual);
                            yield null;
                        }
                    };
                }
            }
        }
        return null;
    }

}