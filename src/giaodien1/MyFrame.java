/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author SBE
 */
public class MyFrame extends JFrame {

    /**
     * Components
     */
    private PanelMain panel;
    private MenuBar menu;

    //Getter
    public PanelMain getPanel() {
        return panel;
    }

    public MenuBar getMenu() {
        return menu;
    }

    //Constructor
    public MyFrame() {
        super();
        init();
    }

    //  Initialization
    private void init() {
        this.setTitle("Quản lý thí sinh");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900, 700);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        menu = new MenuBar(this);
        panel = new PanelMain(this);
        this.setJMenuBar(menu);
        this.setContentPane(panel);
    }
}
