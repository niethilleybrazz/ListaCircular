public class Aluno implements Comparable<Aluno> {

    private final int matricula;   // chave
    private final String nome;

    public Aluno(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int compareTo(Aluno outro) {
        return Integer.compare(this.matricula, outro.matricula);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Aluno)) {
            return false;
        }
        return matricula == ((Aluno) o).matricula;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(matricula);
    }

    @Override
    public String toString() {
        return matricula + "-" + nome;
    }
}
