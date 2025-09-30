package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaidaHidrometro {
    // MUDANÇA: A constante do arquivo de saída foi removida, pois não é mais necessária.

    // MUDANÇA: O método agora recebe o caminho do arquivo de saída (outputFile) como parâmetro.
    public static void salvarSaida(DadosHidrometro dadosHidrometro, String outputFile) {
        double consumoAgua = dadosHidrometro.getBase() + (dadosHidrometro.getAgua() * dadosHidrometro.getVazao() * dadosHidrometro.getPressao());
        double consumoAr = dadosHidrometro.getAr() * (dadosHidrometro.getVazao() * dadosHidrometro.getPesoAr());
        double perdas = dadosHidrometro.getAusencia() * dadosHidrometro.getDescontoAusencia();

        double totalLitros = consumoAgua + consumoAr - perdas;
        double contaDagua = dadosHidrometro.getAgua() * dadosHidrometro.getValorPorAgua();

        // MUDANÇA: Usa o parâmetro 'outputFile' ao invés da constante fixa.
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {
            // Dica: Adicionei o nome do arquivo no relatório para ficar mais claro qual simulador o gerou.
            pw.println("----- Relatório Hidrômetro: " + outputFile + " -----");
            pw.println("Água detectada (A): " + dadosHidrometro.getAgua());
            pw.println("Ar detectado (B): " + dadosHidrometro.getAr());
            pw.println("Ausência (C): " + dadosHidrometro.getAusencia());
            pw.printf("Consumo de Água (ajustado): %.2f litros%n", consumoAgua);
            pw.printf("Interferência do Ar: %.2f litros%n", consumoAr);
            pw.printf("Perdas por ausência: %.2f litros%n", perdas);
            pw.printf("TOTAL FINAL: %.2f litros%n", totalLitros);
            pw.printf("VALOR DA CONTA: R$ %.2f%n", contaDagua);
        } catch (IOException e) {
            System.out.println("Erro ao salvar saída em " + outputFile + ": " + e.getMessage());
        }
    }
}
// modificado por ilana.costa