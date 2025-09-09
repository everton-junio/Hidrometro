package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.ifpb.Hidrometro.SaidaHidrometro.salvarSaida;

public class ProcessamentoHidrometro {
    private static final String INPUT_FILE = "C:\\Users\\evert\\Documentos\\Facul\\PDP\\Hidrometro\\src\\main\\java\\org\\ifpb\\Arquivos\\entrada.txt";

    public static void processarFluxo(DadosHidrometro dadosHidrometro) {
        String fluxo;
        try {
            fluxo = Files.readString(Path.of(INPUT_FILE));
        } catch (IOException e) {
            System.out.println("Erro ao ler entrada: " + e.getMessage());
            return;
        }

        if (dadosHidrometro.getPosicaoProcessada() >= fluxo.length()) {
            System.out.println("Nenhum novo fluxo a processar.");
            return;
        }

        String novoFluxo = fluxo.substring(dadosHidrometro.getPosicaoProcessada());
        dadosHidrometro.setPosicaoProcessada(fluxo.length());

        for (char c : novoFluxo.toCharArray()) {
            switch (c) {
                case 'A' -> dadosHidrometro.setAgua(dadosHidrometro.getAgua() + 1);
                case 'B' -> dadosHidrometro.setAr(dadosHidrometro.getAr() + 1);
                case 'C' -> dadosHidrometro.setAusencia(dadosHidrometro.getAusencia() + 1);
            }
        }

        salvarSaida(dadosHidrometro);
        System.out.println("Processado lote: " + novoFluxo);
    }
}
