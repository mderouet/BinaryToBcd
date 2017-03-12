/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytobcd;

import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author warp
 */
public class BinaryToBcd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Original array");
        //Initial classic binary array
        int[] binaryRepresentation = new int[]{0, 0, 0, 1, 1, 0, 1, 0};
        //Show binary array
        showIntArray(binaryRepresentation);
        //Getting decimal value of this binary representation
        int decimalValueStandardBinary = valueStandardBinary(binaryRepresentation);
        
        System.out.println("Converted array");
        //Getting BCD equivalent and display in console
        showIntArray(decimalToBCD(decimalValueStandardBinary));
    }

    private static int[] decimalToBCD(int decimalValue) {
        if (decimalValue >= 99) {
            return new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        }
        if (decimalValue < 10) {
            int[] firstPart = {0, 0, 0, 0};
            return combineArray(firstPart, decimalIntToQuart(getIntValueAtIndex(decimalValue, 1, 2)));
        }
        return combineArray(decimalIntToQuart(getIntValueAtIndex(decimalValue, 0, 1)), decimalIntToQuart(getIntValueAtIndex(decimalValue, 1, 2)));
    }

    private static int[] combineArray(int[] array1, int[] array2) {
        int[] bcdArray = new int[8];
        System.arraycopy(array1, 0, bcdArray, 0, array1.length);
        System.arraycopy(array2, 0, bcdArray, array1.length, array2.length);
        return bcdArray;
    }
    
    private static int getIntValueAtIndex(int value,int indexBegin, int indexEnd)
    {
        return Integer.parseInt(Integer.toString(value).substring(indexBegin,indexEnd));
    }
    
    private static int[] decimalIntToQuart(int decimalValue) {
        switch (decimalValue) {
            case 0:
                return new int[]{0, 0, 0, 0};
            case 1:
                return new int[]{0, 0, 0, 1};
            case 2:
                return new int[]{0, 0, 1, 0};
            case 3:
                return new int[]{0, 0, 1, 1};
            case 4:
                return new int[]{0, 1, 0, 0};
            case 5:
                return new int[]{0, 1, 0, 1};
            case 6:
                return new int[]{0, 1, 1, 0};
            case 7:
                return new int[]{0, 1, 1, 1};
            case 8:
                return new int[]{1, 0, 0, 0};
            case 9:
                return new int[]{1, 0, 0, 1};
            default:
                return new int[]{0, 0, 0, 0};
        }
    }

    //Display values of a binary array
    private static void showIntArray(int[] arrayToShow) {
        for (int i = 0; i < arrayToShow.length; i++) {
            System.out.print(arrayToShow[i]);
        }
        System.out.println();
    }

    private static int valueStandardBinary(int[] binaryValue) {
        int decimalBinaryValue = 0;
        if (binaryValue.length == 8) {
            for (int i = 0; i < 8; i++) {
                if (binaryValue[i] == 1) {
                    decimalBinaryValue = decimalBinaryValue + binaryStandardValue(i);
                }
            }
        }
        return decimalBinaryValue;
    }

    //Return the value of a quart
    private static int valueQuart(int[] quartValue) {
        int decimalQuartValue = 0;
        if (quartValue.length == 4) {
            for (int i = 0; i < 4; i++) {
                if (quartValue[i] == 1) {
                    decimalQuartValue = decimalQuartValue + binaryQuartValue(i);
                }
            }
        }
        return decimalQuartValue;
    }

    private static int binaryStandardValue(int binaryIndex) {
        switch (binaryIndex) {
            case 0:
                return 128;
            case 1:
                return 64;
            case 2:
                return 32;
            case 3:
                return 16;
            case 4:
                return 8;
            case 5:
                return 4;
            case 6:
                return 2;
            case 7:
                return 1;

            default:
                return 0;
        }
    }

    //Return the decimal value by index of an integer binary
    private static int binaryQuartValue(int binaryIndex) {
        switch (binaryIndex) {
            case 0:
            case 4: {
                return 8;

            }
            case 1:
            case 5: {
                return 4;
            }
            case 2:
            case 6: {
                return 2;
            }
            case 3:
            case 7: {
                return 1;

            }

            default:
                return 0;
        }
    }
}
