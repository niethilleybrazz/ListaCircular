public class Pilha<T> {

    private final ListaDupla<T> lista = new ListaDupla<>();

    public void empilhar(T dado) {
        lista.inserirFim(dado);
    }

    public T desempilhar() {
        return lista.removerFim();
    }

    public T topo() {
        return lista.consultarFim();
    }

    public boolean estaVazia() {
        return lista.estaVazia();
    }

    public int tamanho() {
        return lista.tamanho();
    }

    public void limpar() {
        lista.destruir();
    }

    @Override
    public String toString() {
        return "Pilha (base -> topo): " + lista;
    }
}
