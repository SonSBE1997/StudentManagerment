/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

/**
 *
 * @author SBE
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class About extends JFrame {

    /**
     *
     */
    private ImageIcon image;
    private JLabel icon;
    private JTextArea txa;
    private GridBagLayout gb;
    private GridBagConstraints gbc;

    public About() {
        super();
        init();
    }

    private void init() {
        this.setTitle("About me");
        this.setSize(new Dimension(350, 350));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(500, 50);
        this.setResizable(false);

        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        JPanel panel = createPanel();
        this.setContentPane(panel);
        this.setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        JPanel panelImage = new JPanel();
        JPanel panelText = new JPanel();

        panel.setLayout(gb);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        image = new ImageIcon("logo.png");
        icon = new JLabel(image);

        txa = new JTextArea();
        txa.setBorder(new EtchedBorder());
        txa.setEditable(false);
        txa.setTabSize(4);
        txa.setFont(new Font("Arial", Font.BOLD, 13));
        txa.setText("Chương trình được thực hiện bởi:\n"
                + "   Giáo viên hướng dẫn:\n\tTS Nguyễn Trọng Phúc\n"
                + "   Sinh viên thực hiện:\n\tVương Sỹ Sơn");

        //add Image and title to Panel Image
        panelImage.setLayout(new GridLayout());
        panelImage.add(icon);
        //add TextArea to Panel Text
        panelText.setLayout(new GridLayout());
        panelText.add(txa);

        //add Panel Image and Panel Text to panel
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.ipadx = 50;
        gbc.ipady = 20;
        gb.setConstraints(panelImage, gbc);
        panel.add(panelImage);

        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.ipadx = 50;
        gbc.ipady = 20;

        gb.setConstraints(panelText, gbc);
        panel.add(panelText);
        return panel;
    }

}
