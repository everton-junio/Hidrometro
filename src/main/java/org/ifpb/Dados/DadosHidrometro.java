package org.ifpb.Dados;

public class DadosHidrometro {

    private int agua = 0;
    private int ar = 0;
    private int ausencia = 0;
    private int posicaoProcessada = 0;
    private double vazao = 0;
    private double pressao = 0;
    private double base = 0;
    private double valorPorAgua = 0;
    private double pesoAr = 0;
    private double descontoAusencia = 0;

    public int getAr() {
        return ar;
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
