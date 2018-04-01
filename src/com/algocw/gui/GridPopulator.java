package com.algocw.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class GridPopulator {

    // [row][col]
    public static int [][] matrixArray = {

            { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
            { 3 , 3 , 0 , 3 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
            { 3 , 3 , 3 , 3 , 3 , 3 , 0 , 0 , 1 , 2 , 2 , 2 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
            { 3 , 3 , 3 , 3 , 3 , 3 , 0 , 0 , 1 , 2 , 2 , 2 , 2 , 1 , 0 , 0 , 0 , 0 , 0 , 0 },
            { 0 , 0 , 3 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 2 , 2 , 2 , 1 , 0 , 0 , 1 , 1 , 0 , 0 },
            { 0 , 3 , 3 , 0 , 1 , 1 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 0 , 1 , 1 , 1 , 0 , 0 },
            { 3 , 1 , 0 , 0 , 1 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 0 , 0 , 0 },
            { 0 , 1 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 3 , 3 },
            { 0 , 0 , 1 , 2 , 2 , 1 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 3 , 3 , 3 },
            { 0 , 1 , 2 , 2 , 2 , 2 , 1 , 1 , 0 , 0 , 0 , 0 , 3 , 3 , 3 , 3 , 3 , 3 , 3 , 0 },
            { 0 , 1 , 0 , 1 , 1 , 1 , 2 , 1 , 3 , 0 , 0 , 0 , 3 , 3 , 3 , 3 , 0 , 0 , 0 , 0 },
            { 0 , 1 , 1 , 0 , 0 , 0 , 3 , 3 , 3 , 3 , 0 , 0 , 3 , 3 , 3 , 0 , 0 , 0 , 0 , 0 },
            { 0 , 0 , 3 , 3 , 3 , 3 , 3 , 3 , 3 , 3 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
            { 3 , 3 , 3 , 3 , 3 , 3 , 3 , 3 , 3 , 0 , 0 , 4 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 4 },
            { 0 , 0 , 3 , 3 , 3 , 3 , 0 , 0 , 0 , 1 , 1 , 4 , 4 , 0 , 0 , 0 , 0 , 0 , 0 , 4 },
            { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 4 , 4 , 4 , 0 , 0 , 4 , 4 , 4 },
            { 0 , 1 , 1 , 1 , 1 , 1 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 4 , 4 , 4 , 4 , 4 , 4 , 4 },
            { 1 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 4 , 4 , 4 , 4 , 4 , 4 , 4 },
            { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 4 , 4 , 4 , 4 , 4 , 4 , 4 , 4 , 4 },
            { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 4 , 4 , 4 , 4 , 4 , 4 , 4 , 4 , 4 }

    };



    public static void main(String[] args) {

    }
















    static String  filename="gridData.txt";

    //this method will read the data from the file
    public static String readFileData() throws IOException {
        String everything;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            everything = sb.toString();

        } finally {
            br.close();
        }

        return everything;

    }




}
