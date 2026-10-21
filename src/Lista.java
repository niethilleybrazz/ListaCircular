public class Lista<T extends Comparable<T>> extends ListaDupla<T> {

    // 1. Inserção ordenada por chave
    public void inserirOrdenado(T dado) {
        No<T> atual = primeiro;
        while (atual != null && atual.dado.compareTo(dado) <= 0) {
            atual = atual.proximo;
        }
        inserirAntes(atual, dado);   // atual == null -> insere no final
    }

    // 2. Partição em duas

    public static class Particao<E extends Comparable<E>> {
        public final Lista<E> primeira = new Lista<>();
        public final Lista<E> segunda = new Lista<>();
    }

    public Particao<T> particionar(T pivo) {
        Particao<T> p = new Particao<>();
        for (T dado : this) {
            if (dado.compareTo(pivo) < 0) {
                p.primeira.inserirFim(dado);
            } else {
                p.segunda.inserirFim(dado);
            }
        }
        return p;
    }

    public Particao<T> particionarAoMeio() {
        Particao<T> p = new Particao<>();
        int metade = (tamanho + 1) / 2;
        int contador = 0;
        for (T dado : this) {
            if (contador < metade) {
                p.primeira.inserirFim(dado);
            } else {
                p.segunda.inserirFim(dado);
            }
            contador++;
        }
        return p;
    }

    // 3. Inverção usando pilha
    public void inverterComPilha() {
        Pilha<T> pilha = new Pilha<>();
        for (T dado : this) {
            pilha.empilhar(dado);
        }
        inicializar();
        while (!pilha.estaVazia()) {
            inserirFim(pilha.desempilhar());
        }
    }

    // 4. Combinação de listas em uma
    public static <T extends Comparable<T>> Lista<T> intercalar(Lista<T> a, Lista<T> b) {
        Lista<T> resultado = new Lista<>();
        No<T> pa = a.primeiro;
        No<T> pb = b.primeiro;

        while (pa != null && pb != null) {
            if (pa.dado.compareTo(pb.dado) <= 0) {
                resultado.inserirFim(pa.dado);
                pa = pa.proximo;
            } else {
                resultado.inserirFim(pb.dado);
                pb = pb.proximo;
            }
        }
        while (pa != null) {          // sobras de A
            resultado.inserirFim(pa.dado);
            pa = pa.proximo;
        }
        while (pb != null) {          // sobras de B
            resultado.inserirFim(pb.dado);
            pb = pb.proximo;
        }
        return resultado;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> Lista<T> mesclar(Lista<T>... listas) {
        Lista<T> resultado = new Lista<>();
        for (Lista<T> lista : listas) {
            resultado = intercalar(resultado, lista);
        }
        return resultado;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> Lista<T> concatenar(Lista<T>... listas) {
        Lista<T> resultado = new Lista<>();
        for (Lista<T> lista : listas) {
            for (T dado : lista) {
                resultado.inserirFim(dado);
            }
        }
        return resultado;
    }

    // 5. Interseção entre duas listas
    public static <T extends Comparable<T>> Lista<T> intersecao(Lista<T> a, Lista<T> b) {
        Lista<T> resultado = new Lista<>();
        No<T> pa = a.primeiro;
        No<T> pb = b.primeiro;

        while (pa != null && pb != null) {
            int cmp = pa.dado.compareTo(pb.dado);
            if (cmp == 0) {
                boolean repetido = !resultado.estaVazia()
                        && resultado.ultimo.dado.compareTo(pa.dado) == 0;
                if (!repetido) {
                    resultado.inserirFim(pa.dado);
                }
                pa = pa.proximo;
                pb = pb.proximo;
            } else if (cmp < 0) {
                pa = pa.proximo;
            } else {
                pb = pb.proximo;
            }
        }
        return resultado;
    }

    public static <T extends Comparable<T>> Lista<T> intersecaoNaoOrdenada(Lista<T> a, Lista<T> b) {
        Lista<T> resultado = new Lista<>();
        for (T dado : a) {
            if (b.contem(dado) && !resultado.contem(dado)) {
                resultado.inserirFim(dado);
            }
        }
        return resultado;
    }

    // 6. Balance-line
    public static class ResultadoBalanceLine<E extends Comparable<E>> {
        public final Lista<E> emAmbas = new Lista<>();
        public final Lista<E> somenteEmA = new Lista<>();   // A tem, B não tem
        public final Lista<E> somenteEmB = new Lista<>();   // B tem, A não tem
    }

    public static <T extends Comparable<T>> ResultadoBalanceLine<T> balanceLine(Lista<T> a, Lista<T> b) {
        ResultadoBalanceLine<T> r = new ResultadoBalanceLine<>();
        No<T> pa = a.primeiro;
        No<T> pb = b.primeiro;

        while (pa != null && pb != null) {
            int cmp = pa.dado.compareTo(pb.dado);
            if (cmp == 0) {
                r.emAmbas.inserirFim(pa.dado);
                pa = pa.proximo;
                pb = pb.proximo;
            } else if (cmp < 0) {
                r.somenteEmA.inserirFim(pa.dado);
                pa = pa.proximo;
            } else {
                r.somenteEmB.inserirFim(pb.dado);
                pb = pb.proximo;
            }
        }
        while (pa != null) {
            r.somenteEmA.inserirFim(pa.dado);
            pa = pa.proximo;
        }
        while (pb != null) {
            r.somenteEmB.inserirFim(pb.dado);
            pb = pb.proximo;
        }
        return r;
    }
}
