// Generated from Gyh.g4 by ANTLR 4.7.2

    import java.util.ArrayList;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GyhParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, PC=13, OpAritMult=14, OpAritDiv=15, OpAritSoma=16, 
		OpAritSub=17, OpRel=18, OpBoolE=19, OpBoolOu=20, Delim=21, Atrib=22, AbrePar=23, 
		FechaPar=24, Var=25, NumInt=26, NumReal=27, Cadeia=28, Comment=29, WS=30;
	public static final int
		RULE_programa = 0, RULE_listaDeclaracoes = 1, RULE_declaracao = 2, RULE_tipoVar = 3, 
		RULE_expressaoAritmetica = 4, RULE_termoAritmetico = 5, RULE_fatorAritmetico = 6, 
		RULE_expressaoRelacional = 7, RULE_termoRelacional = 8, RULE_operadorBooleano = 9, 
		RULE_listaComandos = 10, RULE_comando = 11, RULE_comandoAtribuicao = 12, 
		RULE_comandoEntrada = 13, RULE_comandoSaida = 14, RULE_comandoCondicao = 15, 
		RULE_comandoRepeticao = 16, RULE_subAlgoritmo = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "listaDeclaracoes", "declaracao", "tipoVar", "expressaoAritmetica", 
			"termoAritmetico", "fatorAritmetico", "expressaoRelacional", "termoRelacional", 
			"operadorBooleano", "listaComandos", "comando", "comandoAtribuicao", 
			"comandoEntrada", "comandoSaida", "comandoCondicao", "comandoRepeticao", 
			"subAlgoritmo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'DEC'", "'PROG'", "'INT'", "'REAL'", "'LER'", "'IMPRIMIR'", "'SE'", 
			"'ENTAO'", "'SENAO'", "'ENQTO'", "'INI'", "'FIM'", null, "'*'", "'/'", 
			"'+'", "'-'", null, "'E'", "'OU'", "':'", "':='", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "PC", "OpAritMult", "OpAritDiv", "OpAritSoma", "OpAritSub", "OpRel", 
			"OpBoolE", "OpBoolOu", "Delim", "Atrib", "AbrePar", "FechaPar", "Var", 
			"NumInt", "NumReal", "Cadeia", "Comment", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Gyh.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private String _varNome;
	    private String _varTipo;
	    private String _varValor;
	    private Simbolo _varSimbolo;
	    private TabelaSimbolos tabelaSimbolos = new TabelaSimbolos();

	    private String _varSaida;
	    private String _varEntrada;

	    private String _varExp;
	    private String _conteudoExp;

	    private String _cond;
	    private ArrayList<Comando> listaTrue = new ArrayList<Comando>();
	    private ArrayList<Comando> listaFalse = new ArrayList<Comando>();

	    private ArrayList<Comando> listaCmd = new ArrayList<Comando>();
	    private ArrayList<Comando> listaAux = new ArrayList<Comando>();
	    private ProgramaGyh programa = new ProgramaGyh();

	    public void geraComando(){
	        programa.geradorC();
	    }

	    public void verificaVar(String nomeVar){
	        if (!tabelaSimbolos.contains(nomeVar)) System.out.println("Erro Semântico: variável não declarada " + nomeVar);
	    }


	public GyhParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramaContext extends ParserRuleContext {
		public List<TerminalNode> Delim() { return getTokens(GyhParser.Delim); }
		public TerminalNode Delim(int i) {
			return getToken(GyhParser.Delim, i);
		}
		public ListaDeclaracoesContext listaDeclaracoes() {
			return getRuleContext(ListaDeclaracoesContext.class,0);
		}
		public ListaComandosContext listaComandos() {
			return getRuleContext(ListaComandosContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(Delim);
			setState(37);
			match(T__0);
			setState(38);
			listaDeclaracoes();
			setState(39);
			match(Delim);
			setState(40);
			match(T__1);
			setState(41);
			listaComandos();
			programa.setTabelaVar(tabelaSimbolos);
			          System.out.println("Programa --->>> VarTable");
			          programa.setComando(listaCmd);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaDeclaracoesContext extends ParserRuleContext {
		public List<DeclaracaoContext> declaracao() {
			return getRuleContexts(DeclaracaoContext.class);
		}
		public DeclaracaoContext declaracao(int i) {
			return getRuleContext(DeclaracaoContext.class,i);
		}
		public ListaDeclaracoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaDeclaracoes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterListaDeclaracoes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitListaDeclaracoes(this);
		}
	}

	public final ListaDeclaracoesContext listaDeclaracoes() throws RecognitionException {
		ListaDeclaracoesContext _localctx = new ListaDeclaracoesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_listaDeclaracoes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(44);
				declaracao();
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Var );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaracaoContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(GyhParser.Var, 0); }
		public TerminalNode Delim() { return getToken(GyhParser.Delim, 0); }
		public TipoVarContext tipoVar() {
			return getRuleContext(TipoVarContext.class,0);
		}
		public DeclaracaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterDeclaracao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitDeclaracao(this);
		}
	}

	public final DeclaracaoContext declaracao() throws RecognitionException {
		DeclaracaoContext _localctx = new DeclaracaoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(Var);
			setState(50);
			match(Delim);
			setState(51);
			tipoVar();

			             _varNome = _input.LT(-3).getText();
			             _varTipo = _input.LT(-1).getText();
			             _varValor = null;
			             _varSimbolo = new Simbolo(_varNome, _varTipo, _varValor);
			             if (!tabelaSimbolos.contains(_varNome)){
			                 tabelaSimbolos.add(_varSimbolo);
			                 System.out.println("Simbolo adicionado " + _varSimbolo);
			                } else {
			                 System.out.println("Erro Semântico, tentando add novamente " + _varSimbolo);
			             }
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoVarContext extends ParserRuleContext {
		public TipoVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterTipoVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitTipoVar(this);
		}
	}

	public final TipoVarContext tipoVar() throws RecognitionException {
		TipoVarContext _localctx = new TipoVarContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipoVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoAritmeticaContext extends ParserRuleContext {
		public List<TermoAritmeticoContext> termoAritmetico() {
			return getRuleContexts(TermoAritmeticoContext.class);
		}
		public TermoAritmeticoContext termoAritmetico(int i) {
			return getRuleContext(TermoAritmeticoContext.class,i);
		}
		public List<TerminalNode> OpAritSoma() { return getTokens(GyhParser.OpAritSoma); }
		public TerminalNode OpAritSoma(int i) {
			return getToken(GyhParser.OpAritSoma, i);
		}
		public List<TerminalNode> OpAritSub() { return getTokens(GyhParser.OpAritSub); }
		public TerminalNode OpAritSub(int i) {
			return getToken(GyhParser.OpAritSub, i);
		}
		public ExpressaoAritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoAritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterExpressaoAritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitExpressaoAritmetica(this);
		}
	}

	public final ExpressaoAritmeticaContext expressaoAritmetica() throws RecognitionException {
		ExpressaoAritmeticaContext _localctx = new ExpressaoAritmeticaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expressaoAritmetica);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			termoAritmetico();
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritSoma || _la==OpAritSub) {
				{
				{
				setState(57);
				_la = _input.LA(1);
				if ( !(_la==OpAritSoma || _la==OpAritSub) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				                    _conteudoExp += _input.LT(-1).getText();
				setState(59);
				termoAritmetico();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoAritmeticoContext extends ParserRuleContext {
		public List<FatorAritmeticoContext> fatorAritmetico() {
			return getRuleContexts(FatorAritmeticoContext.class);
		}
		public FatorAritmeticoContext fatorAritmetico(int i) {
			return getRuleContext(FatorAritmeticoContext.class,i);
		}
		public List<TerminalNode> OpAritMult() { return getTokens(GyhParser.OpAritMult); }
		public TerminalNode OpAritMult(int i) {
			return getToken(GyhParser.OpAritMult, i);
		}
		public List<TerminalNode> OpAritDiv() { return getTokens(GyhParser.OpAritDiv); }
		public TerminalNode OpAritDiv(int i) {
			return getToken(GyhParser.OpAritDiv, i);
		}
		public TermoAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterTermoAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitTermoAritmetico(this);
		}
	}

	public final TermoAritmeticoContext termoAritmetico() throws RecognitionException {
		TermoAritmeticoContext _localctx = new TermoAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_termoAritmetico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			fatorAritmetico();
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpAritMult || _la==OpAritDiv) {
				{
				{
				setState(66);
				_la = _input.LA(1);
				if ( !(_la==OpAritMult || _la==OpAritDiv) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				                _conteudoExp += _input.LT(-1).getText();
				setState(68);
				fatorAritmetico();
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FatorAritmeticoContext extends ParserRuleContext {
		public TerminalNode NumInt() { return getToken(GyhParser.NumInt, 0); }
		public TerminalNode NumReal() { return getToken(GyhParser.NumReal, 0); }
		public TerminalNode Var() { return getToken(GyhParser.Var, 0); }
		public TerminalNode AbrePar() { return getToken(GyhParser.AbrePar, 0); }
		public ExpressaoAritmeticaContext expressaoAritmetica() {
			return getRuleContext(ExpressaoAritmeticaContext.class,0);
		}
		public TerminalNode FechaPar() { return getToken(GyhParser.FechaPar, 0); }
		public FatorAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fatorAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterFatorAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitFatorAritmetico(this);
		}
	}

	public final FatorAritmeticoContext fatorAritmetico() throws RecognitionException {
		FatorAritmeticoContext _localctx = new FatorAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fatorAritmetico);
		try {
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NumInt:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(NumInt);
				 _conteudoExp += _input.LT(-1).getText(); 
				}
				break;
			case NumReal:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				match(NumReal);
				 _conteudoExp += _input.LT(-1).getText();
				}
				break;
			case Var:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				match(Var);
				 verificaVar(_input.LT(-1).getText()); _conteudoExp += _input.LT(-1).getText(); 
				}
				break;
			case AbrePar:
				enterOuterAlt(_localctx, 4);
				{
				setState(80);
				match(AbrePar);
				 _conteudoExp += _input.LT(-1).getText();
				setState(82);
				expressaoAritmetica();
				setState(83);
				match(FechaPar);
				 _conteudoExp += _input.LT(-1).getText();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoRelacionalContext extends ParserRuleContext {
		public List<TermoRelacionalContext> termoRelacional() {
			return getRuleContexts(TermoRelacionalContext.class);
		}
		public TermoRelacionalContext termoRelacional(int i) {
			return getRuleContext(TermoRelacionalContext.class,i);
		}
		public List<OperadorBooleanoContext> operadorBooleano() {
			return getRuleContexts(OperadorBooleanoContext.class);
		}
		public OperadorBooleanoContext operadorBooleano(int i) {
			return getRuleContext(OperadorBooleanoContext.class,i);
		}
		public ExpressaoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterExpressaoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitExpressaoRelacional(this);
		}
	}

	public final ExpressaoRelacionalContext expressaoRelacional() throws RecognitionException {
		ExpressaoRelacionalContext _localctx = new ExpressaoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			termoRelacional();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpBoolE || _la==OpBoolOu) {
				{
				{
				setState(89);
				operadorBooleano();
				 switch(_input.LT(-1).getText()){
				                        case "E": _cond += " && ";
				                           break;
				                         case "OU": _cond += " || ";
				                            break;
				                         default: break;
				                        }
				                     
				setState(91);
				termoRelacional();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoRelacionalContext extends ParserRuleContext {
		public List<ExpressaoAritmeticaContext> expressaoAritmetica() {
			return getRuleContexts(ExpressaoAritmeticaContext.class);
		}
		public ExpressaoAritmeticaContext expressaoAritmetica(int i) {
			return getRuleContext(ExpressaoAritmeticaContext.class,i);
		}
		public TerminalNode OpRel() { return getToken(GyhParser.OpRel, 0); }
		public TerminalNode AbrePar() { return getToken(GyhParser.AbrePar, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public TerminalNode FechaPar() { return getToken(GyhParser.FechaPar, 0); }
		public TermoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterTermoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitTermoRelacional(this);
		}
	}

	public final TermoRelacionalContext termoRelacional() throws RecognitionException {
		TermoRelacionalContext _localctx = new TermoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_termoRelacional);
		try {
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				 _conteudoExp = "";
				setState(99);
				expressaoAritmetica();
				setState(100);
				match(OpRel);

				                    _cond += _conteudoExp + _input.LT(-1).getText();
				                    _conteudoExp = "";
				                
				setState(102);
				expressaoAritmetica();
				 _cond += _conteudoExp; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(AbrePar);
				 _cond += _input.LT(-1).getText();
				setState(107);
				expressaoRelacional();
				setState(108);
				match(FechaPar);
				 _cond += _input.LT(-1).getText();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperadorBooleanoContext extends ParserRuleContext {
		public TerminalNode OpBoolE() { return getToken(GyhParser.OpBoolE, 0); }
		public TerminalNode OpBoolOu() { return getToken(GyhParser.OpBoolOu, 0); }
		public OperadorBooleanoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operadorBooleano; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterOperadorBooleano(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitOperadorBooleano(this);
		}
	}

	public final OperadorBooleanoContext operadorBooleano() throws RecognitionException {
		OperadorBooleanoContext _localctx = new OperadorBooleanoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_operadorBooleano);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_la = _input.LA(1);
			if ( !(_la==OpBoolE || _la==OpBoolOu) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaComandosContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ListaComandosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaComandos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterListaComandos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitListaComandos(this);
		}
	}

	public final ListaComandosContext listaComandos() throws RecognitionException {
		ListaComandosContext _localctx = new ListaComandosContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_listaComandos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(115);
				comando();
				 listaCmd.addAll(listaAux);listaAux.clear();
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__9) | (1L << T__10) | (1L << Var))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoContext extends ParserRuleContext {
		public ComandoAtribuicaoContext comandoAtribuicao() {
			return getRuleContext(ComandoAtribuicaoContext.class,0);
		}
		public ComandoEntradaContext comandoEntrada() {
			return getRuleContext(ComandoEntradaContext.class,0);
		}
		public ComandoSaidaContext comandoSaida() {
			return getRuleContext(ComandoSaidaContext.class,0);
		}
		public ComandoCondicaoContext comandoCondicao() {
			return getRuleContext(ComandoCondicaoContext.class,0);
		}
		public ComandoRepeticaoContext comandoRepeticao() {
			return getRuleContext(ComandoRepeticaoContext.class,0);
		}
		public SubAlgoritmoContext subAlgoritmo() {
			return getRuleContext(SubAlgoritmoContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comando);
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				comandoAtribuicao();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				comandoEntrada();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				comandoSaida();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				comandoCondicao();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(126);
				comandoRepeticao();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(127);
				subAlgoritmo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoAtribuicaoContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(GyhParser.Var, 0); }
		public TerminalNode Atrib() { return getToken(GyhParser.Atrib, 0); }
		public ExpressaoAritmeticaContext expressaoAritmetica() {
			return getRuleContext(ExpressaoAritmeticaContext.class,0);
		}
		public ComandoAtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoAtribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterComandoAtribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitComandoAtribuicao(this);
		}
	}

	public final ComandoAtribuicaoContext comandoAtribuicao() throws RecognitionException {
		ComandoAtribuicaoContext _localctx = new ComandoAtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_comandoAtribuicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(Var);
			 verificaVar(_input.LT(-1).getText()); _varExp = _input.LT(-1).getText() ;
			setState(132);
			match(Atrib);
			_conteudoExp = "";
			setState(134);
			expressaoAritmetica();

			                        ComandoAtribuicao cmd = new ComandoAtribuicao(_varExp, _conteudoExp);
			                        listaAux.add(cmd);
			                   
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoEntradaContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(GyhParser.Var, 0); }
		public ComandoEntradaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoEntrada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterComandoEntrada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitComandoEntrada(this);
		}
	}

	public final ComandoEntradaContext comandoEntrada() throws RecognitionException {
		ComandoEntradaContext _localctx = new ComandoEntradaContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comandoEntrada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__4);
			setState(138);
			match(Var);

			                    verificaVar(_input.LT(-1).getText());
			                    _varEntrada = _input.LT(-1).getText();
			                    ComandoLeitura cmd = new ComandoLeitura(_varEntrada);
			                    listaAux.add(cmd);
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoSaidaContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(GyhParser.Var, 0); }
		public TerminalNode Cadeia() { return getToken(GyhParser.Cadeia, 0); }
		public ComandoSaidaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoSaida; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterComandoSaida(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitComandoSaida(this);
		}
	}

	public final ComandoSaidaContext comandoSaida() throws RecognitionException {
		ComandoSaidaContext _localctx = new ComandoSaidaContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comandoSaida);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(T__5);
				setState(142);
				match(Var);

				                verificaVar(_input.LT(-1).getText());
				                _varSaida = _input.LT(-1).getText();
				                ComandoSaida cmd = new ComandoSaida(_varSaida);
				                listaAux.add(cmd);
				            
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				match(T__5);
				setState(145);
				match(Cadeia);

				                _varSaida = _input.LT(-1).getText();
				                ComandoSaida cmd = new ComandoSaida(_varSaida);
				                listaAux.add(cmd);
				            
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoCondicaoContext extends ParserRuleContext {
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ComandoCondicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoCondicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterComandoCondicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitComandoCondicao(this);
		}
	}

	public final ComandoCondicaoContext comandoCondicao() throws RecognitionException {
		ComandoCondicaoContext _localctx = new ComandoCondicaoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_comandoCondicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(T__6);
			 _cond = ""; listaTrue.clear(); listaFalse.clear();
			setState(151);
			expressaoRelacional();
			setState(152);
			match(T__7);
			setState(153);
			comando();
			 listaTrue.addAll(listaAux); listaAux.clear(); 
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(155);
				match(T__8);
				setState(156);
				comando();
				listaFalse.addAll(listaAux); listaAux.clear();
				}
				break;
			}

			                    ComandoCondicao cmd = new ComandoCondicao(_cond, new ArrayList<>(listaTrue), new ArrayList<>(listaFalse));
			                    listaAux.add(cmd);
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoRepeticaoContext extends ParserRuleContext {
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public ComandoContext comando() {
			return getRuleContext(ComandoContext.class,0);
		}
		public ComandoRepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoRepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterComandoRepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitComandoRepeticao(this);
		}
	}

	public final ComandoRepeticaoContext comandoRepeticao() throws RecognitionException {
		ComandoRepeticaoContext _localctx = new ComandoRepeticaoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_comandoRepeticao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__9);
			 _cond = "";
			setState(165);
			expressaoRelacional();
			setState(166);
			comando();

			                        ComandoRepeticao cmd = new ComandoRepeticao(_cond, new ArrayList<>(listaAux));
			                        listaAux.clear();
			                        listaAux.add(cmd);
			                  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubAlgoritmoContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public SubAlgoritmoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subAlgoritmo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).enterSubAlgoritmo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhListener ) ((GyhListener)listener).exitSubAlgoritmo(this);
		}
	}

	public final SubAlgoritmoContext subAlgoritmo() throws RecognitionException {
		SubAlgoritmoContext _localctx = new SubAlgoritmoContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_subAlgoritmo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__10);
			setState(171); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(170);
				comando();
				}
				}
				setState(173); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__9) | (1L << T__10) | (1L << Var))) != 0) );
			setState(175);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00b4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3\60\n\3\r\3\16\3\61"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\7\6?\n\6\f\6\16\6B\13\6\3"+
		"\7\3\7\3\7\3\7\7\7H\n\7\f\7\16\7K\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\5\bY\n\b\3\t\3\t\3\t\3\t\3\t\7\t`\n\t\f\t\16\tc\13\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nr\n\n\3\13\3\13"+
		"\3\f\3\f\3\f\6\fy\n\f\r\f\16\fz\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0083\n\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\5\20\u0096\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u00a2\n\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\6\23\u00ae\n\23\r\23\16\23\u00af\3\23\3\23\3\23\2\2\24\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$\2\6\3\2\5\6\3\2\22\23\3\2\20\21\3\2"+
		"\25\26\2\u00b2\2&\3\2\2\2\4/\3\2\2\2\6\63\3\2\2\2\b8\3\2\2\2\n:\3\2\2"+
		"\2\fC\3\2\2\2\16X\3\2\2\2\20Z\3\2\2\2\22q\3\2\2\2\24s\3\2\2\2\26x\3\2"+
		"\2\2\30\u0082\3\2\2\2\32\u0084\3\2\2\2\34\u008b\3\2\2\2\36\u0095\3\2\2"+
		"\2 \u0097\3\2\2\2\"\u00a5\3\2\2\2$\u00ab\3\2\2\2&\'\7\27\2\2\'(\7\3\2"+
		"\2()\5\4\3\2)*\7\27\2\2*+\7\4\2\2+,\5\26\f\2,-\b\2\1\2-\3\3\2\2\2.\60"+
		"\5\6\4\2/.\3\2\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\5\3\2\2"+
		"\2\63\64\7\33\2\2\64\65\7\27\2\2\65\66\5\b\5\2\66\67\b\4\1\2\67\7\3\2"+
		"\2\289\t\2\2\29\t\3\2\2\2:@\5\f\7\2;<\t\3\2\2<=\b\6\1\2=?\5\f\7\2>;\3"+
		"\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\13\3\2\2\2B@\3\2\2\2CI\5\16\b\2"+
		"DE\t\4\2\2EF\b\7\1\2FH\5\16\b\2GD\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2"+
		"\2J\r\3\2\2\2KI\3\2\2\2LM\7\34\2\2MY\b\b\1\2NO\7\35\2\2OY\b\b\1\2PQ\7"+
		"\33\2\2QY\b\b\1\2RS\7\31\2\2ST\b\b\1\2TU\5\n\6\2UV\7\32\2\2VW\b\b\1\2"+
		"WY\3\2\2\2XL\3\2\2\2XN\3\2\2\2XP\3\2\2\2XR\3\2\2\2Y\17\3\2\2\2Za\5\22"+
		"\n\2[\\\5\24\13\2\\]\b\t\1\2]^\5\22\n\2^`\3\2\2\2_[\3\2\2\2`c\3\2\2\2"+
		"a_\3\2\2\2ab\3\2\2\2b\21\3\2\2\2ca\3\2\2\2de\b\n\1\2ef\5\n\6\2fg\7\24"+
		"\2\2gh\b\n\1\2hi\5\n\6\2ij\b\n\1\2jr\3\2\2\2kl\7\31\2\2lm\b\n\1\2mn\5"+
		"\20\t\2no\7\32\2\2op\b\n\1\2pr\3\2\2\2qd\3\2\2\2qk\3\2\2\2r\23\3\2\2\2"+
		"st\t\5\2\2t\25\3\2\2\2uv\5\30\r\2vw\b\f\1\2wy\3\2\2\2xu\3\2\2\2yz\3\2"+
		"\2\2zx\3\2\2\2z{\3\2\2\2{\27\3\2\2\2|\u0083\5\32\16\2}\u0083\5\34\17\2"+
		"~\u0083\5\36\20\2\177\u0083\5 \21\2\u0080\u0083\5\"\22\2\u0081\u0083\5"+
		"$\23\2\u0082|\3\2\2\2\u0082}\3\2\2\2\u0082~\3\2\2\2\u0082\177\3\2\2\2"+
		"\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\31\3\2\2\2\u0084\u0085"+
		"\7\33\2\2\u0085\u0086\b\16\1\2\u0086\u0087\7\30\2\2\u0087\u0088\b\16\1"+
		"\2\u0088\u0089\5\n\6\2\u0089\u008a\b\16\1\2\u008a\33\3\2\2\2\u008b\u008c"+
		"\7\7\2\2\u008c\u008d\7\33\2\2\u008d\u008e\b\17\1\2\u008e\35\3\2\2\2\u008f"+
		"\u0090\7\b\2\2\u0090\u0091\7\33\2\2\u0091\u0096\b\20\1\2\u0092\u0093\7"+
		"\b\2\2\u0093\u0094\7\36\2\2\u0094\u0096\b\20\1\2\u0095\u008f\3\2\2\2\u0095"+
		"\u0092\3\2\2\2\u0096\37\3\2\2\2\u0097\u0098\7\t\2\2\u0098\u0099\b\21\1"+
		"\2\u0099\u009a\5\20\t\2\u009a\u009b\7\n\2\2\u009b\u009c\5\30\r\2\u009c"+
		"\u00a1\b\21\1\2\u009d\u009e\7\13\2\2\u009e\u009f\5\30\r\2\u009f\u00a0"+
		"\b\21\1\2\u00a0\u00a2\3\2\2\2\u00a1\u009d\3\2\2\2\u00a1\u00a2\3\2\2\2"+
		"\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\21\1\2\u00a4!\3\2\2\2\u00a5\u00a6\7"+
		"\f\2\2\u00a6\u00a7\b\22\1\2\u00a7\u00a8\5\20\t\2\u00a8\u00a9\5\30\r\2"+
		"\u00a9\u00aa\b\22\1\2\u00aa#\3\2\2\2\u00ab\u00ad\7\r\2\2\u00ac\u00ae\5"+
		"\30\r\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7\16\2\2\u00b2%\3\2\2\2"+
		"\r\61@IXaqz\u0082\u0095\u00a1\u00af";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}