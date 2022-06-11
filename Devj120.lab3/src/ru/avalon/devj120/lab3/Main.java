/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ru.avalon.devj120.lab3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bezdetk0@mail.ru
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       DataConverter dc = new DataConverter();
       dc.toBinary("original.txt", "Binary.txt", "UTF-8");
       dc.toText("Binary.txt", "redable1.txt", "UTF-8");
        try {
            double d = dc.getSum("original6.txt");
            System.out.println(String.valueOf("сумма всех чисел встречающихся в тексте: " + d));
        } catch (ConverterException ex) {
            System.err.println(ex.getMessage());
        }

        
        
    }
    
}
