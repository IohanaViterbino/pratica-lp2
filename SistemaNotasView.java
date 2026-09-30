import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cliente das classes de aluno: o programa que lança notas e imprime o
 * relatório da turma.
 *
 * Ponto central da prática: esta classe NÃO deve saber se está
 * lidando com um AlunoTecnico, um AlunoGraduacao ou um AlunoPosGraduacao.
 * Ela conversa apenas com o tipo Aluno.
 *
 * ---------------------------------------------------------------------
 * ITENS 5, 6 DA PRÁTICA: complete esta classe.
 * ---------------------------------------------------------------------
 */
public class SistemaNotasView {

    public static void main(String[] args) {
        ArrayList<Aluno> turma = montarTurma();

        imprimirRelatorio(turma);
        imprimirResumoPorSituacao(turma);
        imprimirAlertasDePrazo(turma);
        demonstrarTipoEstaticoEDinamico();
        demonstrarCasosDeBorda();
    }

    private static ArrayList<Aluno> montarTurma() {
        ArrayList<Aluno> turma = new ArrayList<Aluno>();

        // Aluno técnico calouro
        Aluno einstein = AlunoFactory.criar(
                "TECNICO",
                "2026001",
                "Albert Einstein"
        );
        turma.add(einstein);

        // Técnico com prazo estourado
        Aluno noether = AlunoFactory.criar(
                "TECNICO",
                "2021005",
                "Emmy Noether"
        );
        noether.setInicioDoCurso("03/2021");
        turma.add(noether);

        // Graduação
        Aluno celine = AlunoFactory.criar(
                "GRADUACAO",
                "2025048",
                "Celine Dion"
        );
        celine.setInicioDoCurso("03/2023");
        turma.add(celine);

        // Pós-graduação
        Aluno maria = AlunoFactory.criar(
                "POS",
                "2021034",
                "Maria Cecília"
        );
        maria.setInicioDoCurso("05/2025");
        turma.add(maria);

        // -------------------------------------------------------------
        // TODO 5.1 / 5.2
        // Criação dos demais alunos usando a fábrica.
        // -------------------------------------------------------------

        String[][] matriculas = {
                {"TECNICO", "2026002", "Blaise Pascal", "03/2026"},
                {"GRADUACAO", "2026003", "Cecilia Payne", "03/2026"},
                {"POS", "2026004", "Dmitri Mendeleev", "03/2026"},

                // Casos antigos para testar prazo
                {"GRADUACAO", "2020001", "Ada Lovelace", "03/2020"},
                {"POS", "2020002", "Alan Turing", "03/2020"}
        };

        for (int i = 0; i < matriculas.length; i++) {
            Aluno aluno = AlunoFactory.criar(
                    matriculas[i][0],
                    matriculas[i][1],
                    matriculas[i][2]
            );

            if (aluno != null) {
                aluno.setInicioDoCurso(matriculas[i][3]);
                turma.add(aluno);
            }
        }

        // -------------------------------------------------------------
        // Lançamento das notas
        // -------------------------------------------------------------

        lancarNotas(turma.get(0), "8.0", "7.5", "6.0", "9.0");
        lancarNotas(turma.get(1), "4.0", "3.0", "5.0", "2.0");
        lancarNotas(turma.get(2), "6.0", "8.0", "5.5");
        lancarNotas(turma.get(3), "D");

        // Demais alunos
        lancarNotas(turma.get(4), "9.0", "8.5", "9.5", "10.0");
        lancarNotas(turma.get(5), "6.0", "5.0", "4.5");
        lancarNotas(turma.get(6), "C");
        lancarNotas(turma.get(7), "7.0", "7.5", "8.0");
        lancarNotas(turma.get(8), "B");

        return turma;
    }

    private static void lancarNotas(Aluno aluno, String... valores) {
        for (String valore : valores) {
            aluno.lancarNota(valore);
        }
    }

    private static void imprimirRelatorio(ArrayList<Aluno> turma) {
        System.out.println("===== RESUMO POR SITUACAO =====");

        HashMap<String, Integer> contagem = new HashMap<String, Integer>();

        String[] situacoes = {
                Aluno.APROVADO,
                Aluno.RECUPERACAO,
                Aluno.REPROVADO,
                Aluno.NAO_AVALIADO
        };

        for (String situacao : situacoes) {
            contagem.put(situacao, 0);
        }

        for (Aluno aluno : turma) {
            String situacao = aluno.getSituacao();
            int quantidadeAtual = contagem.getOrDefault(situacao, 0);
            contagem.put(situacao, quantidadeAtual + 1);
        }

        for (String situacoe : situacoes) {
            System.out.println(
                    situacoe + ": " + contagem.get(situacoe)
            );
        }

        System.out.println();
    }

    /**
     * Conta quantos alunos existem em cada situação.
     */
    private static void imprimirResumoPorSituacao(ArrayList<Aluno> turma) {
        System.out.println("===== RESUMO POR SITUACAO =====");
        System.out.println();
    }

    /**
     * Mostra o prazo de cada aluno e informa se ele ainda está dentro
     * do prazo de integralização.
     */
    private static void imprimirAlertasDePrazo(ArrayList<Aluno> turma) {
        System.out.println("===== PRAZO DE INTEGRALIZACAO =====");
        for (Aluno aluno : turma) {
            System.out.println("Aluno: " + aluno.getNome());
            System.out.println(
                    "Inicio do curso: " + aluno.getInicioDoCurso()
            );
            System.out.println(
                    "Prazo: " + aluno.getPrazo()
            );

            if (aluno.estaNoPrazo()) {
                System.out.println(
                        "Situacao do prazo: Dentro do prazo"
                );
                System.out.println(
                        "Tempo restante: "
                                + aluno.getTempoRestante()
                                + " "
                                + aluno.getUnidadeDePrazo()
                );
            } else {
                System.out.println(
                        "Situacao do prazo: Prazo estourado"
                );
                System.out.println(
                        "Tempo restante: "
                                + aluno.getTempoRestante()
                                + " "
                                + aluno.getUnidadeDePrazo()
                );
            }
            System.out.println(
                    "------------------------------------------------------------"
            );
        }
        System.out.println();
    }

    /**
     * Demonstra tipo estático e tipo dinâmico.
     */
    private static void demonstrarTipoEstaticoEDinamico() {
        System.out.println("===== TIPO ESTATICO x TIPO DINAMICO =====");

        Aluno chico = AlunoFactory.criar("TECNICO", "2025001", "Chico Xavier");
        chico.setInicioDoCurso("03/2025");
        chico.lancarNota("8.7");
        chico.lancarNota("9.2");
        chico.lancarNota("9");
        chico.lancarNota("9.6");
        System.out.println(chico.getSituacao());
        System.out.println("Desempenho: " + chico.getDesempenho());
        System.out.println(chico.getClass().getSimpleName());
        System.out.println("------------------------------------------------------------");

        Aluno roberto = AlunoFactory.criar("GRADUACAO", "2025006", "Roberto Carlos");
        roberto.setInicioDoCurso("08/2025");
        roberto.lancarNota("7.9");
        roberto.lancarNota("8.6");
        System.out.println(roberto.getSituacao());
        System.out.println("Desempenho: " + roberto.getDesempenho());
        System.out.println(roberto.getClass().getSimpleName());
        System.out.println("------------------------------------------------------------");

         Aluno pos = AlunoFactory.criar("POS", "2026009", "Isaac Newton");
         pos.setInicioDoCurso("01/2026");
         pos.lancarNota("C");
         System.out.println(pos.getSituacao());
        System.out.println("Desempenho: " + pos.getDesempenho());
         System.out.println(pos.getClass().getSimpleName());

        System.out.println();
    }

    /**
     * Demonstra alguns casos de falha.
     */
    private static void demonstrarCasosDeBorda() {
        System.out.println("===== CASOS DE BORDA =====");
        System.out.println("Tipos aceitos pela fabrica: " + AlunoFactory.getTiposDisponiveis());

        // 1. Tipo inexistente
        Aluno desconhecido = AlunoFactory.criar("MESTRADO", "2026011", "Katherine Johnson");
        System.out.println("Aluno criado para o tipo MESTRADO: " + desconhecido);

        if (desconhecido == null) {
            System.out.println("Resultado: nenhum aluno foi criado.");
        }
        System.out.println("------------------------------------------------------------");

        // 2. Conceito invalido na pós-graduação
        Aluno posInvalida = AlunoFactory.criar("POS","2026012","Grace Hopper");
        posInvalida.lancarNota("E");

        System.out.println("Notas da pós-graduação: " + posInvalida.getNotas());
        System.out.println("Situacao: " + posInvalida.getSituacao());
        System.out.println("------------------------------------------------------------");

        // 3. Nota invalido na graduação
        Aluno gradInvalida = AlunoFactory.criar("GRADUACAO","2026012","Grace Hopper");
        gradInvalida.lancarNota("9.5");
        gradInvalida.lancarNota("8.6");

        System.out.println("Notas da raduação: " + gradInvalida.getNotas());
        System.out.println("Situacao: " + gradInvalida.getSituacao());
        System.out.println("------------------------------------------------------------");

        // 3. Nota maior que 10
        Aluno graduacaoInvalida = AlunoFactory.criar("GRADUACAO","2026013","Carl Sagan");
        graduacaoInvalida.lancarNota("11");

        System.out.println("Notas da graduação: " + graduacaoInvalida.getNotas());
        System.out.println("------------------------------------------------------------");

        // 4. Texto que não é número
        graduacaoInvalida.lancarNota("abc");

        System.out.println("Notas da graduação após texto invalido: "
                        + graduacaoInvalida.getNotas()
        );
        System.out.println("------------------------------------------------------------");

        // 5. Nota em excesso

        graduacaoInvalida.lancarNota("8");
        graduacaoInvalida.lancarNota("7");
        graduacaoInvalida.lancarNota("6");
        graduacaoInvalida.lancarNota("9");

        System.out.println("Notas finais da graduação: " + graduacaoInvalida.getNotas());
        System.out.println("------------------------------------------------------------");

        // 6. Conceito em excesso na pós-graduação
        Aluno posExcesso = AlunoFactory.criar("POS","2026014","Marie Curie");
        posExcesso.lancarNota("A");
        posExcesso.lancarNota("B");

        System.out.println("Notas finais da pós-graduação: " + posExcesso.getNotas());
        System.out.println("------------------------------------------------------------");

        System.out.println();
    }
}
