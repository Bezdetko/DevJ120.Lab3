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
        
        
        try {
            
            
            final int BUFFERSIZE = 1024;
            String string = "";
            Charset charset = Charset.forName(charSet);
            char[] buffer = new char[BUFFERSIZE];
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
            
            byte[] bytes = string.getBytes(charSet);
            String[] binary = new String[bytes.length];
            String binarystring = "";
            for(int i=0 ; i<bytes.length ; i++){
                binary[i]=String.format("%8s", Integer.toBinaryString(bytes[i])).replace(" ", "0");
                binarystring += binary[i];
                
//                binary[i]=Integer.toBinaryString(bytes[i]);                
//                binarystring += binary[i];
                
            } 
            System.out.println(binarystring);

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

        } catch (UnsupportedEncodingException ex) {
            System.err.println(ex.getMessage());
        } 
        return outputFileName;
    }

    @Override
    public String toText(String inputFileName, String outputFileName, String charSet) {
        
        final int BUFFERSIZE = 8;
            String  binaryString = "";
            Charset charset = Charset.forName(charSet);
            char[] buffer = new char[BUFFERSIZE];
            
            try (FileReader inputFile = new FileReader(inputFileName)){
                while ( inputFile.read(buffer) != -1){
                    binaryString =  binaryString + new String(buffer) + "-" ;
                    buffer = new char[BUFFERSIZE];
                }
                } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
             
           String [] bytesString = binaryString.split("-");
           byte [] textByte = new byte[bytesString.length];
           for (int i=0; i<bytesString.length; i++){
               textByte[i] = Byte.parseByte(bytesString[i], 2);
           }
           



        
        String original;
//        try {
            original = new String(textByte, charset);
            System.out.println(original);
//        } catch (UnsupportedEncodingException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
 
File outputFile = new File(outputFileName);
try {
    outputFile.createNewFile();
} catch (IOException ex) {
    System.out.println(ex.getMessage());
}

try (FileWriter fw = new FileWriter(outputFile, false)){
    fw.write(original);
} catch (IOException ex) {
    System.out.println(ex.getMessage());
}


        return outputFileName;
    }


        
//        return null;
//        
//    }

    @Override
    public double getSum(String fileName) throws ConverterException {
        
        return 0;
        
    }
    
    
    
}
