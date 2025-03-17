package main.java.com.example.PucTricula.command;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Usuario;

public class CalcularMensalidadesCommand implements Command{
    private Administrador administrador;
    private List<Usuario> usuarios;
    private List<Disciplina> disciplinas;
    private Scanner scanner;

    public CalcularMensalidadesCommand(Administrador administrador, List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
        this.administrador = administrador;
        this.usuarios = usuarios;
        this.disciplinas = disciplinas;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        administrador.calcularMensalidades(usuarios, disciplinas, scanner);
    }
}
