/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;

import dulieu.MyDate;
import dulieu.ThiSinh;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import xuli.DanhSachThiSinh;
import xuli.DanhSachTinhThanh;
import xuli.MyException;

/**
 *
 * @author SBE
 */
public class MyFrame extends JFrame implements ActionListener {

    /**
     * Components
     */
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraint;

    private JTextField tfNoiSinh, tfMaTS;
    private JButton btLoc;

    private JTable table;
    private static DefaultTableModel tableModel = new DefaultTableModel();

    private JTextField tfMaThiSinh, tfTenThiSinh, tfNgaySinh, tfDiemToan, tfDiemLy, tfDiemHoa, tfTongDiem;
    private JRadioButton rbtNam, rbtNu;
    private JComboBox<String> cbThanhPho;

    private JButton btSapXep, btThem, btSua, btXoa, btOK, btCancel;

    /**
     * Attribute
     */
    public static DanhSachThiSinh dsts;
    private static DanhSachTinhThanh dstt;
    private List<ThiSinh> list;
    private int check;

    /**
     * Constructor
     */
    public MyFrame() {
        super();
        init();
    }

    /**
     * Initialization
     */
    private void init() {
        this.setTitle("Quản lý thí sinh");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900, 700);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        gridBagLayout = new GridBagLayout();
        gridBagConstraint = new GridBagConstraints();
        dsts = new DanhSachThiSinh();
        dstt = dsts.getDstt();
        list = dsts.getListTS();

        MenuBar myMenuBar = new MenuBar(this);
        JPanel panel = panelMain();
        //Add Components
        this.setJMenuBar(myMenuBar);
        this.add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        new MyFrame().setVisible(true);
    }

    /**
     * Tạo các thành phần của giao diện
     */
    //Panel
    private JPanel panelMain() {
        JPanel panel = new JPanel();
        panel.setLayout(gridBagLayout);
        gridBagConstraint.insets = new Insets(0, 10, 0, 10);
        gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;

        JPanel pnlFilter = panelFilter();
        JPanel pnlListStudent = panelListStudent();
        JPanel pnlStudentInfo = panelStudentInfo();
        JPanel pnlAction = panelAction();
        themComponent(panel, pnlFilter, 0, 0, 100, 10);
        themComponent(panel, pnlListStudent, 0, 1, 100, 180);
        themComponent(panel, pnlStudentInfo, 0, 2, 100, 30);
        themComponent(panel, pnlAction, 0, 3, 100, 20);
        return panel;
    }

    private JPanel panelFilter() {
        JPanel panel = new JPanel();

        panel.setLayout(gridBagLayout);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lọc thí sinh",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        gridBagConstraint.insets = new Insets(0, 10, 0, 10);
        gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;

        tfNoiSinh = taoTextField();
        tfMaTS = taoTextField();
        btLoc = taoNut("Lọc", "Filter3");

        JLabel lbNoiSinh = taoNhan("Nơi sinh");
        JLabel lbMaTS = taoNhan("Mã thí sinh");
        themComponent(panel, lbNoiSinh, 0, 0, 30, 10);
        themComponent(panel, tfNoiSinh, 1, 0, 50, 10);
        themComponent(panel, lbMaTS, 2, 0, 30, 10);
        themComponent(panel, tfMaTS, 3, 0, 50, 10);
        themComponent(panel, btLoc, 4, 0, 40, 10);
        return panel;
    }

    private JPanel panelListStudent() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Danh sách thí sinh",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        panel.setLayout(new BorderLayout());

        table = taoBang();
        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll);
        hienThiBang(list);
        return panel;
    }

    private JPanel panelStudentInfo() {
        JPanel panel = new JPanel();
        //thiết kế tên , viền và bố cục 
        panel.setLayout(gridBagLayout);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Thông tin thí sinh",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        gridBagConstraint.insets = new Insets(5, 10, 5, 10);
        gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;

        tfMaThiSinh = taoTextField();
        tfTenThiSinh = taoTextField();
        tfNgaySinh = taoTextField();
        tfDiemToan = taoTextField();
        tfDiemLy = taoTextField();
        tfDiemHoa = taoTextField();
        tfTongDiem = taoTextField();

        cbThanhPho = taoComboBox();

        rbtNam = new JRadioButton("Nam");
        rbtNam.setSelected(true);
        rbtNu = new JRadioButton("Nữ");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtNam);
        genderGroup.add(rbtNu);

        JPanel gender = new JPanel();
        gender.setLayout(new GridLayout());
        gender.add(rbtNam);
        gender.add(rbtNu);

        setLockEdit();
        //Thêm các component
        JLabel lbMaThiSinh = taoNhan("Mã thí sinh");
        JLabel lbTenThiSinh = taoNhan("Họ và tên");
        JLabel lbThanhPho = taoNhan("Quê quán");
        JLabel lbNgaySinh = taoNhan("Ngày sinh");
        JLabel lbGioiTinh = taoNhan("Giới tính");
        JLabel lbDiemToan = taoNhan("Điểm toán");
        JLabel lbDiemLy = taoNhan("Điểm lí");
        JLabel lbDiemHoa = taoNhan("Điểm hoá");
        JLabel lbTotal = taoNhan("Tổng điểm");

        themComponent(panel, lbMaThiSinh, 0, 0, 40, 10);
        themComponent(panel, tfMaThiSinh, 1, 0, 50, 10);
        themComponent(panel, lbDiemToan, 2, 0, 40, 10);
        themComponent(panel, tfDiemToan, 3, 0, 50, 10);

        themComponent(panel, lbTenThiSinh, 0, 1, 40, 10);
        themComponent(panel, tfTenThiSinh, 1, 1, 50, 10);
        themComponent(panel, lbDiemLy, 2, 1, 40, 10);
        themComponent(panel, tfDiemLy, 3, 1, 50, 10);

        themComponent(panel, lbThanhPho, 0, 2, 40, 10);
        themComponent(panel, cbThanhPho, 1, 2, 50, 10);
        themComponent(panel, lbDiemHoa, 2, 2, 40, 10);
        themComponent(panel, tfDiemHoa, 3, 2, 50, 10);

        themComponent(panel, lbNgaySinh, 0, 3, 40, 10);
        themComponent(panel, tfNgaySinh, 1, 3, 50, 10);
        themComponent(panel, lbTotal, 2, 3, 40, 10);
        themComponent(panel, tfTongDiem, 3, 3, 50, 10);

        themComponent(panel, lbGioiTinh, 0, 4, 40, 10);
        themComponent(panel, gender, 1, 4, 50, 10);
        return panel;
    }

    private JPanel panelAction() {
        JPanel panel = new JPanel();

        panel.setLayout(gridBagLayout);
        panel.setBorder(new EtchedBorder());
        gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraint.insets = new Insets(0, 10, 0, 10);

        btSapXep = taoNut("Sắp xếp", "Sort");
        btThem = taoNut("Thêm", "Add2");
        btSua = taoNut("Sửa", "Edit1");
        btXoa = taoNut("Xoá", "Delete");
        btOK = taoNut("OK", "OK");
        btCancel = taoNut("Cancel", "Cancel");
        btOK.setEnabled(false);

        themComponent(panel, btSapXep, 0, 0, 30, 10);
        themComponent(panel, btThem, 1, 0, 30, 10);
        themComponent(panel, btSua, 2, 0, 30, 10);
        themComponent(panel, btXoa, 3, 0, 30, 10);
        themComponent(panel, btOK, 4, 0, 30, 10);
        themComponent(panel, btCancel, 5, 0, 30, 10);
        return panel;
    }

    /**
     * Add component to Container
     *
     * @param ct Container
     * @param c Component
     * @param col col in layout
     * @param row row in Layout
     * @param nCol weight square
     * @param nRow height square
     */
    public void themComponent(Container ct, Component c, int col, int row, int nCol, int nRow) {
        gridBagConstraint.gridx = col;
        gridBagConstraint.gridy = row;

        gridBagConstraint.ipadx = nCol;
        gridBagConstraint.ipady = nRow;
        gridBagLayout.setConstraints(c, gridBagConstraint);
        ct.add(c);
    }

    /**
     * create a label
     *
     * @param title title of label
     * @return label
     */
    public JLabel taoNhan(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setPreferredSize(new Dimension(70, 20));
        return label;
    }

    /**
     * create a button
     *
     * @param title title of button
     * @param linkIcon link icon
     * @return button
     */
    public JButton taoNut(String title, String linkIcon) {
        JButton button = new JButton(title);
        button.setSize(new Dimension(70, 20));
        button.setIcon(new ImageIcon("icon\\" + linkIcon + ".png"));
        button.addActionListener(this);
        return button;
    }

    /**
     * create a textField
     *
     * @return textField
     */
    public JTextField taoTextField() {
        JTextField textField = new JTextField("", 13);
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        return textField;
    }

    /**
     * create a comboBox
     *
     * @return comboBox
     */
    public JComboBox<String> taoComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(80, 20));
        comboBox.setFont(new Font("Arial", 0, 12));
        List<String> listModel = new ArrayList<>();

        dstt.getListTT().forEach((tt) -> {
            listModel.add(tt.getTenTinh());
        });

        String[] model = new String[listModel.size()];
        listModel.toArray(model);
        comboBox.setModel(new DefaultComboBoxModel<>(model));
        return comboBox;
    }

    /**
     * create table list student
     *
     * @return table list student
     */
    private JTable taoBang() {
        JTable tb = new JTable();
        String[] colsName = {"STT", "Mã thí sinh", "Họ và tên", "Quê quán",
            "Ngày sinh", "Giới tính", "Điểm toán", "Điểm lí", "Điểm hoá"};

        tableModel = new DefaultTableModel(new Object[][]{}, colsName) {
            Class[] types = new Class[]{
                Integer.class, Integer.class, String.class, String.class,
                String.class, String.class, Float.class, Float.class, Float.class};

            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false};

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

        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                hienThiThiSinh(tb);
            }
        });

        tb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); //To change body of generated methods, choose Tools | Templates.
                hienThiThiSinh(tb);
            }

        });
        return tb;
    }

    /*
     * Đẩy dữ liệu lên giao diện
     */
    public static void hienThiBang(List<ThiSinh> list) {
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

    private void hienThiThiSinh(JTable tb) {
        int hang = tb.getSelectedRow();
        String id = String.valueOf(table.getValueAt(hang, 1));
        String name = (String) tb.getValueAt(hang, 2);
        String thanhPho = (String) tb.getValueAt(hang, 3);
        String ngaySinh = (String) tb.getValueAt(hang, 4);
        String gender = (String) tb.getValueAt(hang, 5);
        String toan = String.valueOf(tb.getValueAt(hang, 6));
        String ly = String.valueOf(tb.getValueAt(hang, 7));
        String hoa = String.valueOf(tb.getValueAt(hang, 8));

        cbThanhPho.setSelectedItem(thanhPho);
        tfMaThiSinh.setText(id);
        tfTenThiSinh.setText(name);

        String result[] = ngaySinh.split("-");
        tfNgaySinh.setText(result[0] + "/" + result[1] + "/" + result[2]);
        if (gender.toLowerCase().equals("nam")) {
            rbtNam.setSelected(true);
        } else {
            rbtNu.setSelected(true);
        }
        tfDiemToan.setText(toan);
        tfDiemLy.setText(ly);
        tfDiemHoa.setText(hoa);
        float total = Float.parseFloat(toan) + Float.parseFloat(ly) + Float.parseFloat(hoa);
        DecimalFormat formatter = new DecimalFormat("#.#");
        tfTongDiem.setText(formatter.format(total));
    }

    public boolean themThiSinh() {
        int maTS;
        try {
            maTS = Integer.parseInt(tfMaThiSinh.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bạn phải nhập mã thí sinh là một số nguyên");
            return false;
        }

        String tenTS = tfTenThiSinh.getText();
        if (tenTS.equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn phải nhập vào tên thí sinh");
            return false;
        }
        String thanhPho = (String) cbThanhPho.getSelectedItem();

        String ngaySinh = tfNgaySinh.getText();
        try {
            MyDate.checkDate(ngaySinh);
        } catch (MyException ex) {
            if (ex.getMessage().equals("date1")) {
                JOptionPane.showMessageDialog(null, "Bạn phải nhập ngày dạng dd/MM/yyyy");
                return false;
            }
            if (ex.getMessage().equals("date2")) {
                JOptionPane.showMessageDialog(null, "tháng không tồn tại");
                return false;
            }
            if (ex.getMessage().equals("date3")) {
                JOptionPane.showMessageDialog(null, "ngày" + ngaySinh + " không tồn tại");
                return false;
            }
            if (ex.getMessage().equals("age1")) {
                JOptionPane.showMessageDialog(null, "thí sinh chưa đủ tuổi dự thi");
                return false;
            }
            if (ex.getMessage().equals("age2")) {
                JOptionPane.showMessageDialog(null, "thí sinh chưa được sinh ra");
                return false;
            }
            if (ex.getMessage().equals("age3")) {
                JOptionPane.showMessageDialog(null, "Bạn hãy nhập năm sinh của thí sinh hợp lí hơn");
                return false;
            }
        }
        MyDate dateOfBirth = new MyDate(ngaySinh);
        boolean gioiTinh = true;
        if (rbtNam.isSelected()) {
            gioiTinh = true;
        }
        if (rbtNu.isSelected()) {
            gioiTinh = false;
        }
        float toan;
        try {
            toan = Float.parseFloat(tfDiemToan.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bạn phải nhập điểm toán là một số thực nằm trong đoạn [0,10]");
            return false;
        }
        float ly;
        try {
            ly = Float.parseFloat(tfDiemLy.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bạn phải nhập điểm lí là một số thực nằm trong đoạn [0,10]");
            return false;
        }
        float hoa;

        try {
            hoa = Float.parseFloat(tfDiemHoa.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bạn phải nhập điểm hoá là một số thực nằm trong đoạn [0,10]");
            return false;
        }
        tfTongDiem.setText((toan + ly + hoa) + "");
        try {
            dsts.themThiSinh(maTS, tenTS, thanhPho, dateOfBirth, gioiTinh, toan, ly, hoa);
            return true;
        } catch (MyException e) {
            if (e.getMessage().equals("maTS")) {
                JOptionPane.showMessageDialog(null, "Bạn không thể thêm thí sinh trùng mã");
            }
            if (e.getMessage().equals("toan")) {
                JOptionPane.showMessageDialog(null, "Điểm toán không hợp lệ");
            }
            if (e.getMessage().equals("ly")) {
                JOptionPane.showMessageDialog(null, "Điểm lý không hợp lệ");
            }
            if (e.getMessage().equals("hoa")) {
                JOptionPane.showMessageDialog(null, "Điểm hoá không hợp lệ");
            }

            return false;
        }
    }

    public int suaThiSinh() {
        int kt = -1;
        try {
            int hang = table.getSelectedRow();
            int maTS = Integer.parseInt(String.valueOf(table.getValueAt(hang, 1)));
            String tenTS = (String) table.getValueAt(hang, 2);
            String thanhPho = (String) table.getValueAt(hang, 3);
            String ngaySinh = (String) table.getValueAt(hang, 4);
            String gioiTinh = (String) table.getValueAt(hang, 5);
            boolean gt;
            gt = gioiTinh.equals("Nam");
            float toan = Float.parseFloat(String.valueOf(table.getValueAt(hang, 6)));
            float ly = Float.parseFloat(String.valueOf(table.getValueAt(hang, 7)));
            float hoa = Float.parseFloat(String.valueOf(table.getValueAt(hang, 8)));

            int maTS1;
            try {
                maTS1 = Integer.parseInt(tfMaThiSinh.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "\nBạn phải nhập mã thí sinh là một số nguyên");
                kt = 0;
                return kt;
            }

            String name1 = tfTenThiSinh.getText();
            if (name1.equals("")) {
                JOptionPane.showMessageDialog(null, "\nBạn phải nhập vào tên thí sinh");
                kt = 0;
                return kt;
            }
            String thanhPho1 = (String) cbThanhPho.getSelectedItem();
            String ngaySinh1 = tfNgaySinh.getText();
            try {
                MyDate.checkDate(ngaySinh);
            } catch (MyException ex) {
                kt = 0;
                if (ex.getMessage().equals("date1")) {
                    JOptionPane.showMessageDialog(null, "Bạn phải nhập ngày dạng dd/MM/yyyy");
                    return kt;
                }
                if (ex.getMessage().equals("date2")) {
                    JOptionPane.showMessageDialog(null, "tháng không tồn tại");
                    return kt;
                }
                if (ex.getMessage().equals("date3")) {
                    JOptionPane.showMessageDialog(null, "ngày" + ngaySinh + " không tồn tại");
                    return kt;
                }
                if (ex.getMessage().equals("age1")) {
                    JOptionPane.showMessageDialog(null, "thí sinh chưa đủ tuổi dự thi");
                    return kt;
                }
                if (ex.getMessage().equals("age2")) {
                    JOptionPane.showMessageDialog(null, "thí sinh chưa được sinh ra");
                    return kt;
                }
                if (ex.getMessage().equals("age3")) {
                    JOptionPane.showMessageDialog(null, "Bạn hãy nhập năm sinh của thí sinh hợp lí hơn");
                    return kt;
                }

            }
            boolean gioiTinh1 = true;
            if (rbtNam.isSelected()) {
                gioiTinh1 = true;
            }
            if (rbtNu.isSelected()) {
                gioiTinh1 = false;
            }
            float toan1;
            try {
                toan1 = Float.parseFloat(tfDiemToan.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.toString()
                        + "\nBạn phải nhập điểm toán là một số thực nằm trong đoạn [0,10]");
                kt = 0;
                return kt;
            }
            float ly1;
            try {
                ly1 = Float.parseFloat(tfDiemLy.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.toString()
                        + "\nBạn phải nhập điểm lí là một số thực nằm trong đoạn [0,10]");
                kt = 0;
                return kt;
            }
            float hoa1;

            try {
                hoa1 = Float.parseFloat(tfDiemHoa.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.toString()
                        + "\nBạn phải nhập điểm hoá là một số thực nằm trong đoạn [0,10]");
                kt = 0;
                return kt;
            }
            tfTongDiem.setText((toan1 + ly1 + hoa1) + "");

            if (maTS != maTS1) {
                try {
                    kt = dsts.suaMaThiSinh(maTS, maTS1) ? 1 : 0;
                } catch (MyException e) {
                    JOptionPane.showMessageDialog(null, "Bạn không thể sửa thí sinh trùng mã");
                }
            }
            if (tenTS.equals(name1) == false) {
                kt = dsts.suaTenThiSinh(maTS1, name1) ? 1 : 0;
            }
            if (thanhPho.equals(thanhPho1) != true) {
                kt = dsts.suaQueThiSinh(maTS1, thanhPho1) ? 1 : 0;
            }
            if (ngaySinh.equals(ngaySinh1) == false) {
                MyDate date = new MyDate(ngaySinh1);
                kt = dsts.suaNgaySinhThiSinh(maTS1, date) ? 1 : 0;
            }
            if (gt != gioiTinh1) {
                kt = dsts.suaGioiTinhThiSinh(maTS1, gioiTinh1) ? 1 : 0;
            }
            if (toan != toan1) {
                kt = dsts.suaDiemToan(maTS1, toan1) ? 1 : 0;
            }
            if (ly != ly1) {
                kt = dsts.suaDiemLy(maTS1, ly1) ? 1 : 0;
            }
            if (hoa != hoa1) {
                kt = dsts.suaDiemHoa(maTS1, hoa1) ? 1 : 0;
            }
            return kt;
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        return kt;
    }

    private boolean locThiSinh() {
        setLockEnableButton();
        btOK.setEnabled(false);
        List<ThiSinh> li;
        if (tfNoiSinh.getText().equals("") == true) {
            if (tfMaTS.getText().equals("") == true) {
                JOptionPane.showMessageDialog(null, "Bạn phải lọc theo tiêu chí nào đó!");
                setEnableButton();
            } else {
                li = dsts.locTheoMaThiSinh(tfMaTS.getText());
                hienThiBang(li);
            }
        } else {

            if (tfMaTS.getText().equals("") == true) {
                li = dsts.locTheoQueQuan(tfNoiSinh.getText());
                hienThiBang(li);
            } else {
                li = dsts.locTheoMaThiSinhVaQueQuan(tfMaTS.getText(), tfNoiSinh.getText());
                hienThiBang(li);
            }
        }
        return true;
    }

    private boolean xoaThiSinh() {
        int hang = table.getSelectedRow();
        if (hang < 0) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng nào để xoá");
        } else {
            int id = Integer.parseInt(String.valueOf(table.getValueAt(hang, 1)));
            int xoa = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thí sinh có mã  thí sinh là " + id,
                    "Delete", JOptionPane.YES_NO_OPTION);
            if (xoa == JOptionPane.YES_OPTION) {
                dsts.xoaThiSinh(id);
                setDefault();
                JOptionPane.showMessageDialog(null, "Xóa thành công!");
            }
            hienThiBang(list);
        }
        return true;
    }

    /**
     * Một số thao tác khi xử lí
     */
    private void setLockEnableButton() {
        btOK.setEnabled(true);
        btThem.setEnabled(false);
        btSapXep.setEnabled(false);
        btSua.setEnabled(false);
        btXoa.setEnabled(false);
        btLoc.setEnabled(false);
    }

    private void setEnableButton() {
        btOK.setEnabled(false);
        btThem.setEnabled(true);
        btSapXep.setEnabled(true);
        btSua.setEnabled(true);
        btXoa.setEnabled(true);
        btLoc.setEnabled(true);
    }

    private void setLockEdit() {
        tfMaThiSinh.setEditable(false);
        tfTenThiSinh.setEditable(false);
        tfNgaySinh.setEditable(false);
        tfDiemToan.setEditable(false);
        tfDiemLy.setEditable(false);
        tfDiemHoa.setEditable(false);
        tfTongDiem.setEditable(false);

        cbThanhPho.setEnabled(false);
        rbtNam.setEnabled(false);
        rbtNu.setEnabled(false);
    }

    private void setEdit() {
        tfMaThiSinh.setEditable(true);
        tfTenThiSinh.setEditable(true);
        tfNgaySinh.setEditable(true);
        tfDiemToan.setEditable(true);
        tfDiemLy.setEditable(true);
        tfDiemHoa.setEditable(true);

        cbThanhPho.setEnabled(true);
        rbtNam.setEnabled(true);
        rbtNu.setEnabled(true);
    }

    private void setDefault() {
        tfMaThiSinh.setText("");
        tfTenThiSinh.setText("");
        tfNgaySinh.setText("");
        tfDiemToan.setText("");
        tfDiemLy.setText("");
        tfDiemHoa.setText("");
        tfTongDiem.setText("");
        tfNoiSinh.setText("");
        tfMaTS.setText("");

        cbThanhPho.setSelectedIndex(0);
        rbtNam.setSelected(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Lọc")) {
            locThiSinh();
        }
        if (e.getActionCommand().equals("Sắp xếp")) {
            new Sort().setVisible(true);
        }
        if (e.getActionCommand().equals("Thêm")) {
            int hang = table.getSelectedRow();
            if (hang > 0) {
                this.setDefault();
            }
            check = 1;
            setEdit();
            setLockEnableButton();
        }
        if (e.getActionCommand().equals("Sửa")) {
            int hang = table.getSelectedRow();
            if (hang < 0) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng nào để sửa");
                setDefault();
                setEnableButton();
                setLockEdit();
            } else {
                setLockEnableButton();
                setEdit();
                check = 2;
            }
        }
        if (e.getActionCommand().equals("Xoá")) {
            xoaThiSinh();
        }
        if (e.getActionCommand().equals("OK")) {
            if (check == 1) {
                if (themThiSinh()) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại");
                }
            }
            if (check == 2) {
                int kt = suaThiSinh();
                switch (kt) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "Sửa thất bại");
                        hienThiThiSinh(table);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Bạn chưa sửa gì");
                        break;
                }
            }

            hienThiBang(list);

            setLockEdit();
            setEnableButton();
        }
        if (e.getActionCommand().equals("Cancel")) {
            setDefault();
            setEnableButton();
            setLockEdit();
            hienThiBang(list);
        }
    }

}
