/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ru.avalon.devj120.lab3;

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
       dc.toBinary("original2.txt", "Binary2.txt", "UTF-8");
//        dc.toText("Binary2.txt", "redable1.txt", "UTF-8");
        
//String substr = "1111111111111111111111111101000111111111111111111111111110001011" ;
//                int count = Integer.parseInt(substr, 2);
//                System.out.println(count);

        
    }
    
}
