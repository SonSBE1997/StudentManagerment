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
import dulieu.MyDate;
import dulieu.ThiSinh;
import dulieu.TinhThanh;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Data {

    //Đọc ghi tinh thanh
    public List<TinhThanh> docTinhThanh() {
        List<TinhThanh> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("tinhthanh.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(";");
                list.add(new TinhThanh(Integer.parseInt(result[0]), result[1]));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại");
        }

        return list;
    }

    public boolean ghiTinhThanh(List<TinhThanh> li) {
        try {
            FileWriter fw = new FileWriter("tinhthanh.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (TinhThanh x : li) {
                String ob = x.getMaTinh() + "," + x.getTenTinh() + "\n";
                bw.write(ob);
            }
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ghi dữ liệu thất bại", "", JOptionPane.ERROR);
            return false;
        }
    }

    // Đọc ghi thí sinh
    public List<ThiSinh> docThiSinh() {
        List<ThiSinh> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("thisinh.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(";");
                ThiSinh ts = new ThiSinh();
                ts.setMaThiSinh(Integer.parseInt(result[0]));
                ts.setTenThiSinh(result[1]);
                ts.setMaQueQuan(Integer.parseInt(result[2]));
                ts.setNgaySinh(new MyDate(result[3]));
                ts.setGioiTinh(Boolean.parseBoolean(result[4]));
                ts.setDiemToan(Float.parseFloat(result[5]));
                ts.setDiemLy(Float.parseFloat(result[6]));
                ts.setDiemHoa(Float.parseFloat(result[7]));

                list.add(ts);
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Đọc dữ liệu thất bại");
        }
        return list;
    }

    public boolean ghiThiSinh(List<ThiSinh> list) {
        try {
            FileWriter fw = new FileWriter("thisinh.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (ThiSinh ts : list) {
                String line = ts.getMaThiSinh()
                        + ";" + ts.getTenThiSinh() + ";"
                        + ts.getMaQueQuan() + ";" + ts.getNgaySinh().toString()
                        + ";" + ts.getGioiTinh() + ";" + ts.getDiemToan() + ";"
                        + ts.getDiemLy() + ";" + ts.getDiemHoa() + "\n";
                bw.write(line);
            }
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ghi dữ liệu thất bại", "", JOptionPane.ERROR);
            return false;
        }
    }

    public boolean ghiThemThiSinh(List<ThiSinh> list, ThiSinh thiSinh) {
        try {
            FileWriter fw = new FileWriter("thisinh.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            String line = thiSinh.getMaThiSinh()
                    + ";" + thiSinh.getTenThiSinh() + ";"
                    + thiSinh.getMaQueQuan() + ";" + thiSinh.getNgaySinh().toString()
                    + ";" + thiSinh.getGioiTinh() + ";" + thiSinh.getDiemToan() + ";"
                    + thiSinh.getDiemLy() + ";" + thiSinh.getDiemHoa() + "\n";
            bw.write(line);
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ghi thêm thí sinh thất bại", "", JOptionPane.ERROR);
            return false;
        }
    }
}
