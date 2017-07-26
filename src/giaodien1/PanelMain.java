/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import dulieu.ThiSinh;
import java.awt.GridBagConstraints;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import xuli.DanhSachThiSinh;
import xuli.MyException;

/**
 *
 * @author SBE
 */
public class PanelMain extends JPanel {

    // Components
    private PanelFilter panelFilter;
    private PanelListStudent panelListStudent;
    private PanelStudentInfo panelStudentInfo;
    private PanelAction panelAction;
    private XuLyDuLieuTrenForm xuLy;
    public MyFrame myFrame;

    private DanhSachThiSinh dsts;
    private List<ThiSinh> list;

    //Getter
    public DanhSachThiSinh getDsts() {
        return dsts;
    }

    public List<ThiSinh> getList() {
        return list;
    }

    public void setList(List<ThiSinh> list) {
        this.list = list;
    }

    // Constructor
    public PanelMain(MyFrame myFrame) {
        super();
        this.myFrame = myFrame;
        init();
    }

    // Initialization
    private void init() {
        this.setLayout(TaoThanhPhan.gridBagLayout);
        TaoThanhPhan.gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;
        dsts = new DanhSachThiSinh();
        list = dsts.getListTS();
        panelFilter = new PanelFilter(myFrame);
        panelAction = new PanelAction(myFrame);
        xuLy = new XuLyDuLieuTrenForm(dsts);
//        xuLy.setMyFrame(myFrame);
        panelListStudent = xuLy.getPanelListStudent();
        panelListStudent.setMyFrame(myFrame);
        panelStudentInfo = xuLy.getPanelStudentInfo();

        hienThiBang();
        TaoThanhPhan.themComponent(this, panelFilter, 0, 0, 380, 30);
        TaoThanhPhan.themComponent(this, panelListStudent, 0, 1, 850, 120);
        TaoThanhPhan.themComponent(this, panelStudentInfo, 0, 2, 100, 10);
        TaoThanhPhan.themComponent(this, panelAction, 0, 3, 0, 10);
    }

    public void hienThiBang() {
        panelListStudent.hienThiBang(list);
    }

    public void hienThiThiSinh() {
        String[] dl = panelListStudent.layDuLieu();
        if (panelListStudent.hangDuocChon() > 0) {
            panelStudentInfo.hienThiThiSinh(dl);
        }
    }

    public boolean locThiSinh() {
        try {
            List<ThiSinh> ketQuaLoc = panelFilter.xulyLoc();
            panelListStudent.hienThiBang(ketQuaLoc);
        } catch (MyException ex) {
            hienNut();
            JOptionPane.showMessageDialog(null, "Bạn phải lọc theo tiêu chí nào đó!");
            return false;
        }
        return true;
    }

    public boolean demThiSinh() {
        List<ThiSinh> ketQuaLoc = panelFilter.xuLyDem();
        panelListStudent.hienThiBang(ketQuaLoc);
        return true;
    }

    public boolean xoaThiSinh() {
        int hang = panelListStudent.hangDuocChon();
        if (hang < 0) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng nào để xoá");
            return false;
        } else {
            int id = Integer.parseInt(panelListStudent.layMaTSDuocChon());
            int xoa = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thí sinh có mã  thí sinh là " + id,
                    "Delete", JOptionPane.YES_NO_OPTION);
            if (xoa == JOptionPane.YES_OPTION) {
                dsts.xoaThiSinh(id);
                datMacDinh();
                panelListStudent.xoaThiSinhTrongBang(hang);
                JOptionPane.showMessageDialog(null, "Xóa thành công!");
            }
        }
        return true;
    }

    public void datMacDinh() {
        panelFilter.datMacDinh();
        panelStudentInfo.datMacDinh();
    }

    public void anNut() {
        panelFilter.anNut();
        panelAction.anNut();
    }

    public void hienNut() {
        panelFilter.hienNut();
        panelAction.hienNut();
    }

    public void khongChoNhapTS() {
        panelStudentInfo.khoaONhap();
    }

    public void choPhepNhapLoc() {
        panelFilter.moONhap();
    }

    public int soThiSinhTrenBang() {
        return panelListStudent.getRowCount();
    }

    public boolean themThiSinh() {

        return xuLy.themThiSinh();
    }

    public boolean xuLyThem() {
        if (panelListStudent.hangDuocChon() > 0) {
            datMacDinh();
        }
        PanelAction.CHECK = 1;
        anNut();
        panelStudentInfo.moONhap();
        panelFilter.khoaONhap();
        return true;
    }

    public int suaThiSinh() {
        int kt = xuLy.suaThiSinh();
        return kt;
    }

    public boolean xuLySua() {
        if (panelListStudent.hangDuocChon() < 0) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng nào để sửa");
            hienNut();
            khongChoNhapTS();
            return false;
        } else {
            PanelAction.CHECK = 2;
            anNut();
            panelStudentInfo.moONhap();
            panelFilter.khoaONhap();
        }
        return true;
    }
}
