/**
 * Aluno de graduação.
 *
 * Tempo de curso: contado em SEMESTRES, apresentado como ano.periodo
 * (ex.: 2026.1), com prazo máximo de 14 semestres.
 * 
 * Nota final: soma das 3 notas de unidade dividida por 3.
 * 
 * Situação: 
 * - média >= 6,0 aprovado; 
 * - 4,0 <= média < 6,0 recuperação;
 * - média < 4,0 reprovado direto.
 *
 * ---------------------------------------------------------------------
 * ITEM 2 DA PRÁTICA: complete esta classe.
 *
 * Use AlunoTecnico.java como exemplo: ela já está pronta e resolve o mesmo
 * problema para outro tipo de aluno. O esqueleto abaixo já compila, mas os
 * resultados ainda estão errados.
 * ---------------------------------------------------------------------
 */
public class AlunoGraduacao extends Aluno {

    public static final int TOTAL_UNIDADES = 3;
    public static final int PRAZO_MAXIMO_SEMESTRES = 14;
    public static final double MEDIA_APROVACAO = 6.0;
    public static final double MEDIA_REPROVACAO = 4.0;

    // Esta classe NÃO guarda ano, período nem semestres cursados: os três são
    // calculados a partir do início do curso, que fica na superclasse.
    public AlunoGraduacao(String matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public void lancarNota(String valor) {
        // TODO 2.1: recuse a nota quando o aluno já tiver TOTAL_UNIDADES notas lançadas
        // ou quando o valor não for uma nota numérica valida.
        // é possível reaproveitar o método ehNotaNumericaValida(valor) herdado de Aluno?
        // Por outro lado, se estiver tudo certo, chame super.lancarNota(valor) para a
        // superclasse guardar a nota na lista.
        if (getNotas().size() >= TOTAL_UNIDADES) {
            System.out.println(
                    "[aviso] O curso de graduação tem apenas " + TOTAL_UNIDADES
                            + " unidades. Nota ignorada: " + valor
            );
            return;
        }
        if (!ehNotaNumericaValida(valor)) {
            System.out.println(
                    "[aviso] Nota invalida (use um numero de 0 a 10). Nota ignorada: " + valor
            );
            return;
        }
        super.lancarNota(valor);
    }

    // Auxiliar private, como em AlunoTecnico: para o cliente, quem responde é
    // getDesempenho(). Ninguém de fora precisa (nem deve) chamar getMedia().
    private double getMedia() {
        // TODO 2.2: some as notas de getNotas() usando
        // converterParaNumero(nota) e divida por TOTAL_UNIDADES.
        double soma = 0.0;
        for (String nota : getNotas()) {
            soma += converterParaNumero(nota);
        }
        // Divide sempre por 3: unidade sem nota lançada conta como zero.
        return soma / TOTAL_UNIDADES;
    }

    @Override
    public String getSituacao() {
        // TODO 2.3: aplique a regra da graduação usando MEDIA_APROVACAO e
        // MEDIA_REPROVACAO. Devolva APROVADO, RECUPERACAO ou REPROVADO
        // (constantes herdadas de Aluno).
        if (getMedia() >= MEDIA_APROVACAO) {
            return APROVADO;
        } else if (getMedia() >= MEDIA_REPROVACAO) {
            return RECUPERACAO;
        }
        return REPROVADO;
    }

    @Override
    public String getDesempenho() {
        // TODO 2.4: devolva algo como "Média: 7,50" (veja String.format).

        return String.format("Media: %.2f", getMedia());
    }

    @Override
    public String getPeriodoAtual() {
        // TODO 2.5: devolva o período no formato ano.periodo (ex.: "2026.1"),
        // calculado a partir do início do curso.
        // Um caminho: quem entrou de janeiro a junho (getMesInicio() <= 6)
        // entrou no período 1; de julho a dezembro, no período 2. Some a esse
        // período de entrada os semestres já decorridos (getTempoDecorrido()
        // menos 1) e converta o total em ano + período: cada 2 semestres
        // avançam 1 ano em getAnoInicio(), e o resto da divisão por 2 diz se o
        // aluno está no período 1 ou no 2.
        return "";
    }

    @Override
    public int getTempoDecorrido() {
        // TODO 2.6: em qual semestre o aluno está? Use getMesesDecorridos()
        // (herdado de Aluno): cada 6 meses é um semestre completo e quem
        // acabou de entrar já está no 1o. Compare com getTempoDecorrido() de
        // AlunoTecnico, que faz a mesma conta em anos.
        return getMesesDecorridos() / 6 + 1;
    }

    @Override
    public int getPrazoMaximo() {
        // TODO 2.7
        return PRAZO_MAXIMO_SEMESTRES;
    }

    @Override
    public String getUnidadeDePrazo() {
        // TODO 2.8: a unidade de tempo deste tipo de aluno.
        return "semestre(s)";
    }
}
