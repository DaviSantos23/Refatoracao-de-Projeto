package main.java.com.example.PucTricula.command;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Usuario;

public class CancelarMatriculaCommand implements Command{
    private Usuario usuario;
    private List<Usuario> usuarios;
    private List<Disciplina> disciplinas;
    private Scanner scanner;

    public CancelarMatriculaCommand(Usuario usuario, List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
        this.usuario = usuario;
        this.usuarios = usuarios;
        this.disciplinas = disciplinas;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        usuario.cancelarMatricula(usuarios, disciplinas, scanner);
    }

}
