/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author SBE
 */
public class MenuBar extends JMenuBar implements ActionListener {

    //Components
    private JMenu mnFile;
    private JMenu mnAbout;
    private JMenuItem itemNew;
    private JMenuItem itemOpen;
    private JMenuItem itemExit;
    private JMenuItem itemAboutMe;
    private MyFrame myFrame;

    //Constructor
    public MenuBar(MyFrame myFrame) {
        super();
        this.myFrame = myFrame;
        initMenuBar();
    }

    private void initMenuBar() {
        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        mnFile = new JMenu("File");
        mnAbout = new JMenu("About");

        itemNew = new JMenuItem("New");
        itemOpen = new JMenuItem("Open");
        itemExit = new JMenuItem("Exit");
        itemAboutMe = new JMenuItem("About me");

//        itemNew.setMnemonic(KeyEvent.CTRL_MASK + KeyEvent.VK_N);
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        mnFile.add(itemNew);
        mnFile.add(itemOpen);
        mnFile.addSeparator();
        mnFile.add(itemExit);

        mnAbout.add(itemAboutMe);

        this.add(mnFile);
        this.add(mnAbout);

        itemNew.addActionListener(this);
        itemOpen.addActionListener(this);
        itemExit.addActionListener(this);
        itemAboutMe.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {
            MyFrame myFrame = new MyFrame();
            myFrame.setVisible(true);
        }
        if (e.getActionCommand().equals("Open")) {
            myFrame.getPanel().getDsts().docDuLieu();
            myFrame.getPanel().setList(myFrame.getPanel().getDsts().getListTS());
            myFrame.getPanel().hienThiBang();
        }
        if (e.getActionCommand().equals("Exit")) {
            int ques = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thoát không?", "Exit", JOptionPane.YES_NO_OPTION);
            if (ques == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        if (e.getActionCommand().equals("About me")) {
            new About().setVisible(true);
        }
    }
}
