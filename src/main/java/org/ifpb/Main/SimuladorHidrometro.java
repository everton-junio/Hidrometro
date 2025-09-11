package org.ifpb.Main;

import org.ifpb.Dados.DadosHidrometro;
import org.ifpb.Hidrometro.DisplayHidrometro;
import org.ifpb.Hidrometro.ProcessamentoHidrometro;

import java.util.Scanner;

import static org.ifpb.Hidrometro.CarregarConfiguracaoHidrometro.carregarConfiguracao;

public class SimuladorHidrometro {

    public static void main(String[] args) {
        DadosHidrometro dadosHidrometro = new DadosHidrometro();
        carregarConfiguracao(dadosHidrometro);

        ProcessamentoHidrometro processamentoHidrometro = new ProcessamentoHidrometro(dadosHidrometro);
        Thread threadProcessamentoHidrometro = new Thread(processamentoHidrometro);
        threadProcessamentoHidrometro.start();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU HIDRÔMETRO ---");
            System.out.println("1. Ver painel do hidrômetro");
            System.out.println("2. Sair");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> DisplayHidrometro.mostrarDisplay(dadosHidrometro);
                case 2 -> {
                    System.out.println("Encerrando...");

                    processamentoHidrometro.parar();
                    try {
                        threadProcessamentoHidrometro.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
