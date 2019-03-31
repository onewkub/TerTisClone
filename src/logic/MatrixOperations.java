package logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixOperations {

    private static int a = 0;

    public static boolean intersects(int[][] matrix, int[][] brick, int x, int y) {
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[i].length; j++) {
//                System.out.printf("%d ", martrix[i][j]);
                int targetX = x + i;
                int targetY = y + j;
//                System.out.printf("tx:%d ty:%d\n", targetX, targetY);
//                if(!outOfBounds(matrix, targetY, targetX)){
//                    if(matrix[targetY][targetX] != 0 && brick[j][i] != 0){
//                        System.out.println("It crash "+a);
//                        a++;
//                        return true;
//                    }
//                }

                if (brick[j][i] != 0 && (outOfBounds(matrix, targetY, targetX) || matrix[targetY][targetX] != 0)) {
//                    for(int o = 0; o < matrix.length; o++) {
//                        for (int h = 0; h < matrix[o].length; h++) {
//                            System.out.print(matrix[o][h]);
//                        }
//                        System.out.println();
//                    }
//                    System.out.println("-------------------------------");
////                    System.out.printf("%d %d\n", brick.length, brick[i].length);
////                    System.out.println("y = "+y);
//                    System.out.println(brick[j][i]);
//                    if (!outOfBounds(matrix, targetY, targetX)) {
//                        System.out.println(targetY + " " + targetX);
//
//                    }
//                    System.out.println("------------------------");
                    return true;
                }
            }
//            System.out.println();
        }

        return false;
    }

    private static boolean outOfBounds(int[][] matrix, int targetY, int targetX) {
        if (targetX >= 0 && targetY >= 0 && targetY < matrix.length && targetX < matrix[targetY].length) {

            return false;
        }
//        System.out.println("on the border");
        return true;
    }

    public static int[][] merge(int[][] filledFields, int[][] brick, int x, int y) {
        int[][] copy = copy(filledFields);

        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[i].length; j++) {
                int targetY = y + j;
                int targetX = x + i;
                if (brick[j][i] != 0) {
//                    System.out.println("targetY = " + targetY);
                    copy[targetY - 1][targetX] = brick[j][i];

                }
            }
        }

        return copy;
    }

    public static int[][] copy(int[][] original) {
        int[][] myInt = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            int[] aMatrix = original[i];
            int aLength = aMatrix.length;
            myInt[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
        }
        return myInt;
    }

    static ClearRow checkRemoving(int[][] matrix) {
        int[][] tmp = new int[matrix.length][matrix[0].length];
        List<Integer> clearedRows = new ArrayList<>();
        Deque<int[]> newRows = new ArrayDeque<>();
        for (int i = 0; i < matrix.length; i++) {
            int[] tmpRow = new int[matrix[i].length];
            boolean rowToClear = true;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rowToClear = false;
                }
                tmpRow[j] = matrix[i][j];
            }
            if (rowToClear) {
                clearedRows.add(i);
            } else {
                newRows.add(tmpRow);
            }
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            int[] row = newRows.pollLast();
            if (row != null) {
                tmp[i] = row;
            } else {
                break;
            }
        }

        int scoreBonus = 50 * clearedRows.size() * clearedRows.size();
        return new ClearRow(clearedRows.size(), tmp, scoreBonus);

    }

    public static List<int[][]> deepCopyList(List<int[][]> list) {
        return list.stream().map(MatrixOperations::copy).collect(Collectors.toList());
    }
}
