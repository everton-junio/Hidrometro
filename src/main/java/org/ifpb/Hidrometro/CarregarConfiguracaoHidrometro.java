package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CarregarConfiguracaoHidrometro {
    private static final double SEGUNDOS_PARA_MILISEGUNDOS = 1000;

    // MUDANÇA: O método agora recebe o caminho do arquivo de configuração como parâmetro.
    public static void carregarConfiguracao(DadosHidrometro dadosHidrometro, String configFile) {
        Properties props = new Properties();
        // MUDANÇA: Usa o parâmetro 'configFile' em vez da constante fixa.
        try (FileReader fr = new FileReader(configFile)) {
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
            System.out.println("Erro ao carregar " + configFile + ". Usando valores padrão.");
        }
    }
}
// modificado por Ilana.costa