package main.java.com.example.PucTricula.command;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Usuario;

public class CadastrarProfessorCommand implements Command{
    private Administrador administrador;
    private List<Usuario> usuarios;
    private Scanner scanner;

    public CadastrarProfessorCommand(Administrador administrador, List<Usuario> usuarios, Scanner scanner) {
        this.administrador = administrador;
        this.usuarios = usuarios;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        administrador.cadastrarProfessor(usuarios, scanner);
    }
}
