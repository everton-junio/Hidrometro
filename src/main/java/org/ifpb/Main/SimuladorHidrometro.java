package org.ifpb.Main;
import org.ifpb.Dados.DadosHidrometro;

import java.util.*;

import static org.ifpb.Hidrometro.ConfiguracaoHidrometro.carregarConfiguracao;
import static org.ifpb.Hidrometro.DisplayHidrometro.mostrarHidrometro;
import static org.ifpb.Hidrometro.ProcessamentoHidrometro.processarFluxo;


public class SimuladorHidrometro {

    public static void main(String[] args) {
        DadosHidrometro dadosHidrometro = new DadosHidrometro();
        carregarConfiguracao(dadosHidrometro);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU HIDRÔMETRO ---");
            System.out.println("1. Ver números do hidrômetro");
            System.out.println("2. Processar próximo lote do fluxo");
            System.out.println("3. Recarregar configuração");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> mostrarHidrometro(dadosHidrometro);
                case 2 -> processarFluxo(dadosHidrometro);
                case 3 -> carregarConfiguracao(dadosHidrometro);
                case 4 -> {
                    System.out.println("Encerrando...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
