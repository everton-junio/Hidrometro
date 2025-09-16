package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DisplayHidrometro extends JPanel {

    private final DadosHidrometro dadosHidrometro;
    private Image backgroundImage;

    public DisplayHidrometro(DadosHidrometro dadosHidrometro) {
        this.dadosHidrometro = dadosHidrometro;
        new Timer(dadosHidrometro.getTaxaAtualizacaoPainelEmSegundos(), e -> repaint()).start();
        try {
            backgroundImage = ImageIO.read(new File("src/main/java/org/ifpb/Arquivos/ImagemHidrometro.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 24));

        String leitura = String.format("%05d", dadosHidrometro.getAgua());
        g.drawString(leitura, 160, 120);

        g.setFont(new Font("Monospaced", Font.BOLD, 10));
        g.drawString(String.valueOf(dadosHidrometro.getPressao()), 142, 139);
        g.drawString(String.valueOf(dadosHidrometro.getVazao()), 142, 151);
    }

    public static void mostrarDisplay(DadosHidrometro dadosHidrometro) {
        JFrame frame = new JFrame("Hidrômetro");
        DisplayHidrometro painel = new DisplayHidrometro(dadosHidrometro);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(painel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
