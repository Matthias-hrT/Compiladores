grammar Gyh;

@header{
    import java.util.ArrayList;
}

@members{
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

}

programa: Delim 'DEC' listaDeclaracoes Delim 'PROG' listaComandos
          {programa.setTabelaVar(tabelaSimbolos);
          System.out.println("Programa --->>> VarTable");
          programa.setComando(listaCmd);};

listaDeclaracoes: (declaracao)+;

declaracao: Var Delim tipoVar{
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
            };

tipoVar: 'INT' | 'REAL' ;

expressaoAritmetica:
                    termoAritmetico ((OpAritSoma | OpAritSub) {
                    _conteudoExp += _input.LT(-1).getText();}
                    termoAritmetico)*;

termoAritmetico: fatorAritmetico ((OpAritMult| OpAritDiv) {
                _conteudoExp += _input.LT(-1).getText();}
                fatorAritmetico)*;

fatorAritmetico: NumInt { _conteudoExp += _input.LT(-1).getText(); }
                | NumReal { _conteudoExp += _input.LT(-1).getText();}
                | Var { verificaVar(_input.LT(-1).getText()); _conteudoExp += _input.LT(-1).getText(); }
                | AbrePar { _conteudoExp += _input.LT(-1).getText();}
                 expressaoAritmetica
                 FechaPar { _conteudoExp += _input.LT(-1).getText();} ;

expressaoRelacional: termoRelacional
                    (operadorBooleano
                     { switch(_input.LT(-1).getText()){
                        case "E": _cond += " && ";
                           break;
                         case "OU": _cond += " || ";
                            break;
                         default: break;
                        }
                     }
                     termoRelacional)*;

termoRelacional: { _conteudoExp = "";}
                expressaoAritmetica
                OpRel {
                    _cond += _conteudoExp + _input.LT(-1).getText();
                    _conteudoExp = "";
                }
                expressaoAritmetica { _cond += _conteudoExp; }
                | AbrePar { _cond += _input.LT(-1).getText();}
                expressaoRelacional
                FechaPar { _cond += _input.LT(-1).getText();};

operadorBooleano: 'E' | 'OU';

listaComandos: (comando { listaCmd.addAll(listaAux);listaAux.clear();})+;

comando: comandoAtribuicao | comandoEntrada | comandoSaida | comandoCondicao | comandoRepeticao | subAlgoritmo;

comandoAtribuicao: Var { verificaVar(_input.LT(-1).getText()); _varExp = _input.LT(-1).getText() ;}
                   Atrib {_conteudoExp = "";}
                   expressaoAritmetica {
                        ComandoAtribuicao cmd = new ComandoAtribuicao(_varExp, _conteudoExp);
                        listaAux.add(cmd);
                   };

comandoEntrada: 'LER' Var{
                    verificaVar(_input.LT(-1).getText());
                    _varEntrada = _input.LT(-1).getText();
                    ComandoLeitura cmd = new ComandoLeitura(_varEntrada);
                    listaAux.add(cmd);
                };

comandoSaida: 'IMPRIMIR' Var {
                verificaVar(_input.LT(-1).getText());
                _varSaida = _input.LT(-1).getText();
                ComandoSaida cmd = new ComandoSaida(_varSaida);
                listaAux.add(cmd);
            }
            | 'IMPRIMIR' Cadeia{
                _varSaida = _input.LT(-1).getText();
                ComandoSaida cmd = new ComandoSaida(_varSaida);
                listaAux.add(cmd);
            };

comandoCondicao: 'SE' { _cond = ""; listaTrue.clear(); listaFalse.clear();}
                expressaoRelacional
                'ENTAO'
                comando { listaTrue.addAll(listaAux); listaAux.clear(); }
                ('SENAO' comando {listaFalse.addAll(listaAux); listaAux.clear();})?{
                    ComandoCondicao cmd = new ComandoCondicao(_cond, new ArrayList<>(listaTrue), new ArrayList<>(listaFalse));
                    listaAux.add(cmd);
                };

comandoRepeticao: 'ENQTO' { _cond = "";}
                  expressaoRelacional comando{
                        ComandoRepeticao cmd = new ComandoRepeticao(_cond, new ArrayList<>(listaAux));
                        listaAux.clear();
                        listaAux.add(cmd);
                  };

subAlgoritmo: 'INI' comando+ 'FIM';

PC: 'DEC' | 'INT' | 'REAL' | 'PROG' | 'LER' | 'IMPRIMIR' | 'SE' | 'SENAO' | 'ENTAO' | 'ENQTO' | 'INI' | 'FIM';
OpAritMult: '*';
OpAritDiv: '/';
OpAritSoma: '+';
OpAritSub: '-';
OpRel: '<' | '<=' | '>' | '>=' | '==' | '!=';
OpBoolE: 'E';
OpBoolOu: 'OU';
Delim: ':';
Atrib: ':=';
AbrePar: '(';
FechaPar: ')';

Var: [a-z] ([0-9] | [A-Z] | [a-z])*;

NumInt: [0-9]+;

NumReal: [0-9]+ '.' [0-9]*;

Cadeia: '"' ~["\r\n]+ '"';

Comment: '#' ~[\r\n]* -> skip;

WS: (' ' | '\n' | '\r' | '\t')+ -> skip;