/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien1;

import dulieu.MyDate;
import javax.swing.JOptionPane;
import xuli.DanhSachThiSinh;
import xuli.MyException;

/**
 *
 * @author SBE
 */
public class XuLyDuLieuTrenForm {

    //Components
    private PanelListStudent panelListStudent;
    private PanelStudentInfo panelStudentInfo;
    private DanhSachThiSinh dsts;

    //Constructor
    public XuLyDuLieuTrenForm(DanhSachThiSinh dsts) {
        this.panelListStudent = new PanelListStudent();
        this.panelStudentInfo = new PanelStudentInfo();
        this.dsts = dsts;
    }

    //Getter and setter
    public PanelListStudent getPanelListStudent() {
        return panelListStudent;
    }

    public void setPanelListStudent(PanelListStudent panelListStudent) {
        this.panelListStudent = panelListStudent;
    }

    public PanelStudentInfo getPanelStudentInfo() {
        return panelStudentInfo;
    }

    public void setPanelStudentInfo(PanelStudentInfo panelStudentInfo) {
        this.panelStudentInfo = panelStudentInfo;
    }

    public DanhSachThiSinh getDsts() {
        return dsts;
    }

    public void setDsts(DanhSachThiSinh dsts) {
        this.dsts = dsts;
    }

    //Method
    //show message
    private boolean batLoiNhap(String noiDung, String title) {
        JOptionPane.showMessageDialog(null, noiDung, title, JOptionPane.ERROR_MESSAGE);
        return false;
    }

    //check data input
    public boolean checkDuLieuNhap(String[] dl, String title) {
        boolean check = true;
        if (dl[0].equals("") || dl[1].equals("") || dl[2].equals("") || dl[3].equals("") || dl[4].equals("")
                || dl[5].equals("") || dl[6].equals("") || dl[7].equals("")) {
            check = batLoiNhap("Bạn phải nhập đầy đủ thông tin!", title);
            return check;
        }
        int maTS;
        try {
            maTS = Integer.parseInt(dl[0]);
        } catch (NumberFormatException e) {
            check = batLoiNhap("Bạn phải nhập mã thí sinh là một số nguyên", title);
        }
        if (dl[1].equals("")) {
            check = batLoiNhap("Bạn phải nhập vào tên thí sinh", title);
        }
        try {
            MyDate.checkDate(dl[3]);
        } catch (MyException ex) {
            if (ex.getMessage().equals("date1")) {
                check = batLoiNhap("Bạn phải nhập ngày dạng dd/MM/yyyy", title);
            }
            if (ex.getMessage().equals("date2")) {
                check = batLoiNhap("tháng không tồn tại", title);
            }
            if (ex.getMessage().equals("date3")) {
                check = batLoiNhap("ngày" + dl[3] + " không tồn tại", title);
            }
            if (ex.getMessage().equals("age1")) {
                check = batLoiNhap("thí sinh chưa đủ tuổi dự thi", title);
            }
            if (ex.getMessage().equals("age2")) {
                check = batLoiNhap("thí sinh chưa được sinh ra", title);
            }
            if (ex.getMessage().equals("age3")) {
                check = batLoiNhap("Bạn hãy nhập năm sinh của thí sinh hợp lí hơn", title);
            }
        }
        try {
            float toan = Float.parseFloat(dl[5]);
            if (toan < 0 || toan > 10) {
                check = batLoiNhap("Điểm toán không hợp lệ", title);
            }
        } catch (NumberFormatException e) {
            check = batLoiNhap("Bạn phải nhập điểm toán là một số thực nằm trong đoạn [0,10]", title);
        }
        try {
            float ly = Float.parseFloat(dl[6]);
            if (ly < 0 || ly > 10) {
                check = batLoiNhap("Điểm lý không hợp lệ", title);
            }
        } catch (NumberFormatException e) {
            check = batLoiNhap("Bạn phải nhập điểm lý là một số thực nằm trong đoạn [0,10]", title);
        }
        try {
            float hoa = Float.parseFloat(dl[7]);
            if (hoa < 0 || hoa > 10) {
                check = batLoiNhap("Điểm hoá không hợp lệ", title);
            }
        } catch (NumberFormatException e) {
            check = batLoiNhap("Bạn phải nhập điểm hoá là một số thực nằm trong đoạn [0,10]", title);
        }
        return check;
    }

    public int suaThiSinh() {
        int kt = -1;
        String dl[] = panelStudentInfo.layDuLieu();
        String dl1[] = panelListStudent.layDuLieu();
        //lấy dữ liệu nhập
        if (checkDuLieuNhap(dl, "Sửa")) {
            int maTS = Integer.parseInt(dl[0]);
            String tenTS = dl[1];
            String thanhPho = dl[2];
            boolean gioiTinh = dl[4].toLowerCase().equals("nam");
            float toan = Float.parseFloat(dl[5]);
            float ly = Float.parseFloat(dl[6]);
            float hoa = Float.parseFloat(dl[7]);
            panelStudentInfo.datTextTongDiem(toan + ly + hoa + "");

            //lấy dữ liệu từ bảng
            int maTS1 = Integer.parseInt(dl1[0]);
            String tenTS1 = dl1[1];
            String thanhPho1 = dl1[2];
            String ngaySinh1 = dl1[3];
            boolean gioiTinh1 = dl1[4].toLowerCase().equals("nam");
            float toan1 = Float.parseFloat(dl1[5]);
            float ly1 = Float.parseFloat(dl1[6]);
            float hoa1 = Float.parseFloat(dl1[7]);
            int hang = panelListStudent.hangDuocChon();
            Object ob[] = new Object[]{hang, maTS1, tenTS1, thanhPho1, ngaySinh1,
                dl1[4], toan1, ly1, hoa1};
            if (maTS != maTS1) {
                try {
                    kt = dsts.suaMaThiSinh(maTS1, maTS) ? 1 : 0;
                    ob[1] = maTS;
                } catch (MyException e) {
                    JOptionPane.showMessageDialog(null, "Bạn không thể sửa thí sinh trùng mã", "Sửa",
                            JOptionPane.ERROR_MESSAGE);
                    kt = 0;
                    return kt;
                }
            }
            if (tenTS.equals(tenTS1) == false) {
                kt = dsts.suaTenThiSinh(maTS, tenTS) ? 1 : 0;
                ob[2] = tenTS;
            }
            if (thanhPho.equals(thanhPho1) != true) {
                kt = dsts.suaQueThiSinh(maTS, thanhPho1) ? 1 : 0;
                ob[3] = thanhPho;
            }
            if (MyDate.compareDate(dl[3], ngaySinh1) == false) {
                MyDate date = new MyDate(dl[3]);
                kt = dsts.suaNgaySinhThiSinh(maTS, date) ? 1 : 0;
                ob[4] = date.formatDate();
            }
            if (gioiTinh != gioiTinh1) {
                kt = dsts.suaGioiTinhThiSinh(maTS, gioiTinh1) ? 1 : 0;
                ob[5] = dl[4];
            }
            if (toan != toan1) {
                kt = dsts.suaDiemToan(maTS, toan) ? 1 : 0;
                ob[6] = toan;
            }
            if (ly != ly1) {
                kt = dsts.suaDiemLy(maTS, ly) ? 1 : 0;
                ob[7] = ly;
            }
            if (hoa != hoa1) {
                kt = dsts.suaDiemHoa(maTS, hoa) ? 1 : 0;
                ob[8] = hoa;
            }
            if (kt == 1) {
                panelListStudent.suaThiSinhTrongBang(hang, ob);
            }
            return kt;
        }
        return 0;
    }

    public boolean themThiSinh() {
        String[] dl = panelStudentInfo.layDuLieu();
        if (checkDuLieuNhap(dl, "Thêm")) {
            int maTS = Integer.parseInt(dl[0]);
            String tenTS = dl[1];
            String thanhPho = dl[2];
            MyDate ngaySinh = new MyDate(dl[3]);
            boolean gioiTinh = dl[4].toLowerCase().equals("nam");
            float toan = Float.parseFloat(dl[5]);
            float ly = Float.parseFloat(dl[6]);
            float hoa = Float.parseFloat(dl[7]);
            panelStudentInfo.datTextTongDiem(toan + ly + hoa + "");
            try {
                dsts.themThiSinh(maTS, tenTS, thanhPho, ngaySinh, gioiTinh, toan, ly, hoa);
                int stt = panelListStudent.laySTT() + 1;
                Object[] ob = new Object[]{stt, maTS, tenTS, thanhPho,
                    ngaySinh.formatDate(), dl[4], toan, ly, hoa};
                panelListStudent.themThiSinhVaoBang(ob);
                return true;
            } catch (MyException e) {
                return batLoiNhap("Bạn không thể thêm thí sinh trùng mã", "Thêm");
            }
        }
        return false;
    }
}
