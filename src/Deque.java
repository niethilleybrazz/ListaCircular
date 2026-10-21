public class Deque<T> {

    private final ListaDupla<T> lista = new ListaDupla<>();

    public void inserirInicio(T dado) {
        lista.inserirInicio(dado);
    }

    public void inserirFim(T dado) {
        lista.inserirFim(dado);
    }

    public T removerInicio() {
        return lista.removerInicio();
    }

    public T removerFim() {
        return lista.removerFim();
    }

    public T consultarInicio() {
        return lista.consultarInicio();
    }

    public T consultarFim() {
        return lista.consultarFim();
    }

    public boolean estaVazio() {
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
        return "Deque (inicio -> fim): " + lista;
    }
}