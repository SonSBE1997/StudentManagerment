/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import dulieu.ThiSinh;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import xuli.DanhSachTinhThanh;

/**
 *
 * @author SBE
 */
public class PanelListStudent extends JPanel {

    //Component
    private JTable table;
    private DefaultTableModel tableModel;
    private DanhSachTinhThanh dstt;
    private MyFrame myFrame;

    public MyFrame getMyFrame() {
        return myFrame;
    }

    public void setMyFrame(MyFrame myFrame) {
        this.myFrame = myFrame;
    }

    //Constructor
    public PanelListStudent() {
        super();
        init();
    }

    //Initialization
    private void init() {
        dstt = new DanhSachTinhThanh();
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Danh sách thí sinh",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        this.setLayout(new BorderLayout());

        table = taoBang();
        this.themSuKien();
        JScrollPane scroll = new JScrollPane(table);
        this.add(scroll);
    }

    private JTable taoBang() {
        JTable tb = new JTable();

        this.setTableModel();
        tb.setModel(tableModel);
        tb.setRowHeight(20);
        tb.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
        tb.getTableHeader().setResizingAllowed(false);
        tb.getTableHeader().setReorderingAllowed(false);

        tb.setRowMargin(1);
        tb.setFont(new Font("Arial", 0, 12));
        tb.getColumnModel().getColumn(0).setPreferredWidth(10);
        tb.getColumnModel().getColumn(1).setPreferredWidth(50);
        tb.getColumnModel().getColumn(2).setPreferredWidth(80);
        tb.getColumnModel().getColumn(3).setPreferredWidth(80);
        tb.getColumnModel().getColumn(4).setPreferredWidth(70);
        tb.getColumnModel().getColumn(5).setPreferredWidth(20);
        tb.getColumnModel().getColumn(6).setPreferredWidth(30);
        tb.getColumnModel().getColumn(7).setPreferredWidth(30);
        tb.getColumnModel().getColumn(8).setPreferredWidth(30);

        tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Chỉ được chọn 1 hàng duy nhất
        tb.setSelectionBackground(Color.magenta);
        tb.setAutoCreateRowSorter(false);    //Tự động sắp xếp khi click đúp
        tb.setColumnSelectionAllowed(false);//Không cho phép chọn theo cột

        return tb;
    }

    private void setTableModel() {
        String[] colsName = {"STT", "Mã thí sinh", "Họ và tên", "Quê quán",
            "Ngày sinh", "Giới tính", "Điểm toán", "Điểm lí", "Điểm hoá"};

        tableModel = new DefaultTableModel(new Object[][]{}, colsName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    public void themSuKien() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                myFrame.getPanel().hienThiThiSinh();
            }
        });

        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); //To change body of generated methods, choose Tools | Templates.
                myFrame.getPanel().hienThiThiSinh();
            }

        });
    }

    public void hienThiBang(List<ThiSinh> list) {
        tableModel.setRowCount(0);
        int stt = 1;
        for (ThiSinh ts : list) {
            String gt;
            if (ts.getGioiTinh()) {
                gt = "Nam";
            } else {
                gt = "Nữ";
            }
            String que = dstt.timTinhThanhTheoMa(ts.getMaQueQuan()).getTenTinh();
            String ns = ts.getNgaySinh().formatDate();
            Object ob[] = new Object[]{stt, ts.getMaThiSinh(), ts.getTenThiSinh(),
                que, ns, gt, ts.getDiemToan(), ts.getDiemLy(), ts.getDiemHoa()};
            tableModel.addRow(ob);
            stt++;
        }
    }

    public String[] layDuLieu() {
        String[] dl = new String[8];
        int hang = this.hangDuocChon();
        if (hang > 0) {
            dl[0] = String.valueOf(table.getValueAt(hang, 1));
            dl[1] = (String) table.getValueAt(hang, 2);
            dl[2] = (String) table.getValueAt(hang, 3);
            dl[3] = (String) table.getValueAt(hang, 4);
            dl[4] = (String) table.getValueAt(hang, 5);
            dl[5] = String.valueOf(table.getValueAt(hang, 6));
            dl[6] = String.valueOf(table.getValueAt(hang, 7));
            dl[7] = String.valueOf(table.getValueAt(hang, 8));
        }
        return dl;
    }

    public int hangDuocChon() {
        int hang = table.getSelectedRow();
        return hang;
    }

    public String layMaTSDuocChon() {
        String maTS = "";
        int hang = this.hangDuocChon();
        if (hang > 0) {
            maTS = String.valueOf(table.getValueAt(hang, 1));
        }
        return maTS;
    }

    public boolean themThiSinhVaoBang(Object ob[]) {
        tableModel.addRow(ob);
        return true;
    }

    public boolean xoaThiSinhTrongBang(int hang) {
        tableModel.removeRow(hang);
        return true;
    }

    public boolean suaThiSinhTrongBang(int hang, Object[] ob) {
        tableModel.removeRow(hang);
        tableModel.insertRow(hang, ob);
        return true;
    }

    public int laySTT() {
        return tableModel.getRowCount();
    }

    public int getRowCount() {
        return tableModel.getRowCount();
    }
}
