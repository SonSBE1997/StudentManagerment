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
public class MyException extends Exception {

    //Attribute - thông điệp gửi đến để xử lí
    private String message;

    /**
     * Constructor
     */
    public MyException() {
        super();
    }

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message + "";
    }

}
