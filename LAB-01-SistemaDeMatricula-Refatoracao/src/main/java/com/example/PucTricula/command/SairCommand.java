package main.java.com.example.PucTricula.command;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Usuario;

public class SairCommand implements Command{
    private Administrador administrador;
    private Scanner scanner;

    public SairCommand(Administrador admin, Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        administrador.sair(scanner);;
    }

}
