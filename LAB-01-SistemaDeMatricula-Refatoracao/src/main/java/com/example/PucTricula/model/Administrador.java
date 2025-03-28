package main.java.com.example.PucTricula.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrador extends Usuario {
    
    public Administrador(String nome, String email, String senha) {
        super(tipo = "administrador", nome, email, senha);
    }

    public void cadastrarAluno(List<Usuario> usuarios, Scanner scanner) {
        System.out.print(">> Nome: ");
        String nomeAluno = scanner.nextLine();
        System.out.print(">> Email: ");
        String emailAluno = scanner.nextLine();
        System.out.print(">> Senha: ");
        String senhaAluno = scanner.nextLine();

        Usuario aluno = new Aluno(nomeAluno, emailAluno, senhaAluno);
        usuarios.add(aluno);
        salvarUsuario(aluno);
        System.out.println(">> Aluno cadastrado com sucesso!");
    }

    public void cadastrarProfessor(List<Usuario> usuarios, Scanner scanner){
                    System.out.print(">> Nome: ");
                    String nomeProfessor = scanner.nextLine();
                    System.out.print(">> Email: ");
                    String emailProfessor = scanner.nextLine();
                    System.out.print(">> Senha: ");
                    String senhaProfessor = scanner.nextLine();
                    Usuario professor = new Professor(nomeProfessor, emailProfessor, senhaProfessor);
                    usuarios.add(professor);
                    salvarUsuario(professor);
                    System.out.println(">> Professor cadastrado com sucesso!");
    }

    private void salvarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.csv", true))) {
            writer.write(usuario.getTipo() + "," + usuario.getNome() + "," + usuario.getEmail() + "," + usuario.getSenha());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public void listarUsuarios(List<Usuario> usuarios, Scanner scanner){
        System.out.println("\n>> Usuários cadastrados:");
        for (Usuario u : usuarios) {
            System.out.println("- " + u.getNome() + " (" + u.getEmail() + ")");
        }
    }

    public void cadastrarDisciplina(List<Disciplina> disciplinas, Scanner scanner){
        System.out.print(">> Nome da Disciplina: ");
        String nomeDisciplina = scanner.nextLine();

        System.out.print(">> Créditos: ");
        int creditos = scanner.nextInt();
        scanner.nextLine();

        System.out.print(">> Valor mensal da disciplina: ");
        double custo = scanner.nextDouble();
        scanner.nextLine();

        System.out.print(">> Data limite para matrícula (dd/MM/yyyy): ");
        String dataLimiteInput = scanner.nextLine();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataLimite = LocalDate.parse(dataLimiteInput, formatter);

        disciplinas.add(new Disciplina(nomeDisciplina, creditos, dataLimite, custo));
        salvarDisciplinas(disciplinas);

        System.out.println(">> Disciplina cadastrada com sucesso!");
    }
    
    private void salvarDisciplinas(List<Disciplina> disciplinas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("disciplinas.csv"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Disciplina d : disciplinas) {
                writer.write(d.getNome() + "," + d.getCreditos() + "," + d.getCusto() + "," + d.getDataLimiteMatricula().format(formatter));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    /*public void atribuirAluno(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner){
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
    }*/

    public void atribuirProfessor(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner){
        System.out.println(">> Selecione um professor para atribuir a uma disciplina:");
                    List<Professor> listaProfessores = new ArrayList<>();
                    for (Usuario u : usuarios) {
                        if (u instanceof Professor) {
                            listaProfessores.add((Professor) u);
                        }
                    }
                    for (int i = 0; i < listaProfessores.size(); i++) {
                        System.out.println(i + ". " + listaProfessores.get(i).getNome());
                    }
                    int professorIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (professorIndex >= 0 && professorIndex < listaProfessores.size()) {
                        Professor professorSelecionado = listaProfessores.get(professorIndex);
                        System.out.println(">> Selecione uma disciplina para atribuir ao professor:");
                        for (int i = 0; i < disciplinas.size(); i++) {
                            System.out.println(i + ". " + disciplinas.get(i).getNome());
                        }
                        int disciplinaIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (disciplinaIndex >= 0 && disciplinaIndex < disciplinas.size()) {
                            disciplinas.get(disciplinaIndex).atribuirProfessor(professorSelecionado);
                            System.out.println("Professor " + professorSelecionado.getNome()
                                    + " atribuído à disciplina " + disciplinas.get(disciplinaIndex).getNome());
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

    public void atualizarInformacoes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarInformacoes'");
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

    public static void calcularMensalidades(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
        System.out.println("\n--- Sistema de Cobrança ---");
        for (Usuario u : usuarios) {
            if (u instanceof Aluno) {
                Aluno aluno = (Aluno) u;
                double totalMensalidade = 0;
                for (Disciplina d : disciplinas) {
                    if (d.getAlunosMatriculados().contains(aluno)) {
                        totalMensalidade += d.getCusto();
                    }
                }
                System.out.println("Aluno: " + aluno.getNome() + " - Mensalidade: R$ " + totalMensalidade);
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

    public void sair(Scanner scanner){
        System.out.println(">> Encerrando sistema...\n >> Obrigado!");
                    scanner.close();
    }
    /*public void fazerLogout(Usuario usuario, Scanner scanner){
    System.out.println(" >> Fazendo logout...");
                    usuarioLogado = null;
                    System.out.println("\n\n\n--- Seja bem vindo ao PUCTricula! ---\n >> Realize o seu login.");
                    do {
                        usuarioLogado = realizarLogin(usuarios, scanner);
                    } while (usuarioLogado == null);
                }*/
}
