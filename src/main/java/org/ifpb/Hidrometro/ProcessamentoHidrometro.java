package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.lang.Thread.sleep;

// MUDANÇA: Importando as versões modificadas dos métodos
import static org.ifpb.Hidrometro.CarregarConfiguracaoHidrometro.carregarConfiguracao;
import static org.ifpb.Hidrometro.SaidaHidrometro.salvarSaida;

public class ProcessamentoHidrometro implements Runnable {
    // MUDANÇA: Removemos a constante do arquivo de entrada daqui.

    private final DadosHidrometro dadosHidrometro;
    private boolean rodando = true;

    // MUDANÇA: Adicionamos os caminhos dos arquivos como membros da classe.
    private final String inputFile;
    private final String configFile;
    private final String outputFile;

    // MUDANÇA: O construtor agora recebe os caminhos dos arquivos.
    public ProcessamentoHidrometro(DadosHidrometro dadosHidrometro, String inputFile, String configFile, String outputFile) {
        this.dadosHidrometro = dadosHidrometro;
        this.inputFile = inputFile;
        this.configFile = configFile;
        this.outputFile = outputFile;
    }

    public void processarFluxo() throws InterruptedException {
        String fluxo;
        try {
            // MUDANÇA: Usa a variável 'inputFile'
            fluxo = Files.readString(Path.of(this.inputFile));
        } catch (IOException e) {
            System.out.println("Erro ao ler entrada: " + e.getMessage());
            return;
        }

        if (dadosHidrometro.getPosicaoProcessada() >= fluxo.length()) {
            // Opcional: para o simulador se o arquivo de entrada terminou.
            this.rodando = false;
            return;
        }

        String novoFluxo = fluxo.substring(dadosHidrometro.getPosicaoProcessada());
        dadosHidrometro.setPosicaoProcessada(fluxo.length());

        for (char c : novoFluxo.toCharArray()) {
            if (!rodando) { break; }
            sleep((long) dadosHidrometro.getVelocidadeDeLeituraFluxoDeAguaEmSegundos());
            switch (c) {
                case 'A' -> dadosHidrometro.setAgua(dadosHidrometro.getAgua() + 1);
                case 'B' -> dadosHidrometro.setAr(dadosHidrometro.getAr() + 1);
                case 'C' -> dadosHidrometro.setAusencia(dadosHidrometro.getAusencia() + 1);
            }
            // MUDANÇA: Passa os caminhos para os métodos de salvar e carregar.
            salvarSaida(dadosHidrometro, this.outputFile);
            carregarConfiguracao(dadosHidrometro, this.configFile);
        }
    }

    @Override
    public void run() {
        System.out.println("Iniciando processamento para " + inputFile);
        while (rodando) {
            try {
                processarFluxo();
                // Adicionado um sleep para não ler o arquivo em loop frenético se ele acabar
                sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                this.rodando = false;
            }
        }
        System.out.println("Processamento finalizado para " + inputFile);
    }

    public void parar() {
        rodando = false;
    }
}
// modificado por ilana.costa