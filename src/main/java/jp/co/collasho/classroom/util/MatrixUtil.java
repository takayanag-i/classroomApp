package jp.co.collasho.classroom.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 行列（matrix）データに関するUtilクラス
 */
public class MatrixUtil {
    public static <T> List<List<T>> transpose(List<List<T>> matrix) {
        if (matrix == null || matrix.isEmpty()) {
            throw new IllegalArgumentException("Invalid matrix");
        }

        int numRows = matrix.size();
        int maxCols = 0;

        // 最大列数の計算
        for (List<T> row : matrix) {
            if (row.size() > maxCols) {
                maxCols = row.size();
            }
        }

        if (maxCols == 0) {
            throw new IllegalArgumentException("Invalid matrix");
        }

        List<List<T>> transposedMatrix = new ArrayList<>();

        // 転置されたマトリックスの初期化
        for (int i = 0; i < maxCols; i++) {
            transposedMatrix.add(new ArrayList<>());
        }

        // 転置処理
        for (int i = 0; i < numRows; i++) {
            List<T> row = matrix.get(i);
            for (int j = 0; j < row.size(); j++) {
                transposedMatrix.get(j).add(row.get(j));
            }
        }

        return transposedMatrix;
    }
}
