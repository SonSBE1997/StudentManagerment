/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author SBE
 */
public class TaoThanhPhan {

    public static GridBagLayout gridBagLayout = new GridBagLayout();
    public static GridBagConstraints gridBagConstraint = new GridBagConstraints();

    public static void themComponent(Container ct, Component c, int col, int row, int width, int height) {
        gridBagConstraint.gridx = col;
        gridBagConstraint.gridy = row;

        gridBagConstraint.ipadx = width;
        gridBagConstraint.ipady = height;
        gridBagLayout.setConstraints(c, gridBagConstraint);
        ct.add(c);
    }

    public static JTextField taoTextField() {
        JTextField textField = new JTextField("", 13);
        textField.setSize(new Dimension(70, 20));
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        return textField;
    }

    public static JButton taoNut(String title, String linkIcon) {
        JButton button = new JButton(title);
        button.setSize(new Dimension(70, 20));
        button.setIcon(new ImageIcon("icon\\" + linkIcon + ".png"));
//        button.addActionListener(this);
        return button;
    }

    public static JLabel taoNhan(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setPreferredSize(new Dimension(70, 20));
        return label;
    }
}
