package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfiguracaoHidrometro {
    private static final String CONFIG_FILE = "C:\\Users\\evert\\Documentos\\Facul\\PDP\\Hidrometro\\src\\main\\java\\org\\ifpb\\Arquivos\\config.txt";

    public static void carregarConfiguracao(DadosHidrometro dadosHidrometro) {
        Properties props = new Properties();
        try (FileReader fr = new FileReader(CONFIG_FILE)) {
            props.load(fr);
            dadosHidrometro.setVazao(Double.parseDouble(props.getProperty("vazao", "0")));
            dadosHidrometro.setPressao(Double.parseDouble(props.getProperty("pressao", "0")));
            dadosHidrometro.setBase(Double.parseDouble(props.getProperty("base", "0")));
            dadosHidrometro.setValorPorAgua(Double.parseDouble(props.getProperty("valorPorAgua", "0")));
            dadosHidrometro.setPesoAr(Double.parseDouble(props.getProperty("pesoAr", "0")));
            dadosHidrometro.setDescontoAusencia(Double.parseDouble(props.getProperty("descontoAusencia", "0")));
            System.out.println("Configuração carregada: vazao=" + dadosHidrometro.getVazao() +
                    ", pressao=" + dadosHidrometro.getPressao() +
                    ", base=" + dadosHidrometro.getBase() +
                    ", valorPorAgua=" + dadosHidrometro.getValorPorAgua());
        } catch (IOException e) {
            System.out.println("Erro ao carregar configuração. Usando valores padrão.");
        }
    }
}
