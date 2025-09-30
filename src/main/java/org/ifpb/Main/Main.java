package org.ifpb.Main;

import org.ifpb.Dados.DadosHidrometro;
import org.ifpb.Hidrometro.DisplayHidrometro; // Import necessário
import org.ifpb.Hidrometro.ProcessamentoHidrometro;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int NUMERO_DE_SIMULADORES = 5;
    private static final String CAMINHO_BASE_ARQUIVOS = "src/main/java/org/ifpb/Arquivos/";

    public static void main(String[] args) {
        System.out.println("Iniciando " + NUMERO_DE_SIMULADORES + " simuladores...");
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= NUMERO_DE_SIMULADORES; i++) {
            final String configFile = CAMINHO_BASE_ARQUIVOS + "config_" + i + ".txt";
            final String inputFile = CAMINHO_BASE_ARQUIVOS + "entrada_" + i + ".txt";
            final String outputFile = CAMINHO_BASE_ARQUIVOS + "saida_" + i + ".txt";
            // MUDANÇA: Definimos o nome do arquivo de imagem de saída
            final String imageOutputFile = CAMINHO_BASE_ARQUIVOS + "hidrometro_final_" + i + ".png";
            final int simuladorId = i;

            Runnable tarefaSimulador = () -> {
                System.out.println("Thread " + simuladorId + ": Configurando...");
                DadosHidrometro dados = new DadosHidrometro();

                org.ifpb.Hidrometro.CarregarConfiguracaoHidrometro.carregarConfiguracao(dados, configFile);

                ProcessamentoHidrometro processador = new ProcessamentoHidrometro(dados, inputFile, configFile, outputFile);

                // A simulação roda aqui
                processador.run();

                // MUDANÇA: Ao final da simulação, chamamos o método para gerar a imagem
                System.out.println("Thread " + simuladorId + ": Gerando imagem de resultado...");
                DisplayHidrometro.gerarImagemFinal(dados, imageOutputFile);
            };

            Thread thread = new Thread(tarefaSimulador);
            thread.setName("Simulador-" + i);
            threads.add(thread);
            thread.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println("Thread principal interrompida.");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Todos os simuladores finalizaram. Verifique os arquivos de saída e as imagens na pasta 'Arquivos'.");
    }
}
//modificado por ilana.costa