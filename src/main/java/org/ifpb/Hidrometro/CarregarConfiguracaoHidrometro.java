package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CarregarConfiguracaoHidrometro {
    private static final String CONFIG_FILE = "src/main/java/org/ifpb/Arquivos/config.txt";
    private static final double SEGUNDOS_PARA_MILISEGUNDOS = 1000;

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
            dadosHidrometro.setTaxaAtualizacaoPainelEmSegundos(Double.parseDouble(props.getProperty("taxaAtualizacaoPainelEmSegundos", "0")) * SEGUNDOS_PARA_MILISEGUNDOS);
            dadosHidrometro.setVelocidadeDeLeituraFluxoDeAguaEmSegundos(Double.parseDouble(props.getProperty("velocidadeDeLeituraFluxoDeAguaEmSegundos", "0")) * SEGUNDOS_PARA_MILISEGUNDOS);
        } catch (IOException e) {
            System.out.println("Erro ao carregar configuração. Usando valores padrão.");
        }
    }
}
