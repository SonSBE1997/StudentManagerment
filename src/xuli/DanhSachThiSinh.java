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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DanhSachThiSinh {

    private List<ThiSinh> listTS = new ArrayList<>();   //danh sách thí sinh
    private DanhSachTinhThanh dstt = new DanhSachTinhThanh(); //danh sách tỉnh thành
    private Data data = new Data();

    public DanhSachThiSinh() {
//        this.docDuLieu();
    }

    //Getter
    //Trả về danh sách thí sinh
    public List<ThiSinh> getListTS() {
        return listTS;
    }

    //Trả về đối tượng danh sách tỉnh thành
    public DanhSachTinhThanh getDstt() {
        return dstt;
    }

    // Method
    //Hàm đọc dữ liệu từ file   
    public void docDuLieu() {
        listTS = data.docThiSinh();
    }

    //Ghi dữ liệu vào file
    private void ghiDuLieu() {
        data.ghiThiSinh(listTS);
    }

    // Hàm tìm kiếm theo mã thí sinh + msTS : mã thí sinh cần tìm kiếm
    public ThiSinh timTheoMaThiSinh(int maTS) {
        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS) {
                return ts;
            }
        }
        return null;
    }

    //Thêm thí sinh
    public boolean themThiSinh(int maTS, String tenTS, String que, MyDate namSinh,
            boolean gioiTinh, float toan, float ly, float hoa) throws MyException {
        if (timTheoMaThiSinh(maTS) != null) {
            throw new MyException("maTS");
        }
        int maQue = dstt.timTinhThanhTheoTen(que).getMaTinh();
        ThiSinh ts = new ThiSinh(maTS, tenTS, maQue, namSinh, gioiTinh, toan, ly, hoa);
        listTS.add(ts);
        data.ghiThemThiSinh(listTS, ts);
        return true;
    }

    //Sửa thông tin thí sinh
    //Sửa mã thí sinh + mats1 : mã ban đầu ,mats2 : mã sửa
    public boolean suaMaThiSinh(int maTS1, int maTS2) throws MyException {
        if (timTheoMaThiSinh(maTS2) != null) {
            throw new MyException("maTS");
        }
        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS1) {
                ts.setMaThiSinh(maTS2);
                ghiDuLieu();
                return true;
            }
        }
        return false;
    }

    //Sửa tên thí sinh
    public boolean suaTenThiSinh(int maTS, String tenTS) {
        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS) {
                ts.setTenThiSinh(tenTS);
                ghiDuLieu();
                return true;
            }
        }
        return false;
    }

    // sửa quê thí sinh
    public boolean suaQueThiSinh(int maTS, String que) {
//        if (dstt.timTinhThanhTheoTen(que) == null) {
//            JOptionPane.showMessageDialog(null, "Tỉnh thành không tồn tại");
//            return false;
//        }

        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS) {
                int maQue = dstt.timTinhThanhTheoTen(que).getMaTinh();
                ts.setMaQueQuan(maQue);
                ghiDuLieu();
                return true;
            }
        }
        return false;
    }

    //Sửa ngày sinh 
    public boolean suaNgaySinhThiSinh(int maTS, MyDate ngaySinh) {
        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS) {
                ts.setNgaySinh(ngaySinh);
                ghiDuLieu();
                return true;
            }
        }
        return false;
    }

    //Sửa giới tính
    public boolean suaGioiTinhThiSinh(int maTS, boolean gt) {
        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS) {
                ts.setGioiTinh(gt);
                ghiDuLieu();
                return true;
            }
        }
        return false;
    }

    //Sửa điểm toán
    public boolean suaDiemToan(int maTS, float diem) {
        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS) {
                ts.setDiemToan(diem);
                ghiDuLieu();
                return true;
            }
        }
        return false;
    }

    public boolean suaDiemLy(int maTS, float diem) {
        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS) {
                ts.setDiemLy(diem);
                ghiDuLieu();
                return true;
            }
        }
        return false;
    }

    public boolean suaDiemHoa(int maTS, float diem) {
        for (ThiSinh ts : listTS) {
            if (ts.getMaThiSinh() == maTS) {
                ts.setDiemHoa(diem);
                ghiDuLieu();
                return true;
            }
        }
        return false;
    }

    //Xóa thí sinh
    public boolean xoaThiSinh(int maTS) {
        ThiSinh ts = timTheoMaThiSinh(maTS);
        listTS.remove(ts);
        ghiDuLieu();
        return true;
    }

    //Lọc thí sinh theo tiêu chí ?
    //Lọc thí sinh theo quê quán
    public List<ThiSinh> locTheoQueQuan(String que) {
        List<ThiSinh> dsts = new ArrayList<>();
        for (ThiSinh ts : listTS) {
            String queQuan = dstt.timTinhThanhTheoMa(ts.getMaQueQuan()).getTenTinh().toLowerCase();
            if (queQuan.contains(que.toLowerCase())) {
                dsts.add(ts);
            }
        }
        return dsts;
    }

    //Lọc theo tên 
    public List<ThiSinh> locTheoTen(String ten) {
        List<ThiSinh> dsts = new ArrayList<>();
        for (ThiSinh ts : listTS) {
            if (ts.getTenThiSinh().toLowerCase().endsWith(ten.toLowerCase())) {
                dsts.add(ts);
            }
        }
        return dsts;
    }

    //Lọc theo ngáy sinh
    public List<ThiSinh> locTheoNgaySinh(String ngaySinh) {
        List<ThiSinh> dsts = new ArrayList<>();
        for (ThiSinh ts : listTS) {
            if (ts.getNgaySinh().toString().equals(ngaySinh)) {
                dsts.add(ts);
            }
        }
        return dsts;
    }

    //Lọc theo giới tính
    public List<ThiSinh> locTheoGioiTinh(String gioiTinh) {
        List<ThiSinh> dsts = new ArrayList<>();
        for (ThiSinh ts : listTS) {
            boolean gt = gioiTinh.toLowerCase().equals("nam");
            if (ts.getGioiTinh() == gt) {
                dsts.add(ts);
            }
        }
        return dsts;
    }

    //Lọc theo mã thí sinh
    public List<ThiSinh> locTheoMaThiSinh(String maTS) {
        List<ThiSinh> dsts = new ArrayList<>();
        for (ThiSinh ts : listTS) {
            String ma = ts.getMaThiSinh() + "";
            if (ma.contains(maTS)) {
                dsts.add(ts);
            }
        }
        return dsts;
    }

    //Lọc theo mã thí sinh và quê quán
    public List<ThiSinh> locTheoMaThiSinhVaQueQuan(String maTS, String que) {
        List<ThiSinh> dsts = new ArrayList<>();
        for (ThiSinh ts : listTS) {
            String queQuan = dstt.timTinhThanhTheoMa(ts.getMaQueQuan()).getTenTinh().toLowerCase();
            String ma = ts.getMaThiSinh() + "";
            if (queQuan.contains(que.toLowerCase()) && ma.contains(maTS)) {
                dsts.add(ts);
            }
        }
        return dsts;
    }

    //Sắp xếp
    public List<ThiSinh> sapXepGiamTheoMaThiSinh() {
        List<ThiSinh> dsts = new ArrayList<>();
        for (ThiSinh ts : listTS) {
            dsts.add(ts);
        }
        dsts.sort(new Comparator<ThiSinh>() {
            @Override
            public int compare(ThiSinh o1, ThiSinh o2) {
                if (o1.getMaThiSinh() == o2.getMaThiSinh()) {
                    return 0;
                }
                if (o1.getMaThiSinh() < o2.getMaThiSinh()) {
                    return 1;
                }
                return -1;
            }
        });
        return dsts;
    }

    public List<ThiSinh> sapXepGiamTheoTongDiem() {
        List<ThiSinh> dsts = new ArrayList<>();
        for (ThiSinh ts : listTS) {
            dsts.add(ts);
        }
        dsts.sort(new Comparator<ThiSinh>() {
            @Override
            public int compare(ThiSinh o1, ThiSinh o2) {
                float ts1 = o1.getDiemToan() + o1.getDiemLy() + o1.getDiemHoa();
                float ts2 = o2.getDiemToan() + o2.getDiemLy() + o2.getDiemHoa();
                if (ts1 == ts2) {
                    return 0;
                }
                if (ts1 < ts2) {
                    return 1;
                }
                return -1;
            }
        });
        return dsts;
    }

    public List<ThiSinh> soThiSinh(String tongDiem) {
        List<ThiSinh> dsts = new ArrayList<>();
        float tong = Float.parseFloat(tongDiem);
        for (ThiSinh ts : listTS) {
            float tong1 = ts.getDiemToan() + ts.getDiemLy() + ts.getDiemHoa();
            if (tong1 >= tong) {
                dsts.add(ts);
            }
        }
        return dsts;
    }
}
