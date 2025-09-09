package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

public class DisplayHidrometro {

    public static void mostrarHidrometro(DadosHidrometro dadosHidrometro) {
        double consumoAgua = dadosHidrometro.getBase() + (dadosHidrometro.getAgua() * dadosHidrometro.getVazao() * dadosHidrometro.getPressao());
        double consumoAr = dadosHidrometro.getAr() * (dadosHidrometro.getVazao() * dadosHidrometro.getPesoAr());
        double perdas = dadosHidrometro.getAusencia() * dadosHidrometro.getDescontoAusencia();
        double total = consumoAgua + consumoAr - perdas;
        double contaDagua = dadosHidrometro.getAgua() * dadosHidrometro.getValorPorAgua();

        System.out.println("\n----- HIDRÔMETRO -----");
        System.out.println("Água detectada (A): " + dadosHidrometro.getAgua());
        System.out.println("Ar detectado (B): " + dadosHidrometro.getAr());
        System.out.println("Ausência (C): " + dadosHidrometro.getAusencia());
        System.out.printf("TOTAL FINAL: %.2f litros%n", total);
        System.out.printf("VALOR DA CONTA: R$ %.2f%n", contaDagua);
    }
}
