package org.ifpb.Dados;

public class DadosHidrometro {

    private int agua;
    private int ar;
    private int ausencia;
    private int posicaoProcessada;
    private double vazao;
    private double pressao ;
    private double base;
    private double valorPorAgua;
    private double pesoAr;
    private double descontoAusencia;
    private int taxaAtualizacaoPainelEmSegundos;
    private int velocidadeDeLeituraFluxoDeAguaEmSegundos;

    public int getAr() {
        return ar;
    }

    public int getTaxaAtualizacaoPainelEmSegundos() {
        return taxaAtualizacaoPainelEmSegundos;
    }

    public void setTaxaAtualizacaoPainelEmSegundos(double taxaAtualizacaoPainelEmSegundos) {
        this.taxaAtualizacaoPainelEmSegundos = (int) taxaAtualizacaoPainelEmSegundos;
    }

    public int getVelocidadeDeLeituraFluxoDeAguaEmSegundos() {
        return velocidadeDeLeituraFluxoDeAguaEmSegundos;
    }

    public void setVelocidadeDeLeituraFluxoDeAguaEmSegundos(double velocidadeDeLeituraFluxoDeAguaEmSegundos) {
        this.velocidadeDeLeituraFluxoDeAguaEmSegundos = (int) velocidadeDeLeituraFluxoDeAguaEmSegundos;
    }

    public double getPesoAr() {
        return pesoAr;
    }

    public void setPesoAr(double pesoAr) {
        this.pesoAr = pesoAr;
    }

    public double getDescontoAusencia() {
        return descontoAusencia;
    }

    public void setDescontoAusencia(double descontoAusencia) {
        this.descontoAusencia = descontoAusencia;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public int getAgua() {
        return agua;
    }

    public void setAgua(int agua) {
        this.agua = agua;
    }

    public int getAusencia() {
        return ausencia;
    }

    public void setAusencia(int ausencia) {
        this.ausencia = ausencia;
    }

    public int getPosicaoProcessada() {
        return posicaoProcessada;
    }

    public void setPosicaoProcessada(int posicaoProcessada) {
        this.posicaoProcessada = posicaoProcessada;
    }

    public double getVazao() {
        return vazao;
    }

    public void setVazao(double vazao) {
        this.vazao = vazao;
    }

    public double getPressao() {
        return pressao;
    }

    public void setPressao(double pressao) {
        this.pressao = pressao;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getValorPorAgua() {
        return valorPorAgua;
    }

    public void setValorPorAgua(double valorPorAgua) {
        this.valorPorAgua = valorPorAgua;
    }
}
