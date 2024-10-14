
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
            if (ch == ' '){
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
                    else{
                        System.out.println("Erro: simbolo inexistente '"+ ch + "'" + " na linha " + linhaAtual);

                        ldat.caractereAnterior();
                        continue;
                    }
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
                    System.out.println("Erro: simbolo inexistente '"+ ch + "'" + " na linha " + linhaAtual);
                    continue;
                }

                //cases com Letras

                // E, ENTAO, ENQTO
                case 'E': {
                    proxChar = ldat.lerProxCaractere();

                    if (proxChar == 'N') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'T') {
                            proxChar = ldat.lerProxCaractere();
                            if (proxChar == 'A') {
                                proxChar = ldat.lerProxCaractere();
                                if (proxChar == 'O') {
                                    return new Token("ENTAO", TipoToken.PCEntao, linhaAtual);
                                }
                                ldat.caractereAnterior();
                            }
                            ldat.caractereAnterior();
                        }
                        if (proxChar == 'Q') {
                            proxChar = ldat.lerProxCaractere();
                            if (proxChar == 'T') {
                                proxChar = ldat.lerProxCaractere();
                                if (proxChar == 'O') {
                                    return new Token("ENQTO", TipoToken.PCEnqto, linhaAtual);
                                }
                                ldat.caractereAnterior();
                            }
                            ldat.caractereAnterior();
                        }
                        continue;
                    }
                    ldat.caractereAnterior();
                    return new Token("E", TipoToken.OpBoolE, linhaAtual);
                }
                // OU
                case 'O': {
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'U') {
                        return new Token("OU", TipoToken.OpBoolOu, linhaAtual);
                    }
                    else{
                        System.out.println("Erro: simbolo inexistente '"+ ch + "'" + " na linha " + linhaAtual);
                        ldat.caractereAnterior();
                        continue;
                    }
                }

                // DEC
                case 'D':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'E') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'C') {
                            return new Token("DEC", TipoToken.PCDec, linhaAtual);
                        }
                        ldat.caractereAnterior();
                    }
                    ldat.caractereAnterior();
                    continue;
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
                            ldat.caractereAnterior();
                            continue;
                        }
                        ldat.caractereAnterior();
                        continue;
                    }
                    ldat.caractereAnterior();
                    continue;
                }

                //SE
                case 'S':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'E') {
                        return new Token("SE", TipoToken.PCSe, linhaAtual);
                    }
                    System.out.println("Erro: simbolo inexistente '"+ ch + "'" + " na linha " + linhaAtual);
                    ldat.caractereAnterior();
                    continue;
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
                                            ldat.caractereAnterior();
                                            continue;
                                        }
                                        ldat.caractereAnterior();
                                        continue;
                                    }
                                    ldat.caractereAnterior();
                                    continue;
                                }
                                ldat.caractereAnterior();
                                continue;
                            }
                            ldat.caractereAnterior();
                            continue;
                        }
                        ldat.caractereAnterior();
                        continue;
                    }
                    ldat.caractereAnterior();
                    continue;
                }

                // FIM
                case 'F':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'I') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'M') {
                            return new Token("FIM", TipoToken.PCFim, linhaAtual);
                        }
                        ldat.caractereAnterior();
                        continue;
                    }
                    ldat.caractereAnterior();
                    continue;
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
                            ldat.caractereAnterior();
                            continue;
                        }
                        ldat.caractereAnterior();
                        continue;
                    }
                    ldat.caractereAnterior();
                    continue;
                }

                // LER
                case 'L':{
                    proxChar = ldat.lerProxCaractere();
                    if (proxChar == 'E') {
                        proxChar = ldat.lerProxCaractere();
                        if (proxChar == 'R') {
                            return new Token("LER", TipoToken.PCLer, linhaAtual);
                        }
                        ldat.caractereAnterior();
                        continue;
                    }
                    ldat.caractereAnterior();
                    continue;
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
                    break;
                }

                // # Comentário
                case '#':{
                    StringBuilder comentario = new StringBuilder();

                    while((proxChar = ldat.lerProxCaractere()) != -1){
                        if (proxChar == '\n'){
                            break;
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
                        while ((proxChar = ldat.lerProxCaractere()) != -1  &&
                                (Character.isDigit(proxChar) || proxChar == '.')){
                            isReal = true;
                            buffer.append((char)proxChar);
                        }

                        if (isReal){
                            return new Token(buffer.toString(), TipoToken.NumReal, linhaAtual);
                        } else {
                            return new Token(buffer.toString(), TipoToken.NumInt, linhaAtual);
                        }
                    }
                    break;
                }
            }
        }
        return null;
    }
}