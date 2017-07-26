/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuli;

/**
 *
 * @author SBE
 */
import dulieu.TinhThanh;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DanhSachTinhThanh {

    private List<TinhThanh> listTT;
    private Data data;

    public DanhSachTinhThanh() {
        listTT = new ArrayList<>();
        data = new Data();
        this.docDuLieu();
    }

    public DanhSachTinhThanh(List<TinhThanh> listTT) {
        this.listTT = listTT;
    }

    public List<TinhThanh> getListTT() {
        return listTT;
    }

    private void docDuLieu() {
        listTT = data.docTinhThanh();
    }

    private void ghiDuLieu() {
        data.ghiTinhThanh(listTT);
    }

    public TinhThanh timTinhThanhTheoMa(int maTinhThanh) {
        for (TinhThanh tt : listTT) {
            if (tt.getMaTinh() == maTinhThanh) {
                return tt;
            }
        }
        return null;
    }

    public TinhThanh timTinhThanhTheoTen(String tenTT) {
        for (TinhThanh tt : listTT) {
            if (tt.getTenTinh().toLowerCase().equals(tenTT.toLowerCase())) {
                return tt;
            }
        }
        return null;
    }

    public boolean themTinhThanh(int maTinhThanh, String tenTinhThanh) {
        if (timTinhThanhTheoMa(maTinhThanh) != null || timTinhThanhTheoTen(tenTinhThanh) != null) {
            JOptionPane.showMessageDialog(null, "Bạn không thể thêm tỉnh thành có cùng mã hoặc cùng tên!");
            return false;
        }
        listTT.add(new TinhThanh(maTinhThanh, tenTinhThanh));
        ghiDuLieu();
        return true;
    }

    public boolean suaMaTinhThanh(int maTinhThanh1, int maTinhThanh2) {
        if (timTinhThanhTheoMa(maTinhThanh2) != null) {
            JOptionPane.showMessageDialog(null, "Tỉnh thành đã tồn tại. Bạn không thể thêm tỉnh thành có cùng mã");
            return false;
        }
        for (TinhThanh tt : listTT) {
            if (tt.getMaTinh() == maTinhThanh1) {
                tt.setMaTinh(maTinhThanh2);
                ghiDuLieu();
                return true;
            }
        }
        return true;
    }

    public boolean suaTenTinhThanh(int maTinhThanh, String tenTT) {
        if (timTinhThanhTheoTen(tenTT) != null) {
            JOptionPane.showMessageDialog(null, "Tỉnh thành đã tồn tại. Bạn không thể thêm tỉnh thành có cùng tên");
            return false;
        }
        for (TinhThanh tt : listTT) {
            if (tt.getMaTinh() == maTinhThanh) {
                tt.setTenTinh(tenTT);
                ghiDuLieu();
                return true;
            }
        }
        return true;
    }
}
