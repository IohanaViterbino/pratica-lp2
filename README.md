# Prática 02 — Herança e Polimorfismo

Sistema de lançamento de notas de alunos. A superclasse `Aluno` concentra o que
todo aluno tem em comum; cada subclasse mede o tempo de curso e calcula a nota
final de um jeito diferente.

## Como compilar e executar

```bash
# a partir da pasta do projeto
javac -d out *.java
java -cp out SistemaNotasView
```

O projeto usa apenas recursos disponíveis desde o **JDK 8** e foi testado até o
**JDK 26**. Verifique a sua versão com `java -version`.

O projeto **já compila e roda** como está: o relatório sai incompleto porque as
partes marcadas com `// TODO` ainda não foram implementadas. Compile e execute a
cada item concluído para acompanhar o progresso.

## Arquivos

| Arquivo | Situação |
|---|---|
| `Aluno.java` | **Pronto.** Superclasse: estado comum (inclusive o início do curso), constantes de situação, os métodos que as subclasses sobrescrevem e os métodos que valem para todos. Não precisa alterar. |
| `AlunoTecnico.java` | **Pronto — use como exemplo.** Ano letivo, 4 bimestres, prazo de 5 anos. |
| `AlunoGraduacao.java` | Item 2 — esqueleto com `// TODO`. |
| `AlunoPosGraduacao.java` | Item 3 — esqueleto com `// TODO`. |
| `AlunoFactory.java` | Item 4 — falta completar `criar(...)`. |
| `SistemaNotasView.java` | Itens 5, 6 e 7 — o cliente do sistema. |

## Regras de negócio

| Tipo | Tempo de curso | Prazo máximo | Nota final | Situação |
|---|---|---|---|---|
| Técnico | ano (1º, 2º, …) | 5 anos | soma de 4 notas bimestrais / 4 | ≥ 6 aprovado; < 6 recuperação |
| Graduação | semestre (2026.1) | 14 semestres | soma de 3 notas de unidade / 3 | ≥ 6 aprovado; 4 ≤ média < 6 recuperação; < 4 reprovado |
| Pós-graduação | mês | 24 meses | conceito A, B, C ou D | A/B aprovado; C recuperação; D reprovado |

### Como o tempo de curso é medido

`Aluno` guarda **uma única** informação de tempo: o início do curso (mês e ano),
que o cliente informa com `setInicioDoCurso("03/2020")`. O "hoje" do sistema são
as constantes `Aluno.ANO_ATUAL` e `Aluno.MES_ATUAL` — e não a data da máquina,
para o relatório sair igual em qualquer computador. O método
`getMesesDecorridos()`, herdado por todas as subclasses, diz quantos meses se
passaram entre um e outro.

Ano, semestre, mês, período e prazo são todos **calculados** a partir daí, e é
justamente aí que cada curso se diferencia: 12 meses = 1 ano no técnico, 6 meses
= 1 semestre na graduação, 1 mês = 1 mês na pós-graduação. Nenhuma subclasse
guarda contador próprio. Veja `AlunoTecnico.getTempoDecorrido()`, que já vem
pronto como exemplo.

## O que você deve entregar

Veja o enunciado completo em `Pratica02.pdf`. Em resumo:

1. Diagrama UML de classes do sistema.
2. `AlunoGraduacao` implementada.
3. `AlunoPosGraduacao` implementada.
6. `AlunoFactory` completa.
5. `SistemaNotasView` com relatório polimórfico, resumo por situação, prazos e casos de borda.
6. Um quarto tipo de aluno, funcionando **sem alterar** a estrutura dos métodos do item 5.
7. Este `README.md` preenchido.

---

## PREENCHA ABAIXO (item 7)

**Nomes:**  IOHANA MARIA BARROSO DA SILVA VITERBINO, LORENNA MIRELY DE SOUSA SILVA

**Matrículas:** 20250065181, 20250029292 

**Versão do JDK utilizada:** java 24.0.1 e 21.0.9

**Comandos para compilar e executar:**
Decidimos manter o comandos sugeridos.   

```bash
# a partir da pasta do projeto
javac -d out *.java
java -cp out SistemaNotasView
```

**Quarto tipo de aluno criado (item 5):** Não implementamos uma nova subclasse de Aluno. 

**Por que o cliente consegue tratar todos os alunos do mesmo jeito?**  Porque todos eles herdam da mesma superclasse e seguem a mesma interface de métodos. O polimorfismo permite que cada subclasse execute esse contrato de acordo com suas próprias regras.

**Por que o cliente não precisa de `instanceof` nem de casting em nenhum método?**  Porque a superclasse Aluno define os métodos comuns a todos os tipos de alunos, enquanto as subclasses implementam ou sobrescrevem os métodos de acordo com suas próprias regras de negócio. Assim, o cliente pode manipular todos os objetos por meio de uma referência do tipo Aluno, já que em tempo de execução, o Java indentificará qual implementação sobrescrita deverá ser executada.

**Por que `getMedia()` é `private` nas subclasses e `getNotas()` devolve uma cópia da lista?**  getMedia() é private porque o cálculo da média é feito internamente em cada subclasse para determinar a situação do aluno. Além disso, cada subclasse possui uma forma diferente de calcular a nota final. Já o método getNotas() retorna uma cópia da lista para impedir alterações diretas das notas armazenadas dentro do objeto. 

**O que acontece com um aluno cuja nota/conceito não foi lançado?** Caso o aluno não possua notas o suficiente para avaliar a situação, é retornado como 'Não avaliado'.
