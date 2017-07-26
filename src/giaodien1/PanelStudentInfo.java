/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import xuli.DanhSachTinhThanh;

/**
 *
 * @author SBE
 */
public class PanelStudentInfo extends JPanel {

    /**
     * Components
     */
    private JTextField tfMaThiSinh, tfTenThiSinh, tfNgaySinh, tfDiemToan, tfDiemLy, tfDiemHoa, tfTongDiem;
    private JRadioButton rbtNam, rbtNu;
    private JComboBox<String> cbThanhPho;

    private DanhSachTinhThanh dstt;

    /**
     * Constructor
     */
    public PanelStudentInfo() {
        super();
        init();
    }

    /**
     * Initialization
     */
    private void init() {
        this.setLayout(TaoThanhPhan.gridBagLayout);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Thông tin thí sinh",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14)));
        TaoThanhPhan.gridBagConstraint.insets = new Insets(5, 10, 5, 10);
        TaoThanhPhan.gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;

        tfMaThiSinh = TaoThanhPhan.taoTextField();
        tfTenThiSinh = TaoThanhPhan.taoTextField();
        tfNgaySinh = TaoThanhPhan.taoTextField();
        tfDiemToan = TaoThanhPhan.taoTextField();
        tfDiemLy = TaoThanhPhan.taoTextField();
        tfDiemHoa = TaoThanhPhan.taoTextField();
        tfTongDiem = TaoThanhPhan.taoTextField();

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
        this.khoaONhap();

//        setLockEdit();
        //Thêm các component
        JLabel lbMaThiSinh = TaoThanhPhan.taoNhan("Mã thí sinh");
        JLabel lbTenThiSinh = TaoThanhPhan.taoNhan("Họ và tên");
        JLabel lbThanhPho = TaoThanhPhan.taoNhan("Quê quán");
        JLabel lbNgaySinh = TaoThanhPhan.taoNhan("Ngày sinh");
        JLabel lbGioiTinh = TaoThanhPhan.taoNhan("Giới tính");
        JLabel lbDiemToan = TaoThanhPhan.taoNhan("Điểm toán");
        JLabel lbDiemLy = TaoThanhPhan.taoNhan("Điểm lí");
        JLabel lbDiemHoa = TaoThanhPhan.taoNhan("Điểm hoá");
        JLabel lbTotal = TaoThanhPhan.taoNhan("Tổng điểm");

        TaoThanhPhan.themComponent(this, lbMaThiSinh, 0, 0, 40, 10);
        TaoThanhPhan.themComponent(this, tfMaThiSinh, 1, 0, 60, 15);
        TaoThanhPhan.themComponent(this, lbDiemToan, 2, 0, 40, 10);
        TaoThanhPhan.themComponent(this, tfDiemToan, 3, 0, 60, 15);

        TaoThanhPhan.themComponent(this, lbTenThiSinh, 0, 1, 40, 10);
        TaoThanhPhan.themComponent(this, tfTenThiSinh, 1, 1, 60, 15);
        TaoThanhPhan.themComponent(this, lbDiemLy, 2, 1, 40, 10);
        TaoThanhPhan.themComponent(this, tfDiemLy, 3, 1, 60, 15);

        TaoThanhPhan.themComponent(this, lbThanhPho, 0, 2, 40, 10);
        TaoThanhPhan.themComponent(this, cbThanhPho, 1, 2, 60, 15);
        TaoThanhPhan.themComponent(this, lbDiemHoa, 2, 2, 40, 10);
        TaoThanhPhan.themComponent(this, tfDiemHoa, 3, 2, 60, 15);

        TaoThanhPhan.themComponent(this, lbNgaySinh, 0, 3, 40, 10);
        TaoThanhPhan.themComponent(this, tfNgaySinh, 1, 3, 60, 15);
        TaoThanhPhan.themComponent(this, lbTotal, 2, 3, 40, 10);
        TaoThanhPhan.themComponent(this, tfTongDiem, 3, 3, 60, 15);

        TaoThanhPhan.themComponent(this, lbGioiTinh, 0, 4, 40, 10);
        TaoThanhPhan.themComponent(this, gender, 1, 4, 60, 15);
    }

    public JComboBox<String> taoComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(80, 20));
        comboBox.setFont(new Font("Arial", 0, 12));

        dstt = new DanhSachTinhThanh();
        //foreach
        dstt.getListTT().forEach((tt) -> {
            comboBox.addItem(tt.getTenTinh());
        });

        return comboBox;
    }

    public String[] layDuLieu() {
        String[] dl = new String[8];
        dl[0] = tfMaThiSinh.getText().trim();
        dl[1] = tfTenThiSinh.getText().trim();
        dl[2] = (String) cbThanhPho.getSelectedItem();
        dl[3] = tfNgaySinh.getText().trim();
        if (rbtNam.isSelected()) {
            dl[4] = "Nam";
        } else {
            dl[4] = "Nữ";
        }
        dl[5] = tfDiemToan.getText().trim();
        dl[6] = tfDiemLy.getText().trim();
        dl[7] = tfDiemHoa.getText().trim();
        return dl;
    }

    public boolean hienThiThiSinh(String[] dl) {
        cbThanhPho.setSelectedItem(dl[2]);
        tfMaThiSinh.setText(dl[0]);
        tfTenThiSinh.setText(dl[1]);

        String result[] = dl[3].split("-");
        tfNgaySinh.setText(result[0] + "/" + result[1] + "/" + result[2]);
        if (dl[4].toLowerCase().equals("nam")) {
            rbtNam.setSelected(true);
        } else {
            rbtNu.setSelected(true);
        }
        tfDiemToan.setText(dl[5]);
        tfDiemLy.setText(dl[6]);
        tfDiemHoa.setText(dl[7]);
        float tongDiem = Float.parseFloat(dl[5]) + Float.parseFloat(dl[6]) + Float.parseFloat(dl[7]);
        DecimalFormat formatter = new DecimalFormat("#.#");
        tfTongDiem.setText(formatter.format(tongDiem));
        return true;
    }

    public void khoaONhap() {
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

    public void moONhap() {
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

    public void datMacDinh() {
        tfMaThiSinh.setText("");
        tfTenThiSinh.setText("");
        tfNgaySinh.setText("");
        tfDiemToan.setText("");
        tfDiemLy.setText("");
        tfDiemHoa.setText("");
        tfTongDiem.setText("");

        cbThanhPho.setSelectedIndex(0);
        rbtNam.setSelected(true);

    }

    public void datTextTongDiem(String text) {
        tfTongDiem.setText(text);
    }
}
