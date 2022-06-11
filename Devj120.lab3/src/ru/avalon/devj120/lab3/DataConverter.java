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
public class DataConverter implements IFileConverter {

    @Override
    public String toBinary(String inputFileName, String outputFileName, String charSet) {

        try {

            final int BUFFERSIZE = 1024;
            String string = "";
            Charset charset = Charset.forName(charSet);
            char[] buffer = new char[BUFFERSIZE];
            try (FileReader inputFile = new FileReader(inputFileName)) {
                int i;
                while ((i = inputFile.read(buffer)) != -1) {

                    if (i < 1024) {
                        buffer = Arrays.copyOf(buffer, i);
                    }
                    string += new String(buffer);
//                    buffer = new char[BUFFERSIZE];
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
//            int formatLength;
            for (int i = 0; i < bytes.length; i++) {
//                if (bytes[i] == 0) 
//                formatLength = 8;
//            }
//                else if (Integer.toBinaryString(bytes[i]).length() % 8 == 0){
//                formatLength = Integer.toBinaryString(bytes[i]).length();}
//                else {
//                     formatLength = (Integer.toBinaryString(bytes[i]).length())/8 * 8 + 8;
//                }

                binary[i] = String.format("%8s", Integer.toBinaryString(bytes[i])).replace(" ", "0");
                binarystring += " " + binary[i];

//                System.out.println(bytes[i] + "  " + binary[i] + "  " + Integer.toBinaryString(bytes[i]).length());
//                System.out.println(binary[i].length() + "   " + binary[i] + "   " + binary[i].length()/8 + "  " + binary[i].length()%8);

//                binary[i]=Integer.toBinaryString(bytes[i]);                
//                binarystring += binary[i];
            }
            binarystring = binarystring.trim();
//            System.out.println(binarystring);

            File outputFile = new File(outputFileName);
            if (!outputFile.exists()) {
                try {
                    outputFile.createNewFile();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            try (FileWriter fw = new FileWriter(outputFile, false)) {
                fw.write(binarystring);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
                    System.out.println("Текст в двоичном коде из файла " + inputFileName + ":" + "\n"+ binarystring + "\n");

        } catch (UnsupportedEncodingException ex) {
            System.err.println(ex.getMessage());
        }
        
        return outputFileName;
    }

    @Override
    public String toText(String inputFileName, String outputFileName, String charSet) {

        final int BUFFERSIZE = 1024;
        String binaryString = "";
        Charset charset = Charset.forName(charSet);
        char[] buffer = new char[BUFFERSIZE];

        try (FileReader inputFile = new FileReader(inputFileName)) {
            int i;
            while ((i = inputFile.read(buffer)) != -1) {
                if (i < BUFFERSIZE) {
                    buffer = Arrays.copyOf(buffer, i);
                }
                binaryString += new String(buffer);
//                    binaryString =  binaryString + new String(buffer) + "-" ;
//                    buffer = new char[BUFFERSIZE];
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String[] bytesString = binaryString.split(" ");
        byte[] textByte = new byte[bytesString.length];
        int[] textInt = new int[binaryString.length()];
        for (int i = 0; i < bytesString.length; i++) {
            try{
            textByte[i] = Byte.parseByte(bytesString[i], 2);
            }
            catch (NumberFormatException nfe){
                System.err.println(nfe.getMessage());
            }
        }

        String original;
//        try {
        original = new String(textByte, charset);
//        System.out.println("Текст из двоичного кода:" + "\n"+ original);
//        } catch (UnsupportedEncodingException ex) {
//            System.out.println(ex.getMessage());
//        }
//        

        File outputFile = new File(outputFileName);
        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try (FileWriter fw = new FileWriter(outputFile, false)) {
            fw.write(original);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Текст из преобразованный из двоичного кода из файла " + inputFileName +":" + "\n"+ original + "\n");
        return outputFileName;
    }

//        return null;
//        
//    }
    @Override
    public double getSum(String fileName) throws ConverterException {
    if (fileName == null) throw new ConverterException("fileName == null ");
        double sum = 0;
        final int BUFFERSIZE = 1024;
        char[] buffer = new char[BUFFERSIZE];
        String inputText = "";
        try (FileReader inputFile = new FileReader(fileName)){
                int i;
                while ((i = inputFile.read(buffer)) != -1) {
                    if (i < 1024) {
                        buffer = Arrays.copyOf(buffer, i);
                    }
                    inputText += new String(buffer);
                }
        if (inputText.isEmpty()) throw new ConverterException("fileName не содержит данных");
        String[] words = inputText.split("\\s");
        for (String w: words){
            if (w.matches("^[0-9]*[.]?[0-9]+$")) {
                sum+= Double.parseDouble(w);
            }
        }
                
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sum;
        

    }

}
