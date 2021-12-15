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
       dc.toBinary("original.txt", "Binary.txt", "ASCII");
        dc.toText("Binary.txt", "redable.txt", "ASCII");
    }
    
}
