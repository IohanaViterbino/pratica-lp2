/**
 * Fábrica de alunos.
 *
 * O cliente informa apenas o TIPO do aluno. Em um sistema real, este TIPO
 * poderia vir de um formulário ou do banco de dados por exemplo.
 * Então o cliente recebe de volta um Aluno já instanciado. Este é
 * o ÚNICO lugar do sistema que decide qual subclasse instanciar: em todos os
 * outros lugares o programa trabalha com o tipo Aluno e não pergunta mais
 * nada.
 * 
 * A vantagem de usarmos esse padrão de projeto é que:
 * para adicionar novos tipos de aluno, alteramos apenas este arquivo,
 * mantendo todo o resto do sistema funcionando. Isto é escalável.
 * 
 * Todo aluno sai da fábrica como CALOURO: o início do curso dele é a data de
 * referência do sistema (Aluno.ANO_ATUAL e Aluno.MES_ATUAL). Para colocar o
 * aluno em outro ponto do curso, o cliente chama setInicioDoCurso("mes/ano")
 * no objeto devolvido - e não precisa saber de que tipo ele é.
 *
 * ---------------------------------------------------------------------
 * ITEM 4 DA PRÁTICA: complete o metodo criar() e getTiposDisponiveis().
 * ---------------------------------------------------------------------
 */
public class AlunoFactory {

    public static Aluno criar(String tipo, String matricula, String nome) {
        if (tipo == null) {
            System.out.println("[aviso] Tipo de aluno nao informado.");
            return null;
        }

        String chave = tipo.trim().toUpperCase();

        // TODO 4.1: "GRADUACAO" deve criar um AlunoGraduacao.
        // TODO 4.2: "POS" deve criar um AlunoPosGraduacao.
        // TODO 4.3: quando o tipo nao for reconhecido, imprima um aviso
        // informando os tipos validos com getTiposDisponiveis() e retorne null.

        return switch (chave) {
            case "TECNICO" -> new AlunoTecnico(matricula, nome);
            case "GRADUACAO" -> new AlunoGraduacao(matricula, nome);
            case "POS" -> new AlunoPosGraduacao(matricula, nome);
            default -> {
                System.out.println("[aviso] Tipo de aluno não informado corretamente, insira um dessas:"
                        + getTiposDisponiveis());
                yield null;
            }
        };
    }

    public static String getTiposDisponiveis() {
        // TODO 4.4: mantenha esta lista em dia conforme voce registra os tipos:
        // Ex.: "TECNICO, GRADUACAO, NOVO TIPO"
        return "TECNICO, GRADUACAO, POS";
    }
}
