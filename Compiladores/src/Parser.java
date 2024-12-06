public class Parser {
    private final ListaTokens listaTokens;
    private int contPar;
    private int contIni;

    public Parser(ListaTokens tokens) {
        this.listaTokens = tokens;
        this.contPar = 0;
        this.contIni = 0;
    }

    private boolean match(TipoToken tipoEsperado) {
        Token atual = listaTokens.atual();
        if (atual.getTipo() == TipoToken.Comentario) {
            listaTokens.avancar();
            atual = listaTokens.atual();
        }
        //System.out.println("Token atual: " + atual);
        if (atual.getTipo() == tipoEsperado) {
            if (atual.getTipo() == TipoToken.FechaPar) {
                contPar--;
            }
            if (atual.getTipo() == TipoToken.PCFim){
                contIni--;
            }
            listaTokens.avancar();
            return true;
        }
        return false;
    }

    public void analPrograma() {
        if (parsePrograma() && contIni == 0  && contPar == 0 && (listaTokens.atual() == null || listaTokens.atual().getTipo() == TipoToken.EOF)) {
            System.out.println("Análise sintática concluída com sucesso!");
        } else {

            Token erro = listaTokens.atual();
            int linhaAtual = erro != null ? erro.getLinha() : -1;

            listaTokens.avancar();

            while (listaTokens.atual() != null && linhaAtual == listaTokens.atual().getLinha()) {
                if (listaTokens.atual().getTipo() == TipoToken.FechaPar) {
                    listaTokens.avancar();
                    contPar--;
                } else {
                    listaTokens.avancar();
                }
            }

            while (listaTokens.atual() != null) {
                if (listaTokens.atual().getTipo() == TipoToken.PCFim) {
                    listaTokens.avancar();
                    contIni--;
                } else {
                    listaTokens.avancar();
                }
            }

            if (contPar > 0) {
                System.out.println("Erro sintático: Esperado Token 'FechaPar'");
                System.exit(1);
            }else if (contIni > 0) {
                System.out.println("Erro sintático: Esperado Token 'PCFim'");
            }else if (listaTokens.atual() != null) {
                assert erro != null;
                System.out.println("Erro sintático: Inesperado Token '" + erro.getTipo() + "'");
                System.exit(1);
            }else{
                assert erro != null;
                System.out.println("Erro sintático: Token '" + erro.getTipo() + "'");
                System.exit(1);
            }
        }
    }

    // Programa → ':' 'DEC' ListaDeclaracoes ':' 'PROG' ListaComandos
    private boolean parsePrograma() {
        if (match(TipoToken.Delim)) {
            if (match(TipoToken.PCDec)) {
                if (parseListaDeclaracoes()) {
                    if (match(TipoToken.Delim)) {
                        if (match(TipoToken.PCProg)) {
                            return parseListaComandos();
                        }
                    }
                }
            }
        }
        return false;
    }

    // ListaDeclaracoes → Declaracao ListaDeclaracoes2
    private boolean parseListaDeclaracoes() {
        if (parseDeclaracao()) return parseListaDeclaracoes2();
        return false;
    }

    // ListaDeclaracoes2 → Declaracao ListaDeclaracoes2 | vazio
    private boolean parseListaDeclaracoes2() {
        if (parseDeclaracao()) return parseListaDeclaracoes2();
        return true;
    }

    // Declaracao → VARIAVEL ':' TipoVar
    private boolean parseDeclaracao() {
        return match(TipoToken.Var) && match(TipoToken.Delim) && parseTipoVar();
    }

    // TipoVar → 'INT' | 'REAL'
    private boolean parseTipoVar() {
        return match(TipoToken.PCInt) || match(TipoToken.PCReal);
    }

    // ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica2
    private boolean parseExpressaoAritmetica() {
        parseTermoAritmetico();
        return parseExpressaoAritmetica2();
    }

    // ExpressaoAritmetica2 → '+' TermoAritmetico ExpressaoAritmetica2 | '-' TermoAritmetico ExpressaoAritmetica2 | vazio
    private boolean parseExpressaoAritmetica2() {
        if (match(TipoToken.OpAritSoma) || match(TipoToken.OpAritSub)) {
            parseTermoAritmetico();
            return parseExpressaoAritmetica2();
        }
        return true;
    }

    // TermoAritmetico → FatorAritmetico TermoAritmetico2
    private void parseTermoAritmetico() {
        parseFatorAritmetico();
        parseTermoAritmetico2();
    }

    // TermoAritmetico2 → '*' FatorAritmetico TermoAritmetico2 | '/' FatorAritmetico TermoAritmetico2 | vazio
    private boolean parseTermoAritmetico2() {
        if (match(TipoToken.OpAritMult) || match(TipoToken.OpAritDiv)) {
            parseTermoAritmetico();
            return parseTermoAritmetico2();
        }
        return true;
    }

    // FatorAritmetico → NUMINT | NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')'
    private void parseFatorAritmetico() {
        if (match(TipoToken.AbrePar)) {
            contPar++;
            parseExpressaoAritmetica();
            match(TipoToken.FechaPar);
            return;
        }
        if (!match(TipoToken.NumInt) && !match(TipoToken.NumReal)) {
            match(TipoToken.Var);
        }
    }

    // ExpressaoRelacional → TermoRelacional ExpressaoRelacional2
    private boolean parseExpressaoRelacional() {
        if (parseTermoRelacional()) return parseExpressaoRelacional2();
        return false;
    }

    // ExpressaoRelacional2 → OperadorBooleano TermoRelacional ExpressaoRelacional2 | vazio
    private boolean parseExpressaoRelacional2() {
        if (parseOperadorBooleano()) {
            parseTermoRelacional();
            return parseExpressaoRelacional2();
        }
        return true;
    }

    // TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | '(' ExpressaoRelacional ')'
    private boolean parseTermoRelacional() {
        if (match(TipoToken.AbrePar)) {
            contPar++;
            parseExpressaoRelacional();
            return match(TipoToken.FechaPar);
        }
        if (parseExpressaoAritmetica()) {
            parseOpRel();
            return parseExpressaoAritmetica();
        }
        return false;
    }

    // OperadorBooleano → 'E' | 'OU'
    private boolean parseOperadorBooleano() {
        return match(TipoToken.OpBoolE) || match(TipoToken.OpBoolOu);
    }

    // ListaComandos → Comando ListaComandos2
    private boolean parseListaComandos() {
        if (parseComando()) return parseListaComandos2();
        return false;
    }

    // ListaComandos2 → Comando ListaComandos2 | vazio
    private boolean parseListaComandos2() {
        if (parseComando()) return parseListaComandos2();
        return true;
    }

    // Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo
    private boolean parseComando() {
        return parseComandoAtribuicao() ||
                parseComandoEntrada() ||
                parseComandoSaida() ||
                parseComandoCondicao() ||
                parseComandoRepeticao() ||
                parseSubAlgoritmo();
    }

    // ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica
    private boolean parseComandoAtribuicao() {
        return match(TipoToken.Var) && match(TipoToken.Atrib) && parseExpressaoAritmetica();
    }

    // ComandoEntrada → 'LER' VARIAVEL
    private boolean parseComandoEntrada() {
        return match(TipoToken.PCLer) && match(TipoToken.Var);
    }

    // ComandoSaida → 'IMPRIMIR' VARIAVEL | 'IMPRIMIR' CADEIA
    private boolean parseComandoSaida() {
        Token inicio = listaTokens.atual();
        if (match(TipoToken.PCImprimir)) {
            Token seguinte = listaTokens.atual();
            if (seguinte != null && seguinte.getLinha() == inicio.getLinha()) {
                return match(TipoToken.Var) || match(TipoToken.Cadeia);
            } else {
                System.out.println("Erro sintático: 'Esperado Token Cadeia ou Variável'");
                System.exit(1);
            }
        }
        return false;
    }

    // ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando ComandoCondicao2
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

    // ComandoCondicao2 → 'SENAO' Comando | vazio
    private boolean parseComandoCondicao2() {
        if (match(TipoToken.PCSenao)) {
            return parseComando();
        }
        return true;
    }

    // ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando
    private boolean parseComandoRepeticao() {
        return match(TipoToken.PCEnqto) && parseExpressaoRelacional() && parseComando();
    }

    // SubAlgoritmo → 'INI' ListaComandos 'FIM'
    private boolean parseSubAlgoritmo() {
        if (match(TipoToken.PCIni)) {
            contIni++;
            parseListaComandos();
            return match(TipoToken.PCFim);
        }
        return false;
    }


    // OpRel → '==' | '<' | '>' | '<=' | '>=' | '<>'
    private boolean parseOpRel() {
        return match(TipoToken.OpRelIgual) ||
                match(TipoToken.OpRelMenor) ||
                match(TipoToken.OpRelMaior) ||
                match(TipoToken.OpRelMenorIgual) ||
                match(TipoToken.OpRelMaiorIgual) ||
                match(TipoToken.OpRelDif);
    }
}