public class Main {

    public static void main(String[] args) {
        System.out.println("====== Projeto ======");
        demoPilha();
        demoFila();
        demoDeque();
        demoLista();

        System.out.println("\n====== Implemetações ======");
        InsercaoOrdenada();
        Particao();
        InversaoComPilha();
        Mesclar();
        Intersecao();
        BalanceLine();
        Parenteses();
    }

    private static void titulo(String texto) {
        System.out.println("\n=== " + texto + " ===");
    }

    private static Lista<Integer> listaOrdenada(int... valores) {
        Lista<Integer> lista = new Lista<>();
        for (int v : valores) {
            lista.inserirOrdenado(v);
        }
        return lista;
    }

    private static void demoPilha() {
        titulo("Pilha (LIFO)");
        Pilha<Integer> pilha = new Pilha<>();
        pilha.empilhar(10);
        pilha.empilhar(20);
        pilha.empilhar(30);
        System.out.println(pilha);
        System.out.println("topo = " + pilha.topo());
        System.out.println("desempilhou " + pilha.desempilhar());
        System.out.println(pilha);
    }

    private static void demoFila() {
        titulo("Fila (FIFO)");
        Fila<String> fila = new Fila<>();
        fila.enfileirar("A");
        fila.enfileirar("B");
        fila.enfileirar("C");
        System.out.println(fila);
        System.out.println("frente = " + fila.frente());
        System.out.println("desenfileirou " + fila.desenfileirar());
        System.out.println(fila);
    }

    private static void demoDeque() {
        titulo("Deque (duas pontas)");
        Deque<Integer> deque = new Deque<>();
        deque.inserirInicio(2);
        deque.inserirInicio(1);
        deque.inserirFim(3);
        deque.inserirFim(4);
        System.out.println(deque);
        System.out.println("removeu do início: " + deque.removerInicio());
        System.out.println("removeu do fim:    " + deque.removerFim());
        System.out.println(deque);
    }

    private static void demoLista() {
        titulo("Lista (LDE completa)");
        Lista<Integer> lista = new Lista<>();
        lista.inserirFim(10);
        lista.inserirFim(30);
        lista.inserirInicio(5);
        lista.inserirNaPosicao(2, 20);
        System.out.println("percurso normal:  " + lista);
        System.out.println("percurso reverso: " + lista.toStringReverso());
        System.out.println("obter(2) = " + lista.obter(2));
        System.out.println("contem(30)? " + lista.contem(30));
        System.out.println("removeu da posição 1: " + lista.removerDaPosicao(1));
        System.out.println("remover(30)? " + lista.remover(30));
        System.out.println(lista + "  (tamanho " + lista.tamanho() + ")");
        lista.destruir();
        System.out.println("após destruir: " + lista + "  (vazia? " + lista.estaVazia() + ")");
    }

    private static void InsercaoOrdenada() {
        titulo("1. Inserção ordenada por chave (matrícula)");
        Lista<Aluno> alunos = new Lista<>();
        alunos.inserirOrdenado(new Aluno(30, "Carla"));
        alunos.inserirOrdenado(new Aluno(10, "Ana"));
        alunos.inserirOrdenado(new Aluno(25, "Bruno"));
        alunos.inserirOrdenado(new Aluno(20, "Davi"));
        alunos.inserirOrdenado(new Aluno(40, "Elisa"));
        System.out.println(alunos);
    }

    private static void Particao() {
        titulo("2. Partição de uma lista em duas");
        Lista<Integer> lista = listaOrdenada(8, 3, 5, 1, 9, 4, 7);
        System.out.println("original:           " + lista);

        Lista.Particao<Integer> porPivo = lista.particionar(5);
        System.out.println("pivô 5  -> menores: " + porPivo.primeira);
        System.out.println("           >= 5:    " + porPivo.segunda);

        Lista.Particao<Integer> aoMeio = lista.particionarAoMeio();
        System.out.println("ao meio -> 1ª:      " + aoMeio.primeira);
        System.out.println("           2ª:      " + aoMeio.segunda);
    }

    private static void InversaoComPilha() {
        titulo("3. Inversão de lista usando pilha");
        Lista<Integer> lista = listaOrdenada(1, 3, 4, 5, 7);
        System.out.println("antes:  " + lista);
        lista.inverterComPilha();
        System.out.println("depois: " + lista);
    }

    private static void Mesclar() {
        titulo("4. Combinação de várias listas");
        Lista<Integer> a = listaOrdenada(1, 4, 7);
        Lista<Integer> b = listaOrdenada(2, 5, 8);
        Lista<Integer> c = listaOrdenada(3, 6, 9, 10);
        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("C = " + c);
        System.out.println("intercalar(A, B)  = " + Lista.intercalar(a, b));
        System.out.println("mesclar(A, B, C)   = " + Lista.mesclar(a, b, c));
        System.out.println("concatenar(A, B, C)= " + Lista.concatenar(a, b, c));
    }

    private static void Intersecao() {
        titulo("5. Interseção entre duas listas");
        Lista<Integer> a = listaOrdenada(1, 2, 3, 5, 8);
        Lista<Integer> b = listaOrdenada(2, 3, 5, 7, 9);
        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("interseção (ordenadas, O(n+m))  = " + Lista.intersecao(a, b));

        Lista<Integer> x = new Lista<>();
        Lista<Integer> y = new Lista<>();
        for (int v : new int[]{5, 1, 3, 2}) x.inserirFim(v);
        for (int v : new int[]{2, 9, 5}) y.inserirFim(v);
        System.out.println("X = " + x);
        System.out.println("Y = " + y);
        System.out.println("interseção (qualquer ordem, O(n*m)) = " + Lista.intersecaoNaoOrdenada(x, y));
    }

    private static void BalanceLine() {
        titulo("6. Balance-line");
        Lista<Integer> cadastro = listaOrdenada(101, 102, 103, 105);
        Lista<Integer> pagamentos = listaOrdenada(102, 104, 105, 106);
        System.out.println("Cadastro   (A) = " + cadastro);
        System.out.println("Pagamentos (B) = " + pagamentos);

        Lista.ResultadoBalanceLine<Integer> r = Lista.balanceLine(cadastro, pagamentos);
        System.out.println("estão nas duas:        " + r.emAmbas);
        System.out.println("só no cadastro (A):    " + r.somenteEmA + "   <- A não está em B");
        System.out.println("só nos pagamentos (B): " + r.somenteEmB + "   <- B não está em A");
    }

    private static void Parenteses() {
        titulo("7. Balanceamento de parênteses");
        String[] testes = {
                "(a + b) * [c - {d / e}]",
                "((a + b)",
                "a + b)",
                "(a + b]",
                "{[()]}",
                "{[(])}"
        };
        for (String expr : testes) {
            String erro = Parenteses.encontrarErro(expr);
            System.out.printf("%-26s -> %s%n", expr,
                    erro == null ? "balanceada" : "ERRO: " + erro);
        }
    }
}
