// Generated from Gyh.g4 by ANTLR 4.7.2

    import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GyhParser}.
 */
public interface GyhListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GyhParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(GyhParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(GyhParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#listaDeclaracoes}.
	 * @param ctx the parse tree
	 */
	void enterListaDeclaracoes(GyhParser.ListaDeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#listaDeclaracoes}.
	 * @param ctx the parse tree
	 */
	void exitListaDeclaracoes(GyhParser.ListaDeclaracoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao(GyhParser.DeclaracaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao(GyhParser.DeclaracaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#tipoVar}.
	 * @param ctx the parse tree
	 */
	void enterTipoVar(GyhParser.TipoVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#tipoVar}.
	 * @param ctx the parse tree
	 */
	void exitTipoVar(GyhParser.TipoVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#expressaoAritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoAritmetica(GyhParser.ExpressaoAritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#expressaoAritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoAritmetica(GyhParser.ExpressaoAritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#termoAritmetico}.
	 * @param ctx the parse tree
	 */
	void enterTermoAritmetico(GyhParser.TermoAritmeticoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#termoAritmetico}.
	 * @param ctx the parse tree
	 */
	void exitTermoAritmetico(GyhParser.TermoAritmeticoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#fatorAritmetico}.
	 * @param ctx the parse tree
	 */
	void enterFatorAritmetico(GyhParser.FatorAritmeticoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#fatorAritmetico}.
	 * @param ctx the parse tree
	 */
	void exitFatorAritmetico(GyhParser.FatorAritmeticoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#expressaoRelacional}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoRelacional(GyhParser.ExpressaoRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#expressaoRelacional}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoRelacional(GyhParser.ExpressaoRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#termoRelacional}.
	 * @param ctx the parse tree
	 */
	void enterTermoRelacional(GyhParser.TermoRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#termoRelacional}.
	 * @param ctx the parse tree
	 */
	void exitTermoRelacional(GyhParser.TermoRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#operadorBooleano}.
	 * @param ctx the parse tree
	 */
	void enterOperadorBooleano(GyhParser.OperadorBooleanoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#operadorBooleano}.
	 * @param ctx the parse tree
	 */
	void exitOperadorBooleano(GyhParser.OperadorBooleanoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#listaComandos}.
	 * @param ctx the parse tree
	 */
	void enterListaComandos(GyhParser.ListaComandosContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#listaComandos}.
	 * @param ctx the parse tree
	 */
	void exitListaComandos(GyhParser.ListaComandosContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(GyhParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(GyhParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#comandoAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterComandoAtribuicao(GyhParser.ComandoAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#comandoAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitComandoAtribuicao(GyhParser.ComandoAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#comandoEntrada}.
	 * @param ctx the parse tree
	 */
	void enterComandoEntrada(GyhParser.ComandoEntradaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#comandoEntrada}.
	 * @param ctx the parse tree
	 */
	void exitComandoEntrada(GyhParser.ComandoEntradaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#comandoSaida}.
	 * @param ctx the parse tree
	 */
	void enterComandoSaida(GyhParser.ComandoSaidaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#comandoSaida}.
	 * @param ctx the parse tree
	 */
	void exitComandoSaida(GyhParser.ComandoSaidaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#comandoCondicao}.
	 * @param ctx the parse tree
	 */
	void enterComandoCondicao(GyhParser.ComandoCondicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#comandoCondicao}.
	 * @param ctx the parse tree
	 */
	void exitComandoCondicao(GyhParser.ComandoCondicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#comandoRepeticao}.
	 * @param ctx the parse tree
	 */
	void enterComandoRepeticao(GyhParser.ComandoRepeticaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#comandoRepeticao}.
	 * @param ctx the parse tree
	 */
	void exitComandoRepeticao(GyhParser.ComandoRepeticaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GyhParser#subAlgoritmo}.
	 * @param ctx the parse tree
	 */
	void enterSubAlgoritmo(GyhParser.SubAlgoritmoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GyhParser#subAlgoritmo}.
	 * @param ctx the parse tree
	 */
	void exitSubAlgoritmo(GyhParser.SubAlgoritmoContext ctx);
}