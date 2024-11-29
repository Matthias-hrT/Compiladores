
public class ListaTokens {
    private static class Nodo{
        Token token;
        Nodo anterior;
        Nodo proximo;

        Nodo(Token token){
            this.token = token;
        }
    }

    private Nodo inicio;
    private Nodo atual;

    public ListaTokens() {
        inicio = null;
        atual = null;
    }

    // Adiciona um token no final da lista
    public void adicionar(Token token) {
        Nodo novoNodo = new Nodo(token);
        if (inicio == null) {
            inicio = novoNodo;
            atual = inicio;
        } else {
            Nodo temp = inicio;
            while (temp.proximo != null) {
                temp = temp.proximo;
            }
            temp.proximo = novoNodo;
            novoNodo.anterior = temp;
        }
    }

    // Retorna o token atual sem avançar
    public Token atual() {
        return atual != null ? atual.token : null;
    }

    // Avança para o próximo token
    public void avancar() {
        if (atual != null) {
            atual = atual.proximo;
        }
    }

    // Retrocede para o token anterior
    public void retroceder() {
        if (atual != null && atual.anterior != null) {
            atual = atual.anterior;
        }
    }

    // Reinicia a lista para o início
    public void reiniciar() {
        atual = inicio;
    }

    public void exibirTokens() {
        while(atual != null){
            System.out.println(atual.token);
            atual = atual.proximo;
        }
    }
}
