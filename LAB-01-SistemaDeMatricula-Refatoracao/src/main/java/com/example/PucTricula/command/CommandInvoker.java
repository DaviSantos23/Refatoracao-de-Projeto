package main.java.com.example.PucTricula.command;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private Map<Integer, Command> comandos = new HashMap<>();

    public void adicionarComando(int opcao, Command comando) {
        comandos.put(opcao, comando);
    }

    public void executarComando(int opcao) {
        if (comandos.containsKey(opcao)) {
            comandos.get(opcao).execute();
        } else {
            System.out.println(">> Opção inválida, tente novamente.");
        }
    }
}