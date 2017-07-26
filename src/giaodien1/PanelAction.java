/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author SBE
 */
public class PanelAction extends JPanel {

    // Components
    private JButton btSapXep, btThem, btSua, btXoa, btOK, btHuy;
    public static int CHECK = 0;
    private MyFrame myFrame;

    //Getter and setter
    // Constructor
    public PanelAction(MyFrame myFrame) {
        super();
        this.myFrame = myFrame;
        init();
    }

    // Initialization
    private void init() {
        this.setLayout(TaoThanhPhan.gridBagLayout);
        this.setBorder(new EtchedBorder());
        TaoThanhPhan.gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;
        TaoThanhPhan.gridBagConstraint.insets = new Insets(0, 5, 0, 5);

        btSapXep = TaoThanhPhan.taoNut("Sắp xếp", "Sort");
        btThem = TaoThanhPhan.taoNut("Thêm", "Add2");
        btSua = TaoThanhPhan.taoNut("Sửa", "Edit1");
        btXoa = TaoThanhPhan.taoNut("Xoá", "Delete");
        btOK = TaoThanhPhan.taoNut("OK", "OK");
        btHuy = TaoThanhPhan.taoNut("Huỷ", "Cancel");
        this.themSuKien();

        TaoThanhPhan.themComponent(this, btSapXep, 0, 0, 30, 10);
        TaoThanhPhan.themComponent(this, btThem, 1, 0, 30, 10);
        TaoThanhPhan.themComponent(this, btSua, 2, 0, 30, 10);
        TaoThanhPhan.themComponent(this, btXoa, 3, 0, 30, 10);
        TaoThanhPhan.themComponent(this, btOK, 4, 0, 30, 10);
        TaoThanhPhan.themComponent(this, btHuy, 5, 0, 30, 10);
    }

    private void themSuKien() {
        btSapXep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.setVisible(false);
                Sort sort = new Sort(myFrame);
                if (myFrame.getPanel().soThiSinhTrenBang() != 0) {
                    sort.getDsts().docDuLieu();
                    sort.showTable(sort.getDsts().getListTS());
                }
                sort.setVisible(true);
            }
        });
        btThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.getPanel().xuLyThem();
            }
        });
        btSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.getPanel().xuLySua();
            }
        });
        btXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.getPanel().xoaThiSinh();
            }
        });
        btOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyOK();
            }
        });
        btHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xulyHuy();
            }
        });
    }

    public void anNut() {
        btSapXep.setEnabled(false);
        btThem.setEnabled(false);
        btSua.setEnabled(false);
        btXoa.setEnabled(false);
    }

    public void hienNut() {
        btSapXep.setEnabled(true);
        btThem.setEnabled(true);
        btSua.setEnabled(true);
        btXoa.setEnabled(true);
        btOK.setEnabled(true);
    }

    private void xuLyOK() {
        if (CHECK == 0) {
            return;
        }
        if (CHECK == 1) {
            CHECK = 0;
            if (myFrame.getPanel().themThiSinh()) {
                thongBao("Thêm thành công");
            } else {
                thongBao("Thêm thất bại");
            }
        }
        if (CHECK == 2) {
            CHECK = 0;
            int kt = myFrame.getPanel().suaThiSinh();
            switch (kt) {
                case 1:
                    thongBao("Sửa thành công");
                    break;
                case 0:
                    thongBao("Sửa thất bại");
                    break;
                default:
                    thongBao("Bạn chưa sửa gì");
                    break;
            }
        }
        myFrame.getPanel().hienNut();
        myFrame.getPanel().khongChoNhapTS();
        myFrame.getPanel().choPhepNhapLoc();
    }

    private void thongBao(String noiDung) {
        JOptionPane.showMessageDialog(null, noiDung, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void xulyHuy() {
        CHECK = 0;
        myFrame.getPanel().hienNut();
        myFrame.getPanel().khongChoNhapTS();
        myFrame.getPanel().choPhepNhapLoc();
        myFrame.getPanel().hienThiBang();
        myFrame.getPanel().datMacDinh();
    }
}
