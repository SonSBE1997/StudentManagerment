/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;

import dulieu.ThiSinh;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import xuli.DanhSachThiSinh;
import xuli.DanhSachTinhThanh;

/**
 *
 * @author SBE
 */
public class Sort extends JFrame implements ActionListener {

    /**
     * Attribute
     */
    private DanhSachThiSinh dsts;
    private DanhSachTinhThanh dstt;
    private List<ThiSinh> list;

    /**
     * Components
     */
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraint;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> cbTieuChi, cbChieu;
    private JButton btSort;

    /**
     * Constructor
     */
    public Sort() {
        super();
        init();
    }

    /**
     * Initialization
     */
    private void init() {
        this.setTitle("Sắp xếp thí sinh");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900, 400);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        gridBagLayout = new GridBagLayout();
        gridBagConstraint = new GridBagConstraints();
        dsts = new DanhSachThiSinh();
        dstt = dsts.getDstt();
        list = dsts.getListTS();

        JPanel panel = panelMain();
        //Add Components
        this.add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Sort().setVisible(true);
    }

    /**
     * Method
     */
    private JPanel panelMain() {
        JPanel panel = new JPanel();
        panel.setLayout(gridBagLayout);
        gridBagConstraint.insets = new Insets(0, 10, 0, 10);
        gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;

        JPanel pnlTable = panelTable();
        JPanel pnlSort = panelSort();
        addComponent(panel, pnlTable, 0, 0, 850, 200);
        addComponent(panel, pnlSort, 0, 1, 100, 20);
        return panel;
    }

    private JPanel panelTable() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Danh sách thí sinh",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        panel.setLayout(new BorderLayout());

        table = createJTable();
        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll);
        showTable(list);
        return panel;
    }

    private JPanel panelSort() {
        JPanel panel = new JPanel();
        panel.setLayout(gridBagLayout);
        gridBagConstraint.insets = new Insets(5, 40, 5, 40);
        gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;

        String[] tc = {"Mã thí sinh", "Tổng điểm"};
        cbTieuChi = createComboBox(tc);
        String[] chieu = {"Tăng dần", "Giảm dần"};
        cbChieu = createComboBox(chieu);

        btSort = createButton("Sắp xếp");

        addComponent(panel, cbTieuChi, 0, 0, 50, 20);
        addComponent(panel, cbChieu, 1, 0, 50, 20);
        addComponent(panel, btSort, 2, 0, 50, 20);
        return panel;
    }

    public void addComponent(Container ct, Component c, int col, int row, int nCol, int nRow) {
        gridBagConstraint.gridx = col;
        gridBagConstraint.gridy = row;

        gridBagConstraint.ipadx = nCol;
        gridBagConstraint.ipady = nRow;
        gridBagLayout.setConstraints(c, gridBagConstraint);
        ct.add(c);
    }

    private JTable createJTable() {
        JTable tb = new JTable();
        String[] colsName = {"STT", "Mã thí sinh", "Họ và tên", "Quê quán",
            "Ngày sinh", "Giới tính", "Điểm toán", "Điểm lí", "Điểm hoá", "Tổng điểm"};

        tableModel = new DefaultTableModel(new Object[][]{}, colsName) {
            Class[] types = new Class[]{
                Integer.class, Integer.class, String.class, String.class,
                String.class, String.class, Float.class, Float.class, Float.class, String.class};

            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tb.setModel(tableModel);
        tb.setRowHeight(20);
        tb.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
        tb.getTableHeader().setResizingAllowed(false);
        tb.getTableHeader().setReorderingAllowed(false);

        tb.setRowMargin(1);
        tb.setFont(new Font("Arial", 0, 12));
        tb.getColumnModel().getColumn(0).setPreferredWidth(5);
        tb.getColumnModel().getColumn(1).setPreferredWidth(40);
        tb.getColumnModel().getColumn(2).setPreferredWidth(80);
        tb.getColumnModel().getColumn(3).setPreferredWidth(50);
        tb.getColumnModel().getColumn(4).setPreferredWidth(50);
        tb.getColumnModel().getColumn(5).setPreferredWidth(20);
        tb.getColumnModel().getColumn(6).setPreferredWidth(30);
        tb.getColumnModel().getColumn(7).setPreferredWidth(30);
        tb.getColumnModel().getColumn(8).setPreferredWidth(30);
        tb.getColumnModel().getColumn(9).setPreferredWidth(30);

        tb.setColumnSelectionAllowed(false);//Không cho phép chọn theo cột
        tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Chỉ được chọn 1 hàng duy nhất
        return tb;
    }

    private void showTable(List<ThiSinh> list) {
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
            String ns = ts.getNgaySinh().toString();
            float toan = ts.getDiemToan();
            float ly = ts.getDiemLy();
            float hoa = ts.getDiemHoa();
            DecimalFormat formatter = new DecimalFormat("#.#");
            float tongDiem = toan + ly + hoa;
            Object ob[] = new Object[]{stt, ts.getMaThiSinh(), ts.getTenThiSinh(),
                que, ns, gt, toan, ly, hoa, formatter.format(tongDiem)};
            tableModel.addRow(ob);
            stt++;
        }
    }

    private JComboBox<String> createComboBox(String[] model) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(80, 20));
        comboBox.setFont(new Font("Arial", 1, 13));

        comboBox.setModel(new DefaultComboBoxModel<>(model));
        comboBox.setSelectedIndex(0);
        return comboBox;
    }

    public JButton createButton(String title) {
        JButton button = new JButton(title);
        button.setSize(new Dimension(80, 20));
        button.setFont(new Font("Arial", 1, 13));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Sắp xếp")) {
            List<ThiSinh> li;
            String tieuChiSX = (String) cbTieuChi.getSelectedItem();
            String chieuSX = (String) cbChieu.getSelectedItem();
            if (tieuChiSX.toLowerCase().equals("mã thí sinh")) {
                li = dsts.sapXepGiamTheoMaThiSinh();
                if (chieuSX.toLowerCase().equals("giảm dần")) {
                    showTable(li);
                } else {
                    Collections.reverse(li);
                    showTable(li);
                }
            } else {
                li = dsts.sapXepGiamTheoTongDiem();
                if (chieuSX.toLowerCase().equals("giảm dần")) {
                    showTable(li);
                } else {
                    Collections.reverse(li);
                    showTable(li);
                }
            }
        }
    }
}
