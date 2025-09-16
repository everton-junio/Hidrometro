package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaidaHidrometro {
    private static final String OUTPUT_FILE = "src/main/java/org/ifpb/Arquivos/saida.txt";

    public static void salvarSaida(DadosHidrometro dadosHidrometro) {
        double consumoAgua = dadosHidrometro.getBase() + (dadosHidrometro.getAgua() * dadosHidrometro.getVazao() * dadosHidrometro.getPressao());
        double consumoAr = dadosHidrometro.getAr() * (dadosHidrometro.getVazao() * dadosHidrometro.getPesoAr());
        double perdas = dadosHidrometro.getAusencia() * dadosHidrometro.getDescontoAusencia();

        double totalLitros = consumoAgua + consumoAr - perdas;
        double contaDagua = dadosHidrometro.getAgua() * dadosHidrometro.getValorPorAgua();

        try (PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            pw.println("----- Relatório Hidrômetro -----");
            pw.println("Água detectada (A): " + dadosHidrometro.getAgua());
            pw.println("Ar detectado (B): " + dadosHidrometro.getAr());
            pw.println("Ausência (C): " + dadosHidrometro.getAusencia());
            pw.printf("Consumo de Água (ajustado): %.2f litros%n", consumoAgua);
            pw.printf("Interferência do Ar: %.2f litros%n", consumoAr);
            pw.printf("Perdas por ausência: %.2f litros%n", perdas);
            pw.printf("TOTAL FINAL: %.2f litros%n", totalLitros);
            pw.printf("VALOR DA CONTA: R$ %.2f%n", contaDagua);
        } catch (IOException e) {
            System.out.println("Erro ao salvar saída: " + e.getMessage());
        }
    }
}
