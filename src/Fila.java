public class Fila<T> {

    private final ListaDupla<T> lista = new ListaDupla<>();

    public void enfileirar(T dado) {
        lista.inserirFim(dado);
    }

    public T desenfileirar() {
        return lista.removerInicio();
    }

    public T frente() {
        return lista.consultarInicio();
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
        return "Fila (frente -> fim): " + lista;
    }
}



