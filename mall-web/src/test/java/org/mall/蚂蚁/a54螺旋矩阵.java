//package org.mall.蚂蚁;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName a54螺旋矩阵
// * @Description TODO
// * @Author Jay
// * @Date 2021/4/15 2:33
// * @Version 1.0
// */
//public class a54螺旋矩阵 {
////    给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
////
////
////
////    示例 1：
////
////    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
////    输出：[1,2,3,6,9,8,7,4,5]
//
//    int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
//
//    @Test
//    public void test() {
//        List<Integer> ans = spiralOrder(matrix);
//        System.out.println(ans.toString());
//    }
//
//    public List<Integer> spiralOrder(int[][] matrix) {
//        List<Integer> ans = new ArrayList<>();
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
//            return ans;
//        }
//        int xLen = matrix.length;
//        int yLen = matrix[0].length;
//        int xf = 0, yf = 0, xc = yLen - 1, yc = xLen - 1;
//        while (xf <= xc && yf <= yc){
//            for (int i = xf; i <= xc; i++) {
//                ans.add(matrix[yf][i]);
//            }
//            for (int i = yf+1; i <= yc; i++) {
//                ans.add(matrix[i][xc]);
//            }
//            if(xf < xc && yf < yc){
//                for (int i = xc-1; i > xf; i--) {
//                    ans.add(matrix[yc][i]);
//                }
//                for (int i = yc-1; i > yf; i--) {
//                    ans.add(matrix[i][xf]);
//                }
//            }
//            xf++;
//            xc--;
//            yf++;
//            yc--;
//        }
//        return ans;
//    }
//
//    public List<Integer> spiralOrder1(int[][] matrix) {
//        List<Integer> order = new ArrayList<Integer>();
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
//            return order;
//        }
//        int rows = matrix.length, columns = matrix[0].length;
//        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
//        while (left <= right && top <= bottom) {
//            for (int column = left; column <= right; column++) {
//                order.add(matrix[top][column]);
//            }
//            for (int row = top + 1; row <= bottom; row++) {
//                order.add(matrix[row][right]);
//            }
//            if (left < right && top < bottom) {
//                for (int column = right - 1; column > left; column--) {
//                    order.add(matrix[bottom][column]);
//                }
//                for (int row = bottom; row > top; row--) {
//                    order.add(matrix[row][left]);
//                }
//            }
//            left++;
//            right--;
//            top++;
//            bottom--;
//        }
//        return order;
//    }
//
//}
