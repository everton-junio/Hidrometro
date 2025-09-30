package org.ifpb.Hidrometro;

import org.ifpb.Dados.DadosHidrometro;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage; // Import necessário
import java.io.File;
import java.io.IOException;

public class DisplayHidrometro extends JPanel {

    private final DadosHidrometro dadosHidrometro;
    private Image backgroundImage;

    // --- O CONSTRUTOR E O PAINTCOMPONENT CONTINUAM IGUAIS ---
    public DisplayHidrometro(DadosHidrometro dadosHidrometro) {
        // ... (código original sem alterações)
        this.dadosHidrometro = dadosHidrometro;
        new Timer((int) dadosHidrometro.getTaxaAtualizacaoPainelEmSegundos(), e -> repaint()).start();
        try {
            backgroundImage = ImageIO.read(new File("src/main/java/org/ifpb/Arquivos/ImagemHidrometro.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // ... (código original sem alterações)
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
        g.drawString(leitura, 160, 140);

        g.setFont(new Font("Monospaced", Font.BOLD, 10));
        g.drawString(String.valueOf(dadosHidrometro.getPressao()), 142, 139);
        g.drawString(String.valueOf(dadosHidrometro.getVazao()), 142, 151);
    }

    // --- O MÉTODO DE MOSTRAR DISPLAY CONTINUA IGUAL ---
    public static void mostrarDisplay(DadosHidrometro dadosHidrometro) {
        // ... (código original sem alterações)
        JFrame frame = new JFrame("Hidrômetro");
        DisplayHidrometro painel = new DisplayHidrometro(dadosHidrometro);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(painel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    // ********************************************************************
    // ## MUDANÇA: ADICIONEI ESTE NOVO MÉTODO ESTÁTICO ##
    // Ele gera e salva a imagem do estado final de um hidrômetro.
    // ********************************************************************
    public static void gerarImagemFinal(DadosHidrometro dadosHidrometro, String caminhoImagemSaida) {
        try {
            // 1. Carrega a imagem de fundo
            BufferedImage backgroundImage = ImageIO.read(new File("src/main/java/org/ifpb/Arquivos/ImagemHidrometro.png"));

            // 2. Pega o "pincel" (Graphics2D) para desenhar na imagem
            Graphics2D g = backgroundImage.createGraphics();

            // 3. Replica a lógica de desenho do método paintComponent
            g.setColor(Color.BLACK);
            g.setFont(new Font("Monospaced", Font.BOLD, 24));
            String leitura = String.format("%05d", dadosHidrometro.getAgua());
            g.drawString(leitura, 160, 140); // As coordenadas (X, Y) podem precisar de ajuste

            g.setFont(new Font("Monospaced", Font.BOLD, 10));
            g.drawString(String.valueOf(dadosHidrometro.getPressao()), 147, 160);
            g.drawString(String.valueOf(dadosHidrometro.getVazao()), 147, 170);

            // 4. Libera os recursos do "pincel"
            g.dispose();

            // 5. Salva a imagem modificada em um novo arquivo PNG
            ImageIO.write(backgroundImage, "png", new File(caminhoImagemSaida));
            System.out.println("Imagem salva em: " + caminhoImagemSaida);

        } catch (IOException e) {
            System.err.println("Erro ao gerar a imagem de saída: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
//modificado por ilana.costa