package com.algocw.algo;

public class PathFinder {

    private int [][] finalFinalCoordinatesArray;

    public static void main(String[] args) {
        PathFinder pf= new PathFinder();
        pf.setArray();

    }


    public void setArray(){

        finalFinalCoordinatesArray=new int[][] {{1,2},{1,3},{1,4},{1,5},{2,5},{2,6},{2,7}};

    }

    public int [][] find(int [][] matrix){

        return this.finalFinalCoordinatesArray;
    }

    public int [][] getFinalCoordinatesArray(){
        return this.finalFinalCoordinatesArray;

    }

}
