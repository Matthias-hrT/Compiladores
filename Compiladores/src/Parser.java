public class Parser {
    private final ListaTokens listaTokens;

    public Parser(ListaTokens tokens) {
        this.listaTokens = tokens;
    }

    private boolean match(TipoToken tipoEsperado) {
        if (listaTokens.atual().getTipo() == TipoToken.Comentario) {
            listaTokens.avancar();
        }
        Token atual = listaTokens.atual();
        System.out.println("Token atual: " + atual);
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
            Token erro = listaTokens.atual();
            if (erro != null) {
                System.out.printf("Erro sintático: Token '%s'", erro.getTipo());
                System.exit(1);
            } else {
                System.out.println("Erro sintático: EOF inesperado.");
                System.exit(1);
            }
        }
    }

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

    private boolean parseListaDeclaracoes() {
        if (parseDeclaracao()) return parseListaDeclaracoes2();
        return false;
    }
    private boolean parseListaDeclaracoes2() {
        if (parseDeclaracao()) return parseListaDeclaracoes2();
        return true;
    }

    private boolean parseDeclaracao() {
        return match(TipoToken.Var) && match(TipoToken.Delim) && parseTipoVar();
    }

    private boolean parseTipoVar() {
        return match(TipoToken.PCInt) || match(TipoToken.PCReal);
    }

    private boolean parseListaComandos() {
        if (parseComando()) return parseListaComandos2();
        return false;
    }
    private boolean parseListaComandos2() {
        if (parseComando()) return parseListaComandos2();
        return true;
    }

    private boolean parseComando() {
        return parseComandoAtribuicao() ||
                parseComandoEntrada() ||
                parseComandoSaida() ||
                parseComandoCondicao() ||
                parseComandoRepeticao() ||
                parseSubAlgoritmo();
    }

    private boolean parseComandoAtribuicao() {
        return match(TipoToken.Var) && match(TipoToken.Atrib) && parseExpressaoAritmetica();
    }

    private boolean parseComandoEntrada() {
        return match(TipoToken.PCLer) && match(TipoToken.Var);
    }

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

    private boolean parseComandoCondicao() {
        if (match(TipoToken.PCSe)) {
            parseExpressaoRelacional();
            if (match(TipoToken.PCEntao)) {
                if (parseComando()) return parseComandoCondicao2();
            }
        }
        return false;
    }

    private boolean parseComandoCondicao2() {
        if (match(TipoToken.PCSenao)) {
            return parseComando();
        }
        return true;
    }

    private boolean parseComandoRepeticao() {
        return match(TipoToken.PCEnqto) && parseExpressaoRelacional() && parseComando();
    }

    //SubAlgoritmo → 'INI' ListaComandos 'FIM'
    private boolean parseSubAlgoritmo() {
        if (match(TipoToken.PCIni)) {
            if (parseListaComandos()) return match(TipoToken.PCFim);
        }
        return false;
    }

    private boolean parseExpressaoAritmetica() {
        if (parseTermoAritmetico()) return parseExpressaoAritmetica2();
        return false;
    }
    private boolean parseExpressaoAritmetica2() {
        if (match(TipoToken.OpAritSoma) || match(TipoToken.OpAritSub)) {
            if (parseTermoAritmetico()) return parseExpressaoAritmetica2();
        }
        return true;
    }

    private boolean parseTermoAritmetico() {
        if (parseFatorAritmetico()) return parseTermoAritmetico2();
        return false;
    }

    private boolean parseTermoAritmetico2() {
        if (match(TipoToken.OpAritMult) || match(TipoToken.OpAritDiv)) {
            if (parseTermoAritmetico()) return parseTermoAritmetico2();
        }
        return true;
    }

    private boolean parseFatorAritmetico() {
        if (match(TipoToken.NumInt) || match(TipoToken.NumReal) || match(TipoToken.Var)) return true;
        if (match(TipoToken.AbrePar)) {
            if(parseExpressaoAritmetica()) return match(TipoToken.FechaPar);
        }
        return false;
    }

    private boolean parseExpressaoRelacional() {
        if (parseTermoRelacional()) return parseExpressaoRelacional2();
        return false;
    }
    private boolean parseExpressaoRelacional2() {
        if (parseOperadorBooleano()){
            if (parseTermoRelacional()) return parseExpressaoRelacional2();
        }
       return true;
    }

    private boolean parseTermoRelacional() {
        if (match(TipoToken.AbrePar)) {
            if (parseExpressaoRelacional()) return match(TipoToken.FechaPar);
        }
        if (parseExpressaoAritmetica()){
            if (parseOpRel()) return parseExpressaoAritmetica();
        }
        return false;
    }

    private boolean parseOperadorBooleano() {
        return match(TipoToken.OpBoolE) || match(TipoToken.OpBoolOu);
    }

    private boolean parseOpRel() {
        return match(TipoToken.OpRelMenor) ||
                match(TipoToken.OpRelMenorIgual) ||
                match(TipoToken.OpRelMaior) ||
                match(TipoToken.OpRelMaiorIgual) ||
                match(TipoToken.OpRelIgual) ||
                match(TipoToken.OpRelDif);
    }
}