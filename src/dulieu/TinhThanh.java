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
public class TinhThanh {

    // Attribute
    private int maTinh;
    private String tenTinh;

    // Constructor
    public TinhThanh() {
        this.maTinh = 0;
        this.tenTinh = "";
    }

    public TinhThanh(int maTinh, String tenTinh) {
        this.maTinh = maTinh;
        this.tenTinh = tenTinh;
    }

    // Getter and Setter
    public int getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(int maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    // toString
    @Override
    public String toString() {
        return "TinhThanh [maTinh=" + maTinh + ", tenTinh=" + tenTinh + "]";
    }

}
