package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import javax.swing.*;
import java.awt.*;

public class DisplayHidrometro extends JPanel {

    private final DadosHidrometro dados;

    public DisplayHidrometro(DadosHidrometro dados) {
        this.dados = dados;
        new Timer(dados.getTaxaAtualizacaoPainelEmSegundos(), e -> repaint()).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fundo
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Corpo do hidrômetro
        g.setColor(Color.GRAY);
        g.fillRect(50, 50, 300, 150);

        // Vidro do hidrômetro
        g.setColor(Color.CYAN);
        g.fillRect(60, 60, 280, 130);

        // Números do hidrômetro
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 24));

        String leitura = String.format("%05d", (int) dados.getAgua());
        g.drawString(leitura, 150, 130);

        // Ar e ausência menores
        g.setFont(new Font("Monospaced", Font.PLAIN, 16));
        g.drawString("Ar (B): " + dados.getAr(), 150, 160);
        g.drawString("Ausência (C): " + dados.getAusencia(), 150, 180);
        g.drawString("Vazao (C): " + dados.getVazao(), 150, 200);
    }

    public static void mostrar(DadosHidrometro dados) {
        JFrame frame = new JFrame("Hidrômetro");
        DisplayHidrometro painel = new DisplayHidrometro(dados);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(painel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
