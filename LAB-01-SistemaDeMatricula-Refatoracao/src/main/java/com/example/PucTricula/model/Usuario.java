package main.java.com.example.PucTricula.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Usuario implements Serializable {
    protected static String tipo;
    private String nome;
    private String email;
    private String senha;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String tipo, String nome, String email, String senha) {
        this.tipo = tipo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validarSenha(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }

    public void atribuirAluno(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner){
        System.out.println(">> Selecione um aluno para matrícula:");
                List<Aluno> alunos = new ArrayList<>();
                for (Usuario u : usuarios) {
                    if (u instanceof Aluno) {
                        alunos.add((Aluno) u);
                    }
                }
                for (int i = 0; i < alunos.size(); i++) {
                    System.out.println(i + ". " + alunos.get(i).getNome());
                }
                int alunoIndex = scanner.nextInt();
                scanner.nextLine();
                if (alunoIndex >= 0 && alunoIndex < alunos.size()) {
                    Aluno alunoSelecionado = alunos.get(alunoIndex);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println(">> Selecione uma disciplina:");
                    for (int i = 0; i < disciplinas.size(); i++) {
                        System.out.println(i + ". " + disciplinas.get(i).getNome() + " (Custo: R$ " + disciplinas.get(i).getCusto() + ", Data limite: " 
                        + disciplinas.get(i).getDataLimiteMatricula().format(formatter) + ")");
                    }
                    int disciplinaIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (disciplinaIndex >= 0 && disciplinaIndex < disciplinas.size()) {
                        disciplinas.get(disciplinaIndex).matricularAluno(alunoSelecionado);
                        salvarMatricula(alunoSelecionado.getNome(), disciplinas.get(disciplinaIndex).getNome());
                    }
                }
    }

    private static void salvarMatricula(String nomeAluno, String nomeDisciplina) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("matriculas.csv", true))) {
            writer.write(nomeAluno + "," + nomeDisciplina);
            writer.newLine();
        } catch (IOException e) {

            System.out.println("Erro ao salvar matrícula: " + e.getMessage());
        }
    }

    public void gerarCurriculoSemestral(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
        System.out.println("\n>> Disciplinas cadastradas, seus professores e alunos:");
                    for (Disciplina d : disciplinas) {
                        System.out.println("- " + d.getNome() + " (Créditos: " + d.getCreditos() + ")");
                        System.out.println("  Professor: " + (d.getProfessor() != null ? d.getProfessor().getNome()
                                : "Nenhum professor atribuído"));
                        List<Aluno> alunosMatriculados = d.getAlunosMatriculados();
                        if (alunosMatriculados.isEmpty()) {
                            System.out.println("  >> Nenhum aluno matriculado.");
                        } else {
                            System.out.println("  >> Alunos matriculados:");
                            for (Aluno alunoMatriculado : alunosMatriculados) {
                                System.out.println("    - " + alunoMatriculado.getNome());
                            }
                        }
                    }
    }

    public static void cancelarMatricula(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
        System.out.println("\n--- Cancelamento de Matrícula ---");
        System.out.println(">> Selecione um aluno:");
        List<Aluno> alunos = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u instanceof Aluno) {
                alunos.add((Aluno) u);
            }
        }
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(i + ". " + alunos.get(i).getNome());
        }
        int alunoIndex = scanner.nextInt();
        scanner.nextLine();
        
        if (alunoIndex >= 0 && alunoIndex < alunos.size()) {
            Aluno alunoSelecionado = alunos.get(alunoIndex);
            System.out.println(">> Selecione uma disciplina:");
            for (int i = 0; i < disciplinas.size(); i++) {
                System.out.println(i + ". " + disciplinas.get(i).getNome());
            }
            int disciplinaIndex = scanner.nextInt();
            scanner.nextLine();
            
            if (disciplinaIndex >= 0 && disciplinaIndex < disciplinas.size()) {
                disciplinas.get(disciplinaIndex).cancelarMatricula(alunoSelecionado);
            }
        }
    }

    public static void visualizarDisciplinasProfessor(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
        System.out.println("\n--- Grade do Professor ---");
        System.out.println(">> Selecione um professor:");
        List<Professor> professores = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u instanceof Professor) {
                professores.add((Professor) u);
            }
        }
        for (int i = 0; i < professores.size(); i++) {
            System.out.println(i + ". " + professores.get(i).getNome());
        }
        int professorIndex = scanner.nextInt();
        scanner.nextLine();
        
        if (professorIndex >= 0 && professorIndex < professores.size()) {
            Professor professorSelecionado = professores.get(professorIndex);
            System.out.println(">> Disciplinas ministradas por " + professorSelecionado.getNome() + ":\n");
            boolean encontrouDisciplina = false;
            for (Disciplina d : disciplinas) {
                if (d.getProfessor() != null && d.getProfessor().equals(professorSelecionado)) {
                    System.out.println("- " + d.getNome());
                    encontrouDisciplina = true;
                }
            }
            if (!encontrouDisciplina) {
                System.out.println(">> Este professor não ministra nenhuma disciplina.\n");
            }
        }
    }

}