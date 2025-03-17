package main.java.com.example.PucTricula.command;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Usuario;

public class CadastrarDisciplinaCommand implements Command{
    private Administrador administrador;
    private List<Disciplina> disciplinas;
    private Scanner scanner;

    public CadastrarDisciplinaCommand(Administrador administrador, List<Disciplina> disciplinas, Scanner scanner) {
        this.administrador = administrador;
        this.disciplinas = disciplinas;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        administrador.cadastrarDisciplina(disciplinas, scanner);
    }
}
