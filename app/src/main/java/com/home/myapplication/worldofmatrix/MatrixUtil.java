package com.home.myapplication.worldofmatrix;

/**
 * Created by SONY on 21.02.2016.
 */
public  class  MatrixUtil  {

    public static final int LENGTH_ARRAY = 9;
    public static final int LENGTH_MATRIX = 3;


    public static int[] add(int[] et1, int[] et2) {

        int[] addResult = new int[LENGTH_ARRAY];

        for (int i = 0; i < LENGTH_ARRAY; i++) {
            addResult[i] = et1[i] + et2[i];
        }

        return addResult;
    }


    public static int[] subtract(int[] editNumArrayA, int[] editNumArrayB) {

        int[] subtractResult = new int[LENGTH_ARRAY];

        for (int i = 0; i < LENGTH_ARRAY; i++) {
            subtractResult[i] = editNumArrayA[i] - editNumArrayB[i];
        }

        return subtractResult;
    }


    public static int[][] conversionArrayToMatrix(int[] editTextIntValues) {

        int[][] matrix = new int[LENGTH_MATRIX][LENGTH_MATRIX];

        for (int i = 0; i < LENGTH_MATRIX; i++) {
            for (int j = 0; j < LENGTH_MATRIX; j++) {

                matrix[0][j] = editTextIntValues[j];

                matrix[1][j] = editTextIntValues[j + LENGTH_MATRIX];

                matrix[2][j] = editTextIntValues[j + LENGTH_MATRIX * 2];

            }
        }

        return matrix;
    }


    public static int[][] multi(int[][] matrixA, int[][] matrixB) {

        int [][] matrix = new int[LENGTH_MATRIX][LENGTH_MATRIX];

        for (int i = 0; i < LENGTH_MATRIX; i++) {
            for (int j = 0; j < LENGTH_MATRIX; j++) {
                for (int k = 0; k < LENGTH_MATRIX; k++) {

                    matrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return matrix;
    }


    public static int[] conversionMatrixToArray(int[][] matrix) {

        int[] conversionArray = new int[LENGTH_ARRAY];

        for (int i = 0; i < LENGTH_ARRAY; i++) {

            conversionArray[i] = matrix[i / LENGTH_MATRIX] [i % LENGTH_MATRIX];
        }

        return conversionArray;
    }
}

