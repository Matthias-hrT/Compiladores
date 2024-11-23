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
        if (atual != null && atual.getTipo() == tipoEsperado) {
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
                System.out.printf("Erro sintático: Token '%s' ", erro.getTipo());
                System.exit(1);
            } else {
                System.out.println("Erro sintático: EOF inesperado.");
                System.exit(1);
            }
        }
    }

    private boolean parsePrograma() {
        return match(TipoToken.Delim) &&
                match(TipoToken.PCDec) &&
                parseListaDeclaracoes() &&
                match(TipoToken.Delim) &&
                match(TipoToken.PCProg) &&
                parseListaComandos();
    }

    private boolean parseListaDeclaracoes() {
        if (!parseDeclaracao()) return false;
        while (parseDeclaracao());
        return true;
    }

    private boolean parseDeclaracao() {
        return match(TipoToken.Var) && match(TipoToken.Delim) && parseTipoVar();
    }

    private boolean parseTipoVar() {
        return match(TipoToken.PCInt) || match(TipoToken.PCReal);
    }

    private boolean parseListaComandos() {
        if (!parseComando()) return false;
        while (parseComando());
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
        return match(TipoToken.PCSe) &&
                parseExpressaoRelacional() &&
                match(TipoToken.PCEntao) &&
                parseComando() &&
                parseComandoCondicao2();
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

    private boolean parseSubAlgoritmo() {
        if (!match(TipoToken.PCIni)) {
            return false;
        }
        if (!parseListaComandos()) {
            System.out.println("Erro sintático: 'Inesperado Token PCIni'");
            System.exit(1);
        }
        if (!match(TipoToken.PCFim)) {
            System.out.println("Erro sintático: 'Esperado Token PCFim'");
            System.exit(1);
        }
        return true;
    }

    private boolean parseExpressaoAritmetica() {
        if (parseTermoAritmetico()) return false;
        while (match(TipoToken.OpAritSoma) || match(TipoToken.OpAritSub)) {
            if (parseTermoAritmetico()) return false;
        }
        return true;
    }

    private boolean parseTermoAritmetico() {
        if (parseFatorAritmetico()) return true;
        while (match(TipoToken.OpAritMult) || match(TipoToken.OpAritDiv)) {
            if (parseFatorAritmetico()) return true;
        }
        return false;
    }

    private boolean parseFatorAritmetico() {
        if (match(TipoToken.NumInt) || match(TipoToken.NumReal) || match(TipoToken.Var)) {
            return false;
        } else if (match(TipoToken.AbrePar)) {
            if (!parseExpressaoAritmetica()) {
                System.out.println("Erro sintático: Expressão aritmética esperada após Token 'AbrePar' ");
                System.exit(1);
            }
            if (!match(TipoToken.FechaPar)) {
                System.out.println("Erro sintático: Esperado Token 'FechaPar'");
                System.exit(1);
            }
            return false;
        }
        return true;
    }

    private boolean parseExpressaoRelacional() {
        if (parseTermoRelacional()) {
            return false;
        }
        while (parseOperadorBooleano()) {
            if (parseTermoRelacional()) {
                System.out.println("Erro sintático: Termo esperado após operador booleano.");
                System.exit(1);
            }
        }
        return true;
    }

    private boolean parseTermoRelacional() {
        if (match(TipoToken.AbrePar)) {
            if (!parseExpressaoRelacional()) {
                System.out.println("Erro sintático: Expressão relacional esperada após '('");
                System.exit(1);
            }
            if (!match(TipoToken.FechaPar)) {
                System.out.println("Erro sintático: Esperado Token 'FechaPar' ");
                System.exit(1);
            }
            return false;
        } else return !parseExpressaoAritmetica() || !parseOpRel() || !parseExpressaoAritmetica();
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