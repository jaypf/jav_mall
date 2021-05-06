package org.mall.蚂蚁呀嘿;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName a54螺旋矩阵
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2021/4/15 15:17
 * @Version 1.0
 */
public class a54螺旋矩阵 {
//    给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
//
//
//    示例 1：
//
//    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//    输出：[1,2,3,6,9,8,7,4,5]

    @Test
    public void test() {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
//        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> integers = spiralOrder(matrix);
        System.out.println(integers.toString());
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if(matrix == null || matrix.length == 0){
            return ans;
        }
        int xLen = matrix.length, yLen = matrix[0].length;
        int xf = 0, yf = 0, xc = yLen - 1, yc = xLen - 1;
        while (xf <= xc && yf <= yc){
            for (int i = xf; i <= xc; i++) {
                ans.add(matrix[xf][i]);
            }
            for (int i = yf+1; i <= yc; i++) {
                ans.add(matrix[i][xc]);
            }
            if(xf < xc && yf < yc){
                for (int i = xc-1; i >= xf; i--) {
                    ans.add(matrix[yc][i]);
                }
                for (int i = yc-1; i > yf; i--) {
                    ans.add(matrix[i][xf]);
                }
            }
            xf++;
            xc--;
            yf++;
            yc--;
        }
        return ans;
    }


}
