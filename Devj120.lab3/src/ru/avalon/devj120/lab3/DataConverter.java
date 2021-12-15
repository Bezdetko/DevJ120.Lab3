/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.avalon.devj120.lab3;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author bezdetk0@mail.ru
 */
public class DataConverter implements IFileConverter{

    @Override
    public String toBinary(String inputFileName, String outputFileName, String charSet) {
        final int BUFFERSIZE = 1024;
        String string = "";
        Charset charset = Charset.forName(charSet);
        char[] buffer = new char[BUFFERSIZE];
        byte[] bytes;
        String binarystring = "";
        
        try (FileReader inputFile = new FileReader(inputFileName)){
           while ( inputFile.read(buffer) != -1){
                   string += new String(buffer);
                   buffer = new char[BUFFERSIZE];
           }
           
//           System.out.println(string);
           

           
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
        
        
        try {
            bytes = string.getBytes("UTF-8");
      
        StringBuilder binary = new StringBuilder();
        for(byte b: bytes){
            int val =b;
            for(int i = 0; i < 8; i++){
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            
            
        }
        
          } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DataConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
//            System.out.println(binarystring);
        
        File outputFile = new File(outputFileName);
        try {
            outputFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        try (FileWriter fw = new FileWriter(outputFile, false)){
            fw.write(binarystring);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        

//        byte[] textByte = new byte[];
//        for(int i=0 ; i<bytes.length ; i++){
//            textByte[i] = Byte.parseByte(binary[i], 2);
//        }
//        
//        System.out.println(new String(textByte, charset));
        
    return outputFileName;
    }

    @Override
    public String toText(String inputFileName, String outputFileName, String charSet) {
        
        return null;
        
    }

    @Override
    public double getSum(String fileName) throws ConverterException {
        
        return 0;
        
    }
    
    
    
}