public class Parenteses {

    public static boolean estaBalanceada(String expressao) {
        return encontrarErro(expressao) == null;
    }

    public static String encontrarErro(String expressao) {
        Pilha<Character> simbolos = new Pilha<>();
        Pilha<Integer> posicoes = new Pilha<>();   // onde cada abridor apareceu

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (ehAbridor(c)) {
                simbolos.empilhar(c);
                posicoes.empilhar(i);

            } else if (ehFechador(c)) {
                if (simbolos.estaVazia()) {
                    return "'" + c + "' no índice " + i + " não tem abridor correspondente";
                }
                char abridor = simbolos.desempilhar();
                posicoes.desempilhar();
                if (!formamPar(abridor, c)) {
                    return "'" + c + "' no índice " + i + " não casa com o abridor '" + abridor + "'";
                }
            }
        }

        if (!simbolos.estaVazia()) {
            char abridor = simbolos.desempilhar();
            int pos = posicoes.desempilhar();
            return "'" + abridor + "' no índice " + pos + " nunca foi fechado";
        }
        return null;
    }

    private static boolean ehAbridor(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean ehFechador(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean formamPar(char abridor, char fechador) {
        return (abridor == '(' && fechador == ')')
                || (abridor == '[' && fechador == ']')
                || (abridor == '{' && fechador == '}');
    }
}
