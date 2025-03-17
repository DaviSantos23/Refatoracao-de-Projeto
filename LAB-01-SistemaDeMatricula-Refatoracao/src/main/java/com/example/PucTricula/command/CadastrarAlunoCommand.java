package main.java.com.example.PucTricula.command;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Usuario;


public class CadastrarAlunoCommand implements Command {
    private Administrador administrador;
    private List<Usuario> usuarios;
    private Scanner scanner;

    public CadastrarAlunoCommand(Administrador administrador, List<Usuario> usuarios, Scanner scanner) {
        this.administrador = administrador;
        this.usuarios = usuarios;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        administrador.cadastrarAluno(usuarios, scanner);
    }
}