import java.util.Arrays;

/**
 * Aluno de pós-graduação (Especialização/Mestrado/Doutorado).
 *
 * Tempo de curso: contado em MESES, no máximo 24 meses para conclusão.
 * Nota final: conceito A, B, C ou D (não há média numérica).
 * Situação: A ou B aprovado; C recuperação; D reprovado direto.
 *
 * ---------------------------------------------------------------------
 * ITEM 3 DA PRÁTICA: complete esta classe.
 *
 * Repare que aqui NÃO existe média nenhuma: o estado e a regra são
 * diferentes dos outros tipos de aluno, e isso é normal. O que precisa
 * continuar igual é o conjunto de métodos herdado de Aluno.
 * ---------------------------------------------------------------------
 */
public class AlunoPosGraduacao extends Aluno {

    public static final int PRAZO_MAXIMO_MESES = 24;
    public static final String[] NOTAS = {"A","B","C","D"};

    // Como na graduação, o mês de curso não é guardado: ele é calculado a
    // partir do início do curso, que fica na superclasse.
    public AlunoPosGraduacao(String matricula, String nome) {
        super(matricula, nome);
    }

    // talvez essa classe esteja com problema
    @Override
    public void lancarNota(String valor) {
        boolean isLetraValida = Arrays.asList(NOTAS).contains(valor.trim().toUpperCase());
        if (getNotas().size() > 1) {
            System.out.println("[aviso] Cursos de Pós-graduação só aceitam uma única nota. Nota ignorada: " + valor);
            return;
        }
        if (isLetraValida) {
            super.lancarNota(valor);
        } else {
            System.out.println("[aviso] letra inválida (use uma letra de A a D). Nota ignorada: " + valor);
            return;
        }
    }

    private String getConceito() {
        if (getNotas().size() > 0) {
            return getNotas().getFirst();
        }
        return "-";
    }

    @Override
    public String getSituacao() {
        if (getConceito() == "A" || getConceito() == "B") {
            return APROVADO;
        }
        if (getConceito() == "C") {
            return RECUPERACAO;
        }
        if (getConceito() == "D") {
            return REPROVADO;
        }
        // achei melhor deixar como NAO_AVALIADO mesmo
        return NAO_AVALIADO;
    }

    @Override
    public String getDesempenho() {
        return "Conceito: " + getConceito();
    }

    @Override
    public String getPeriodoAtual() {
        int semestreAtual = getTempoDecorrido();
        return semestreAtual + "o mês";
    }

    @Override
    public int getTempoDecorrido() {
        if (getMesesDecorridos() == 1) {
            return 1;
        }
        return getMesesDecorridos();
    }

    @Override
    public int getPrazoMaximo() {
        return PRAZO_MAXIMO_MESES;
    }

    @Override
    public String getUnidadeDePrazo() {
        return "mes(es)";
    }
}
