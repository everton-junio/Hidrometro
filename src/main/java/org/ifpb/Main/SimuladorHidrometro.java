package org.ifpb.Main;

import org.ifpb.Dados.DadosHidrometro;
import org.ifpb.Hidrometro.DisplayHidrometro;
import org.ifpb.Hidrometro.ProcessamentoHidrometro;

import java.util.Scanner;

import static java.lang.System.exit;
import static org.ifpb.Hidrometro.ConfiguracaoHidrometro.carregarConfiguracao;

public class SimuladorHidrometro {

    public static void main(String[] args) {
        DadosHidrometro dadosHidrometro = new DadosHidrometro();
        carregarConfiguracao(dadosHidrometro);

        ProcessamentoHidrometro proc = new ProcessamentoHidrometro(dadosHidrometro);
        Thread threadProc = new Thread(proc);
        threadProc.start();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU HIDRÔMETRO ---");
            System.out.println("1. Sair");
            System.out.print("Escolha: ");

            DisplayHidrometro.mostrar(dadosHidrometro);
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Encerrando...");
                    exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
