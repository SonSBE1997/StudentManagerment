/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulieu;


/**
 *
 * @author SBE
 */
public class ThiSinh {

    // Attribute
    private int maThiSinh;
    private String tenThiSinh;
    private int maQueQuan;
    private MyDate ngaySinh;
    private boolean gioiTinh;
    private float diemToan, diemLy, diemHoa;

    // Constructor
    public ThiSinh() {
        this.maThiSinh = 0;
        this.tenThiSinh = "";
        this.maQueQuan = 0;
        this.ngaySinh = null;
        this.gioiTinh = true;
        this.diemToan = 0.f;
        this.diemLy = 0.f;
        this.diemHoa = 0.f;
    }

    public ThiSinh(int maThiSinh, String tenThiSinh, int maQueQuan,
            MyDate ngaySinh, boolean gioiTinh, float diemToan, float diemLy,
            float diemHoa) {
        this.maThiSinh = maThiSinh;
        this.tenThiSinh = tenThiSinh;
        this.maQueQuan = maQueQuan;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diemToan = diemToan;
        this.diemLy = diemLy;
        this.diemHoa = diemHoa;
    }

    // Getter and setter
    public int getMaThiSinh() {
        return maThiSinh;
    }

    public void setMaThiSinh(int maThiSinh) {
        this.maThiSinh = maThiSinh;
    }

    public String getTenThiSinh() {
        return tenThiSinh;
    }

    public void setTenThiSinh(String tenThiSinh) {
        this.tenThiSinh = tenThiSinh;
    }

    public int getMaQueQuan() {
        return maQueQuan;
    }

    public void setMaQueQuan(int maQueQuan) {
        this.maQueQuan = maQueQuan;
    }

    public MyDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(MyDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public float getDiemToan() {
        return diemToan;
    }

    public void setDiemToan(float diemToan) {
        this.diemToan = diemToan;
    }

    public float getDiemLy() {
        return diemLy;
    }

    public void setDiemLy(float diemLy) {
        this.diemLy = diemLy;
    }

    public float getDiemHoa() {
        return diemHoa;
    }

    public void setDiemHoa(float diemHoa) {
        this.diemHoa = diemHoa;
    }

    // toString
    @Override
    public String toString() {
        return "ThiSinh [maThiSinh=" + maThiSinh + ", tenThiSinh=" + tenThiSinh
                + ", maQueQuan=" + maQueQuan + ", ngaySinh=" + ngaySinh
                + ", gioiTinh=" + gioiTinh + ", diemToan=" + diemToan
                + ", diemLy=" + diemLy + ", diemHoa=" + diemHoa + "]";
    }
}
