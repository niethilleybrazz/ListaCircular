import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ListaDupla<T> implements Iterable<T> {

    protected No<T> primeiro;
    protected No<T> ultimo;
    protected int tamanho;

    public ListaDupla() {
        inicializar();
    }

    public final void inicializar() {
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserirInicio(T dado) {
        No<T> novo = new No<>(dado);
        if (estaVazia()) {
            primeiro = novo;
            ultimo = novo;
        } else {
            novo.proximo = primeiro;     // novo aponta para o antigo primeiro
            primeiro.anterior = novo;    // antigo primeiro aponta de volta
            primeiro = novo;
        }
        tamanho++;
    }

    public void inserirFim(T dado) {
        No<T> novo = new No<>(dado);
        if (estaVazia()) {
            primeiro = novo;
            ultimo = novo;
        } else {
            novo.anterior = ultimo;      // novo aponta para o antigo último
            ultimo.proximo = novo;       // antigo último aponta para o novo
            ultimo = novo;
        }
        tamanho++;
    }

    protected void inserirAntes(No<T> referencia, T dado) {
        if (referencia == null) {
            inserirFim(dado);
        } else if (referencia == primeiro) {
            inserirInicio(dado);
        } else {
            No<T> novo = new No<>(dado);
            novo.anterior = referencia.anterior;
            novo.proximo = referencia;
            referencia.anterior.proximo = novo;
            referencia.anterior = novo;
            tamanho++;
        }
    }

    public void inserirNaPosicao(int pos, T dado) {
        if (pos < 0 || pos > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos);
        }
        if (pos == tamanho) {
            inserirFim(dado);
        } else {
            inserirAntes(noNaPosicao(pos), dado);
        }
    }

    public T removerInicio() {
        exigirNaoVazia();
        return removerNo(primeiro);
    }

    public T removerFim() {
        exigirNaoVazia();
        return removerNo(ultimo);
    }

    public T removerDaPosicao(int pos) {
        return removerNo(noNaPosicao(pos));
    }

    public boolean remover(T dado) {
        No<T> no = buscarNo(dado);
        if (no == null) {
            return false;
        }
        removerNo(no);
        return true;
    }

    protected T removerNo(No<T> no) {
        if (no.anterior == null) {
            primeiro = no.proximo;
        } else {
            no.anterior.proximo = no.proximo;
        }

        if (no.proximo == null) {
            ultimo = no.anterior;
        } else {
            no.proximo.anterior = no.anterior;
        }

        T dado = no.dado;
        no.anterior = null;
        no.proximo = null;
        tamanho--;
        return dado;
    }

    public T consultarInicio() {
        exigirNaoVazia();
        return primeiro.dado;
    }

    public T consultarFim() {
        exigirNaoVazia();
        return ultimo.dado;
    }

    public T obter(int pos) {
        return noNaPosicao(pos).dado;
    }

    public boolean contem(T dado) {
        return buscarNo(dado) != null;
    }

    protected No<T> buscarNo(T dado) {
        for (No<T> atual = primeiro; atual != null; atual = atual.proximo) {
            if (Objects.equals(atual.dado, dado)) {
                return atual;
            }
        }
        return null;
    }

    protected No<T> noNaPosicao(int pos) {
        if (pos < 0 || pos >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos);
        }
        No<T> atual;
        if (pos < tamanho / 2) {
            atual = primeiro;
            for (int i = 0; i < pos; i++) {
                atual = atual.proximo;
            }
        } else {
            atual = ultimo;
            for (int i = tamanho - 1; i > pos; i--) {
                atual = atual.anterior;
            }
        }
        return atual;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private No<T> atual = primeiro;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                if (atual == null) {
                    throw new NoSuchElementException();
                }
                T dado = atual.dado;
                atual = atual.proximo;
                return dado;
            }
        };
    }

    public Iterable<T> reverso() {
        return () -> new Iterator<T>() {
            private No<T> atual = ultimo;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                if (atual == null) {
                    throw new NoSuchElementException();
                }
                T dado = atual.dado;
                atual = atual.anterior;
                return dado;
            }
        };
    }

    public void destruir() {
        No<T> atual = primeiro;
        while (atual != null) {
            No<T> prox = atual.proximo;
            atual.anterior = null;
            atual.proximo = null;
            atual.dado = null;
            atual = prox;
        }
        inicializar();
    }

    protected void exigirNaoVazia() {
        if (estaVazia()) {
            throw new NoSuchElementException("A estrutura está vazia.");
        }
    }

    @Override
    public String toString() {
        return montarTexto(this);
    }

    public String toStringReverso() {
        return montarTexto(reverso());
    }

    private String montarTexto(Iterable<T> percurso) {
        StringBuilder sb = new StringBuilder("[");
        boolean primeiroItem = true;
        for (T dado : percurso) {
            if (!primeiroItem) {
                sb.append(" <-> ");
            }
            sb.append(dado);
            primeiroItem = false;
        }
        return sb.append("]").toString();
    }
}
 