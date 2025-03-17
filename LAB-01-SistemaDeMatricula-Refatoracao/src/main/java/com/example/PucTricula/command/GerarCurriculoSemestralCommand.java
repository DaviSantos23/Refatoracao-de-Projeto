package main.java.com.example.PucTricula.command;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Usuario;

public class GerarCurriculoSemestralCommand implements Command{
    
    private List<Usuario> usuarios;
    private Usuario usuario;
    private List<Disciplina> disciplinas;
    private Scanner scanner;

    public GerarCurriculoSemestralCommand(Usuario usuario, List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
        this.usuario = usuario;
        this.usuarios = usuarios;
        this.disciplinas = disciplinas;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        usuario.gerarCurriculoSemestral(usuarios, disciplinas, scanner);
    }
}
