/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import dulieu.ThiSinh;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import xuli.MyException;

/**
 *
 * @author SBE
 */
public class PanelFilter extends JPanel {

    /**
     * Components
     */
    private JTextField tfNoiSinh, tfMaTS;
    private JButton btLoc;
    private MyFrame myFrame;
    private JTextField txtDiemDo, txtSL;
    JButton button;

    //Constructor
    public PanelFilter(MyFrame myFrame) {
        super();
        this.myFrame = myFrame;
        init();
    }

    /**
     * Initialization
     */
    private void init() {
        this.setLayout(TaoThanhPhan.gridBagLayout);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lọc thí sinh",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        TaoThanhPhan.gridBagConstraint.insets = new Insets(0, 10, 0, 10);
        TaoThanhPhan.gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;
        
        tfNoiSinh = TaoThanhPhan.taoTextField();
        tfMaTS = TaoThanhPhan.taoTextField();
        btLoc = TaoThanhPhan.taoNut("Lọc", "Filter3");
        
        JLabel lblDiemDo = new JLabel("Điểm đỗ:");
        txtDiemDo = TaoThanhPhan.taoTextField();
        txtDiemDo.setSize(new Dimension(50, 20));
        JLabel lblSL = new JLabel("SL");
        txtSL = TaoThanhPhan.taoTextField();
        txtSL.setSize(new Dimension(50, 20));
        
        button = new JButton("OK");
        this.themSuKien();
        
        JLabel lbNoiSinh = TaoThanhPhan.taoNhan("Nơi sinh");
        JLabel lbMaTS = TaoThanhPhan.taoNhan("Mã thí sinh");
        TaoThanhPhan.themComponent(this, lbNoiSinh, 0, 0, 30, 10);
        TaoThanhPhan.themComponent(this, tfNoiSinh, 1, 0, 50, 15);
        TaoThanhPhan.themComponent(this, lbMaTS, 2, 0, 30, 10);
        TaoThanhPhan.themComponent(this, tfMaTS, 3, 0, 50, 15);
        TaoThanhPhan.themComponent(this, btLoc, 4, 0, 30, 4);
        
        TaoThanhPhan.themComponent(this, lblDiemDo, 0, 1, 30, 10);
        TaoThanhPhan.themComponent(this, txtDiemDo, 1, 1, 50, 15);
        TaoThanhPhan.themComponent(this, lblSL, 2, 1, 30, 10);
        TaoThanhPhan.themComponent(this, txtSL, 3, 1, 50, 15);
        TaoThanhPhan.themComponent(this, button, 4, 1, 30, 4);
    }
    
    public List<ThiSinh> xulyLoc() throws MyException {
        List<ThiSinh> li = new ArrayList<>();
        if (tfNoiSinh.getText().trim().equals("") == true) {
            if (tfMaTS.getText().trim().equals("") == true) {
                throw new MyException();
            } else {
                li = myFrame.getPanel().getDsts().locTheoMaThiSinh(tfMaTS.getText());
            }
        } else {
            
            if (tfMaTS.getText().trim().equals("") == true) {
                li = myFrame.getPanel().getDsts().locTheoQueQuan(tfNoiSinh.getText());
            } else {
                li = myFrame.getPanel().getDsts().locTheoMaThiSinhVaQueQuan(tfMaTS.getText(), tfNoiSinh.getText());
            }
        }
        return li;
    }
    
    public List<ThiSinh> xuLyDem() {
        List<ThiSinh> li = new ArrayList<>();
        if (txtDiemDo.getText().trim().equals("")) {
            return li;
        }
        li = myFrame.getPanel().getDsts().soThiSinh(txtDiemDo.getText().trim());
        txtSL.setText(li.size() + "");
        return li;
    }
    
    public void themSuKien() {
        btLoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.getPanel().anNut();
                hienNut();
                myFrame.getPanel().locThiSinh();
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.getPanel().demThiSinh();
            }
        });
        
    }
    
    public void anNut() {
        btLoc.setEnabled(false);
    }
    
    public void hienNut() {
        btLoc.setEnabled(true);
    }
    
    public void datMacDinh() {
        tfNoiSinh.setText("");
        tfMaTS.setText("");
    }
    
    public void khoaONhap() {
        tfNoiSinh.setEditable(false);
        tfMaTS.setEditable(false);
    }
    
    public void moONhap() {
        tfNoiSinh.setEditable(true);
        tfMaTS.setEditable(true);
    }
}
