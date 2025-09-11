package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.Thread.sleep;
import static org.ifpb.Hidrometro.ConfiguracaoHidrometro.carregarConfiguracao;
import static org.ifpb.Hidrometro.SaidaHidrometro.salvarSaida;

public class ProcessamentoHidrometro implements Runnable {
    private static final String INPUT_FILE = "C:\\Users\\evert\\Documentos\\Facul\\PDP\\Hidrometro\\src\\main\\java\\org\\ifpb\\Arquivos\\entrada.txt";

    private final DadosHidrometro dadosHidrometro;
    private boolean rodando = true;

    public ProcessamentoHidrometro(DadosHidrometro dadosHidrometro) {
        this.dadosHidrometro = dadosHidrometro;
    }

    // 🔹 processamento de um lote de fluxo
    public void processarFluxo() throws InterruptedException {
        String fluxo;
        try {
            fluxo = Files.readString(Path.of(INPUT_FILE));
        } catch (IOException e) {
            System.out.println("Erro ao ler entrada: " + e.getMessage());
            return;
        }

        if (dadosHidrometro.getPosicaoProcessada() >= fluxo.length()) {
            return;
        }

        String novoFluxo = fluxo.substring(dadosHidrometro.getPosicaoProcessada());
        dadosHidrometro.setPosicaoProcessada(fluxo.length());


        for (char c : novoFluxo.toCharArray()) {
            sleep(dadosHidrometro.getVelocidadeDeLeituraFluxoDeAguaEmSegundos());
            switch (c) {
                case 'A' -> dadosHidrometro.setAgua(dadosHidrometro.getAgua() + 1);
                case 'B' -> dadosHidrometro.setAr(dadosHidrometro.getAr() + 1);
                case 'C' -> dadosHidrometro.setAusencia(dadosHidrometro.getAusencia() + 1);
            }
            salvarSaida(dadosHidrometro);
            carregarConfiguracao(dadosHidrometro);
        }

    }

    @Override
    public void run() {
        while (rodando) {
            try {
                processarFluxo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                sleep(dadosHidrometro.getVelocidadeDeLeituraFluxoDeAguaEmSegundos());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void parar() {
        rodando = false;
    }
}
