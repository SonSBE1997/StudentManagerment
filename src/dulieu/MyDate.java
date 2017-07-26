/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulieu;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import xuli.MyException;

/**
 *
 * @author SBE
 */
public final class MyDate extends Date {

    private String date;

    public MyDate() {
        super();
    }

    /**
     * Constuctor
     *
     * @param date có dạng dd/MM/yyyy
     */
    public MyDate(String date) {
        this.date = date;
        String result[] = date.split("/");
        this.setNgay(Integer.parseInt(result[0]));
        this.setThang(Integer.parseInt(result[1]));
        this.setNam(Integer.parseInt(result[2]));
    }

    //chuyen tu dang dd/MM/yyyy sang dd-MM-yyyy
    public String formatDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this);
    }

    public void setNgay(int date) {
        super.setDate(date);
    }

    public void setThang(int month) {
        super.setMonth(month - 1);
    }

    public void setNam(int year) {
        super.setYear(year - 1900);
    }

    @Override
    public String toString() {
        return date;
    }

    //Kiểm tra ngày
    public static boolean checkDate(String date) throws MyException {
        if (date.matches("([0-9]{1,2})/([0-9]{1,2})/([0-9]{1,4})") == false) {
            throw new MyException("date1");
        }

        String[] arr = date.split("/");
        int day = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int year = Integer.parseInt(arr[2]);

        if (month > 12) {
            throw new MyException("date2");
        } else if (month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12) {
            if (day > 31) {
                throw new MyException("date3");
            }
        } else if ((month == 4 || month == 6 || month == 9 || month
                == 11)) {
            if (day > 30) {
                throw new MyException("date3");
            }
        } else if (month == 2) {
            if (year % 4 == 0 || year % 100 == 0) {
                if (day > 29) {
                    throw new MyException("date3");
                }
            } else if (day > 28) {
                throw new MyException("date3");
            }
        }
        Date date1 = new Date();
        int yearNow = 1900 + date1.getYear();
        if (yearNow - year < 18) {
            throw new MyException("age1");
        }
        if (yearNow - year < 0) {
            throw new MyException("age2");
        }
        if (yearNow - year > 150) {
            throw new MyException("age3");
        }

        return true;
    }

    /**
     * hàm so sánh 2 ngày
     *
     * @param date1 date có dạng dd/MM/yyyy
     * @param date2 date có dạng dd-MM-yyyy
     * @return
     */
    public static boolean compareDate(String date1, String date2) {
        String result1[] = date1.split("/");
        int day1 = Integer.parseInt(result1[0]);
        int thang1 = Integer.parseInt(result1[1]);
        int nam1 = Integer.parseInt(result1[2]);
        String result2[] = date2.split("-");
        int day2 = Integer.parseInt(result2[0]);
        int thang2 = Integer.parseInt(result2[1]);
        int nam2 = Integer.parseInt(result2[2]);
        if (day1 != day2) {
            return false;
        }
        if (thang1 != thang2) {
            return false;
        }
        return nam1 == nam2;
    }
}
