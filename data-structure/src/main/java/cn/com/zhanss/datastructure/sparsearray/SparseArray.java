package cn.com.zhanss.datastructure.sparsearray;

import org.junit.Test;

import java.io.*;

/**
 * 稀疏数组
 *
 * @author zhanss
 * @since 2019/10/30
 */
public class SparseArray {

    @Test
    public void testSparseArray() {
        // 棋盘
        int[][] chessArr1 = new int[11][11];
        // 棋盘初始化：0，没有子；1，黑子；2，白子
        chessArr1[1][4] = 1;
        chessArr1[2][5] = 2;
        chessArr1[3][5] = 2;

        // 统计子的数量
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int column : row ) {
                if (column != 0) {
                    sum ++;
                }
                System.out.printf("%d\t", column);
            }
            System.out.println();
        }

        /**
         * 将二维数组压缩成稀疏数组
         */
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int count = 0;
        for (int i = 0; i < 11; i ++) {
            for (int j = 0; j < 11; j ++) {
                if (chessArr1[i][j] == 0) {
                    continue;
                }
                count ++;
                sparseArr[count][0] = i;
                sparseArr[count][1] = j;
                sparseArr[count][2] = chessArr1[i][j];
            }
        }

        // 输出稀疏数组
        System.out.println("二维数组稀疏化：");
        for (int[] sparse : sparseArr) {
            System.out.printf("%d\t%d\t%d\n", sparse[0], sparse[1], sparse[2]);
        }

        /**
         * 将稀疏数组写入文件
         */
        File file = new File("F:/BaiDuYunDownload/尚硅谷/workspace/interviews/file.txt");
        try {
            FileWriter writer = new FileWriter(file);
            for (int[] row : sparseArr) {
                for (int column : row) {
                    writer.write(column + "\t");
                }
                writer.write("\r\n");
            }
            writer.close();
            System.out.println("将稀疏数组写入文件成功！！\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 从文件读取稀疏数组
         */
        int[][] chessArr2 = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int row = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split("\t");
                if (row ++ == 0 || chessArr2 == null) {
                    chessArr2 = new int[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])];
                } else {
                    chessArr2[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = Integer.valueOf(temp[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("稀疏数组还原为二维数组：");
        for (int[] row : chessArr2) {
            for (int column : row ) {
                System.out.printf("%d\t", column);
            }
            System.out.println();
        }
    }

}
