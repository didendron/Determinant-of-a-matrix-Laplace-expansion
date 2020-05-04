package com.mycompany;

import java.util.Scanner;

public class Determinant {


    public static void main(String[] args) {
        int degreeMatrix=0;
        double determinant=0;
        double[][] matrix=null;
        Scanner in=new Scanner(System.in);
        do {
            System.out.println("Enter degree of a matrix(greater or equals 2):");
            degreeMatrix=in.nextInt();
        }while (degreeMatrix<2);

        matrix=new double[degreeMatrix][degreeMatrix];
        int counter=0;
        do{
            String question=String.format("Enter a matrix(%d numbers): ",degreeMatrix*degreeMatrix);
            System.out.println(question);
            for(int i=0;i<degreeMatrix;i++){
                for(int j=0;j<degreeMatrix;j++){
                    matrix[i][j]=in.nextDouble();
                    counter++;
                }
            }
        }while (degreeMatrix*degreeMatrix!=counter);

        determinant=countDeterminant(degreeMatrix,matrix);
        System.out.println("Determinant is: "+determinant);

    }

    //count determinant, using Laplace expansion to first row
    private static double countDeterminant(int degreeMatrix, double[][] matrix){
        double sum=0;
        if (degreeMatrix==2){
            return matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
        }
        else{
            for(int jColumn=0;jColumn<degreeMatrix;jColumn++){
                sum+= raiseMinusOneToN(jColumn+2)*matrix[0][jColumn]*countDeterminant(degreeMatrix-1, createMinor(matrix,jColumn));
            }
            return sum;
        }
    }
    //minor after delete first row and j column in matrix
    private static double[][] createMinor(double[][] matrix, int jColumn){
        double[][] minor=new double[matrix.length-1][matrix.length-1];
        for(int i=0;i<matrix.length-1;i++){
            for(int j=0,k=0;j<matrix.length-1;j++,k++){
                if(k==jColumn)k++;
                minor[i][j]=matrix[i+1][k];
            }
        }
        return minor;
    }
    //-1 to to the power of n
    private static int raiseMinusOneToN(int n){
        int x=-1;
        for(int i=1;i<n;i++){
            x=(-1)*x;
        }
        return x;
    }
}
