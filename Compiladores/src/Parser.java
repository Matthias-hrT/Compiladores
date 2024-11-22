public class Parser {
    private final ListaTokens listaTokens;

    public Parser(ListaTokens tokens) {
        this.listaTokens = tokens;
    }

    private boolean match(TipoToken tipoEsperado) {
        while (listaTokens.atual() != null &&
                listaTokens.atual().getTipo() == TipoToken.Comentario) {
            listaTokens.avancar();
        }

        Token atual = listaTokens.atual();

        if (atual != null && atual.getTipo() == tipoEsperado) {
            // Apenas avança se o próximo token está na mesma linha ou se isso não importa.
            Token proximo = listaTokens.temProximo() ? listaTokens.avancar() : null;
            if (proximo == null || proximo.getLinha() == atual.getLinha()) {
                return true;
            } else {
                listaTokens.retroceder(); // Retrocede caso tenha avançado errado
            }
        }
        return false;
    }

    public void analPrograma() {
        boolean resultado = parsePrograma();

        // Verifique se o próximo token é EOF para garantir que não haja mais tokens inesperados
        Token erro = listaTokens.atual();
        if (resultado && (erro == null || erro.getTipo() == TipoToken.EOF)) {
            System.out.println("Análise sintática concluída com sucesso!");
        }
    }

    // Programa → ':' 'DEC' ListaDeclaracoes ':' 'PROG' ListaComandos
    private boolean parsePrograma(){
        if (match(TipoToken.Delim)){
            if (match(TipoToken.PCDec)){
                if (parseListaDeclapacoes()){
                    if (match(TipoToken.Delim)){
                        if (match(TipoToken.PCProg)){
                            return parseListaComandos();
                        }
                    }
                }
            }
        }
        return false; // Problema no parsec
    }

    // ListaDeclaracoes → Declaracao ListaDeclaracoes2
    private boolean parseListaDeclapacoes(){
        if (parseDeclaracao()){
            return parseListaDeclapacoes2();
        }
        return false;
    }

    // ListaDeclaracoes2 → Declaracao ListaDeclaracoes2 | vazio
    private boolean parseListaDeclapacoes2(){
        if (parseDeclaracao()){
            return parseListaDeclapacoes2();
        }
        return true; // vazio
    }

    // Declaracao → VARIAVEL ':' TipoVar
    private boolean parseDeclaracao(){
        if (match(TipoToken.Var)){
            if (match(TipoToken.Delim)){
                return parseTipoVar();
            }
        }
        return false;
    }

    // TipoVar → 'INT' | 'REAL'
    private boolean parseTipoVar(){
        return match(TipoToken.PCInt) || match(TipoToken.PCReal);
    }

    // ListaComandos → Comando ListaComandos2
    private boolean parseListaComandos(){
        if (parseComando()){
            return parseListaComandos2();
        }
        return false;
    }

    // ListaComandos2 → Comando ListaComandos2 | vazio
    private boolean parseListaComandos2(){
        if (parseComando()){
            return parseListaComandos2();
        }
        return true; // vazio
    }

    // Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo
    private boolean parseComando() {
        return parseComandoAtribuicao()
                || parseComandoEntrada()
                || parseComandoSaida()
                || parseComandoCondicao()
                || parseComandoRepeticao()
                || parseSubAlgoritmo();
    }

    // ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica2
    private boolean parseExpressaoAritmetica() {
        if (parseTermoAritmetico()) {
            return parseExpressaoAritmetica2();
        }
        return false; // Parsing falhou
    }

    // ExpressaoAritmetica2 → '+' TermoAritmetico ExpressaoAritmetica2 | '-' TermoAritmetico ExpressaoAritmetica2 | vazio
    private boolean parseExpressaoAritmetica2() {
        if (match(TipoToken.OpAritSoma) || match(TipoToken.OpAritSub)) { // '+' ou '-'
            if (parseTermoAritmetico()) {
                return parseExpressaoAritmetica2();
            }
            return false; // Operador sem Termo Aritmético
        }
        return true; // vazio
    }

    // TermoAritmetico → FatorAritmetico TermoAritmetico2
    private boolean parseTermoAritmetico() {
        if (parseFatorAritmetico()) {
            return parseTermoAritmetico2();
        }
        return false; // Parsing falhou
    }

    // TermoAritmetico2 → '*' FatorAritmetico TermoAritmetico2 | '/' FatorAritmetico TermoAritmetico2 | vazio
    private boolean parseTermoAritmetico2() {
        if (match(TipoToken.OpAritMult) || match(TipoToken.OpAritDiv)) { // '*' ou '/'
            if (parseFatorAritmetico()) {
                return parseTermoAritmetico2();
            }
            return false; // Operador sem Fator Aritmético
        }
        return true; // vazio
    }

    // FatorAritmetico → NUMINT | NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')'
    private boolean parseFatorAritmetico() {
        if (match(TipoToken.NumInt) || match(TipoToken.NumReal) || match(TipoToken.Var)) {
            return true; // É um fator válido
        } else if (match(TipoToken.AbrePar)) { // '(' ExpressaoAritmetica ')'
            if (parseExpressaoAritmetica()) {
                return match(TipoToken.FechaPar);
            }
        }
        return false; // Parsing falhou
    }

    // ExpressaoRelacional → TermoRelacional ExpressaoRelacional2
    private boolean parseExpressaoRelacional() {
        // Tenta analisar TermoRelacional
        if (parseTermoRelacional()) {
            // Tenta analisar ExpressaoRelacional2 (recursiva)
            return parseExpressaoRelacional2();
        }
        return false; // Parsing falhou
    }

    // ExpressaoRelacional2 → OperadorBooleano TermoRelacional ExpressaoRelacional2 | vazio
    private boolean parseExpressaoRelacional2() {
        // Verifica se há um operador booleano
        if (parseOperadorBooleano()) {
            // Após operador booleano, deve haver outro TermoRelacional
            if (parseTermoRelacional()) {
                // ExpressaoRelacional2 pode se repetir recursivamente
                return parseExpressaoRelacional2();
            }
            return false; // Operador booleano sem TermoRelacional
        }
        return true; // vazio
    }

    // TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | '(' ExpressaoRelacional ')'
    private boolean parseTermoRelacional() {
        // Primeira opção: ExpressaoAritmetica OP_REL ExpressaoAritmetica
        if (parseExpressaoAritmetica()) {
            if (parseOpRel()) { // OP_REL: operadores relacionais
                return parseExpressaoAritmetica(); // Espera outra expressão aritmética
            }
            return false; // Esperava OP_REL após ExpressaoAritmetica
        }
        // Segunda opção: '(' ExpressaoRelacional ')'
        else if (match(TipoToken.AbrePar)) { // Verifica abertura de parênteses
            if (parseExpressaoRelacional()) { // Analisa a ExpressaoRelacional dentro dos parênteses
                return match(TipoToken.FechaPar); // Verifica fechamento de parênteses
            }
        }
        return false; // Parsing falhou
    }

    // OpRel
    private boolean parseOpRel() {
        // Verifica se o token atual é um operador relacional
        return match(TipoToken.OpRelMenor)
                || match(TipoToken.OpRelMenorIgual)
                || match(TipoToken.OpRelMaior)
                || match(TipoToken.OpRelMaiorIgual)
                || match(TipoToken.OpRelIgual)
                || match(TipoToken.OpRelDif);
    }

    // OperadorBooleano → 'E' | 'OU'
    private boolean parseOperadorBooleano() {
        // Verifica os operadores booleanos permitidos: E ou OU
        return match(TipoToken.OpBoolE) || match(TipoToken.OpBoolOu);
    }

    // ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica;
    private boolean parseComandoAtribuicao(){
        if (match(TipoToken.Var)){
            if (match(TipoToken.Atrib)){
                return parseExpressaoAritmetica();
            }
        }
        return false; // nao parsecou
    }

    // ComandoEntrada → 'LER' VARIAVEL
    private boolean parseComandoEntrada(){
        if (match(TipoToken.PCLer)){
            return match(TipoToken.Var);
        }
        return false; // nao parsecou
    }

    // ComandoSaida → 'IMPRIMIR' VARIAVEL | 'IMPRIMIR' CADEIA
    private boolean parseComandoSaida() {
        Token inicio = listaTokens.atual();

        if (match(TipoToken.PCImprimir)) {
            Token seguinte = listaTokens.atual();
            if (seguinte != null && seguinte.getLinha() == inicio.getLinha() &&
                    (match(TipoToken.Var) || match(TipoToken.Cadeia))) {
                return true;
            } else {
                System.out.println("Erro: Comando 'IMPRIMIR' incompleto na linha " + inicio.getLinha());
                return false;
            }
        }
        return false; // Parsing falhou
    }


    // ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando ComandoCondicao2
    private boolean parseComandoCondicao() {
        if (match(TipoToken.PCSe)) {
            if (parseExpressaoRelacional()) {
                if (match(TipoToken.PCEntao)) {
                    if (parseComando()) {
                        return parseComandoCondicao2(); // Opcional, já que SENAO é vazio
                    }
                }
            }
        }
        return false; // Parsing falhou
    }

    // ComandoCondicao2 → 'SENAO' Comando | vazio
    private boolean parseComandoCondicao2() {
        if (match(TipoToken.PCSenao)) {
            return parseComando();
        }
        return true; // vazio
    }

    // ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando
    private boolean parseComandoRepeticao() {
        if (match(TipoToken.PCEnqto)) {
            if (parseExpressaoRelacional()) {
                return parseComando();
            }
        }
        return false; // Parsing falhou
    }

    // SubAlgoritmo → 'INI' ListaComandos 'FIM'
    private boolean parseSubAlgoritmo() {
        if (match(TipoToken.PCIni)) {
            if (parseListaComandos()) {
                return match(TipoToken.PCFim);
            }
        }
        return false; // Parsing falhou
    }
}
