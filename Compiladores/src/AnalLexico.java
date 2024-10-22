
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
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
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
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }

                //cases com Letras

                    /*
                // E, ENTAO, ENQTO
                case 'E': {
                    StringBuilder buffer = new StringBuilder();
                    proxChar = ldat.lerProxCaractere();
                    buffer.append(ch);
                    if (proxChar == 'N') {
                        buffer.append((char)proxChar);
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'T') {
                            buffer.append((char)proxChar);
                            proxChar = ldat.lerProxCaractere();
                            if (proxChar == 'A') {
                                buffer.append((char)proxChar);
                                proxChar = ldat.lerProxCaractere();
                                if (proxChar == 'O') {
                                    buffer.append((char)proxChar);
                                    return new Token(buffer.toString(), TipoToken.PCEntao, linhaAtual);
                                }
                            }
                        } else if (proxChar == 'Q') {
                            buffer.append((char)proxChar);
                            proxChar = ldat.lerProxCaractere();
                            if (proxChar == 'T') {
                                buffer.append((char)proxChar);
                                proxChar = ldat.lerProxCaractere();
                                if (proxChar == 'O') {
                                    buffer.append((char)proxChar);
                                    return new Token(buffer.toString(), TipoToken.PCEnqto, linhaAtual);
                                }
                            }
                        }
                    }
                    String palavra = buffer.toString();
                    System.out.println("Erro Léxico: desconhecido < " + palavra + " > na linha " + linhaAtual);
                    return null;
                }
                // OU
                case 'O': {
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'U') {
                        return new Token("OU", TipoToken.OpBoolOu, linhaAtual);
                    }
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }

                // DEC
                case 'D':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'E') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'C') {
                            return new Token("DEC", TipoToken.PCDec, linhaAtual);
                        }
                    }
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }

                // PROG
                case 'P':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'R') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'O') {
                            proxChar = ldat.lerProxCaractere();
                            if (proxChar == 'G') {
                                return new Token("PROG", TipoToken.PCProg, linhaAtual);
                            }
                        }
                    }
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }

                //SE
                case 'S':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'E') {
                        return new Token("SE", TipoToken.PCSe, linhaAtual);
                    }
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }

                // INI, INT, IMPRIMIR
                case 'I':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'N') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'I') {
                            return new Token("INI", TipoToken.PCIni, linhaAtual);
                        }else if(proxChar == 'T') {
                            return new Token("INT", TipoToken.PCInt, linhaAtual);
                        }
                    }else if(proxChar == 'M'){
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'P') {
                            proxChar = ldat.lerProxCaractere();
                            if (proxChar == 'R') {
                                proxChar = ldat.lerProxCaractere();
                                if (proxChar == 'I') {
                                    proxChar = ldat.lerProxCaractere();
                                    if(proxChar == 'M'){
                                        proxChar = ldat.lerProxCaractere();
                                        if (proxChar == 'I'){
                                            proxChar = ldat.lerProxCaractere();
                                            if (proxChar == 'R'){
                                                return new Token("IMPRIMIR", TipoToken.PCImprimir, linhaAtual);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }

                // FIM
                case 'F':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'I') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'M') {
                            return new Token("FIM", TipoToken.PCFim, linhaAtual);
                        }
                    }
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }

                // REAL
                case 'R':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'E') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'A') {
                            proxChar = ldat.lerProxCaractere();
                            if (proxChar == 'L') {
                                return new Token("REAL", TipoToken.PCReal, linhaAtual);
                            }
                        }
                    }
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }

                // LER
                case 'L':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'E') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'R') {
                            return new Token("LER", TipoToken.PCLer, linhaAtual);
                        }
                    }
                    System.out.println("Erro Léxico: < " + ch + " > na linha " + linhaAtual);
                    return null;
                }
*/
                // CADEIA
                case '"':{
                    StringBuilder cadeia = new StringBuilder();

                    while ((proxChar = ldat.lerProxCaractere()) != -1){
                        if (proxChar == '"'){
                            return new Token(cadeia.toString(), TipoToken.Cadeia, linhaAtual);
                       }
                        cadeia.append((char)proxChar);
                    }
                    System.out.println("Erro Léxico: desconhecido caractere "+ ch + " na linha " + linhaAtual);
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
                        if (isReal){
                            return new Token(buffer.toString(), TipoToken.NumReal, linhaAtual);
                        } else {
                            return new Token(buffer.toString(), TipoToken.NumInt, linhaAtual);
                        }
                    }
                    else if (Character.isUpperCase(ch)){
                        while ((proxChar = ldat.lerProxCaractere()) != -1 && Character.isUpperCase(proxChar)){
                            buffer.append((char)proxChar);
                        }
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
                        default -> {
                            System.out.println("Erro Léxico: caractere desconhecido < " + palavra + " > na linha " + linhaAtual);
                            yield null;
                        }
                    };
                }
            }
        }
        return null;
    }

}