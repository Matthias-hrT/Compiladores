public class Parser {
    private final ListaTokens listaTokens;

    public Parser(ListaTokens tokens) {
        this.listaTokens = tokens;
    }

    private boolean match(TipoToken tipoEsperado) {
        Token atual = listaTokens.atual();
        if (atual.getTipo() == TipoToken.Comentario) {
            listaTokens.avancar();
            atual = listaTokens.atual();
        }
        //System.out.println("Token atual: " + atual);
        if (atual.getTipo() == tipoEsperado) {
            listaTokens.avancar();
            return true;
        }
        return false;
    }

    public void analPrograma() {
        if (parsePrograma() && (listaTokens.atual() == null || listaTokens.atual().getTipo() == TipoToken.EOF)) {
            System.out.println("Análise sintática concluída com sucesso!");
        } else {
            System.out.println("Erro sintático: Token '" + listaTokens.atual().getTipo() + "'");
            System.exit(1);
        }
    }

    //Programa → ':' 'DEC' ListaDeclaracoes ':' 'PROG' ListaComandos
    private boolean parsePrograma() {
        if (match(TipoToken.Delim)) { // ':'
            if (match(TipoToken.PCDec)) { // 'DEC'
                if (parseListaDeclaracoes()) { // ListaDeclaracoes
                    if (match(TipoToken.Delim)) { // ':'
                        if (match(TipoToken.PCProg)) { // 'PROG'
                            return parseListaComandos(); // ListaComandos
                        }
                    }
                }
            }
        }
        return false; // Falha se qualquer condição não for atendida
    }

    //ListaDeclaracoes →  Declaracao ListaDeclaracoes2
    private boolean parseListaDeclaracoes() {
        if (parseDeclaracao()) return parseListaDeclaracoes2();
        return false;
    }

    //ListaDeclaracoes2 →  Declaracao ListaDeclaracoes2 | vazio
    private boolean parseListaDeclaracoes2() {
        if (parseDeclaracao()) return parseListaDeclaracoes2();
        return true;
    }

    //Declaracao → VARIAVEL ':' TipoVar
    private boolean parseDeclaracao() {
        return match(TipoToken.Var) && match(TipoToken.Delim) && parseTipoVar();
    }

    //TipoVar → 'INT' | 'REAL'
    private boolean parseTipoVar() {
        return match(TipoToken.PCInt) || match(TipoToken.PCReal);
    }

    //ListaComandos → Comando ListaComandos2
    private boolean parseListaComandos() {
        if (parseComando()) return parseListaComandos2();
        return false;
    }

    //ListaComandos2 →  Comando ListaComandos2 | vazio
    private boolean parseListaComandos2() {
        if (parseComando()) return parseListaComandos2();
        return true;
    }

    //Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo
    private boolean parseComando() {
        return parseComandoAtribuicao() ||
                parseComandoEntrada() ||
                parseComandoSaida() ||
                parseComandoCondicao() ||
                parseComandoRepeticao() ||
                parseSubAlgoritmo();
    }

    //ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica
    private boolean parseComandoAtribuicao() {
        return match(TipoToken.Var) && match(TipoToken.Atrib) && parseExpressaoAritmetica();
    }

    //ComandoEntrada → 'LER' VARIAVEL
    private boolean parseComandoEntrada() {
        return match(TipoToken.PCLer) && match(TipoToken.Var);
    }

    //ComandoSaida → 'IMPRIMIR'  VARIAVEL | 'IMPRIMIR' CADEIA
    private boolean parseComandoSaida() {
        Token inicio = listaTokens.atual();
        if (match(TipoToken.PCImprimir)) {
            Token seguinte = listaTokens.atual();
            if (seguinte != null && seguinte.getLinha() == inicio.getLinha()){
                return match(TipoToken.Var) || match(TipoToken.Cadeia);
            } else {
                System.out.println("Erro sintático: 'Esperado Token Cadeia ou Variável'");
                System.exit(1);
            }
        }
        return false; // Parsing falhou
    }

    //Comando Condicao → 'SE' ExpressaoRelacional 'ENTAO' Comando ComandoCondicao2
    private boolean parseComandoCondicao() {
        if (match(TipoToken.PCSe)) {
            parseExpressaoRelacional();
            if (match(TipoToken.PCEntao)) {
                parseComando();
                return parseComandoCondicao2();
            }
        }
        return false;
    }

    //ComandoCondicao2 → 'SENAO' Comando | vazio
    private boolean parseComandoCondicao2() {
        if (match(TipoToken.PCSenao)) {
            return parseComando();
        }
        return true;
    }

    //ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando
    private boolean parseComandoRepeticao() {
        return match(TipoToken.PCEnqto) && parseExpressaoRelacional() && parseComando();
    }

    //SubAlgoritmo → 'INI' ListaComandos 'FIM'
    private boolean parseSubAlgoritmo() {
        if (match(TipoToken.PCIni)) {
            parseListaComandos();
            return match(TipoToken.PCFim);
        }
        return false;
    }

    //ExpressaoAritmetica → TermoAritimetico ExpressaoAritmetica2
    private boolean parseExpressaoAritmetica() {
        parseTermoAritmetico();
        return parseExpressaoAritmetica2();
    }

    //ExpressaoAritimetica2 →  ‘+’ TermoAritmetico ExpressaoAritmetica2 | ‘-’ TermoAritmetico ExpressaoAritmetica2 | vazio
    private boolean parseExpressaoAritmetica2() {
        if (match(TipoToken.OpAritSoma) || match(TipoToken.OpAritSub)) {
            parseTermoAritmetico();
            return parseExpressaoAritmetica2();
        }
        return true;
    }

    //TermoAritmetico → FatorAritmetico TermoAritmetico2
    private void parseTermoAritmetico() {
        parseFatorAritmetico();
        parseTermoAritmetico2();
    }

    //TemoAritimetico2 →  '*' FatorAritimetico TermoAritmetico2 |  ‘/’ FatorAritimetico TermoAritmetico2| vazio
    private boolean parseTermoAritmetico2() {
        if (match(TipoToken.OpAritMult) || match(TipoToken.OpAritDiv)) {
            parseTermoAritmetico();
            return parseTermoAritmetico2();
        }
        return true;
    }

    //FatorAritmetico → NUMINT| NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')'
    private void parseFatorAritmetico() {
        Token inicio = listaTokens.atual();
        if (match(TipoToken.AbrePar)) {
            parseExpressaoAritmetica();
            match(TipoToken.FechaPar);
            return;
        }
        if (!match(TipoToken.NumInt) && !match(TipoToken.NumReal)) {
            match(TipoToken.Var);
        }
    }

    //ExpressaoRelacional → TermoRelacional ExpressaoRelacional 2
    private boolean parseExpressaoRelacional() {
        if (parseTermoRelacional()) return parseExpressaoRelacional2();
        return false;
    }

    //ExpressaoRelacional2 →  OperadorBooleano TermoRelacional ExpressaoRelacional2| vazio
    private boolean parseExpressaoRelacional2() {
        if (parseOperadorBooleano()){
            parseTermoRelacional();
            return parseExpressaoRelacional2();
        }
       return true;
    }

    //TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | '(' ExpressaoRelacional ')'
    private boolean parseTermoRelacional() {
        if (match(TipoToken.AbrePar)) {
            parseExpressaoRelacional();
            return match(TipoToken.FechaPar);
        }
        if (parseExpressaoAritmetica()){
            parseOpRel();
            return parseExpressaoAritmetica();
        }
        return false;
    }

    //OperadorBooleano → 'E' | 'OU'
    private boolean parseOperadorBooleano() {
        return match(TipoToken.OpBoolE) || match(TipoToken.OpBoolOu);
    }

    //OP_REL
    private void parseOpRel() {
        if (!match(TipoToken.OpRelMenor) && !match(TipoToken.OpRelMenorIgual) && !match(TipoToken.OpRelMaior) && !match(TipoToken.OpRelMaiorIgual) && !match(TipoToken.OpRelIgual)) {
            match(TipoToken.OpRelDif);
        }
    }
}