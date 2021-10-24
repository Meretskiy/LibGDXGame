package com.meretskiy.game.math;


import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

/**
 * Утилита для работы с матрицами
 */
public class MatrixUtils {

    private MatrixUtils() {
    }

    /**
     * Расчёт матрицы перехода 4x4
     * Проицируем изображение на конечный прямоугольник
     *
     * @param mat итоговая матрица преобразований
     * @param src исходный прямоугольник
     * @param dst итоговый прямоугольник
     */
    public static void calcTransitionMatrix(Matrix4 mat, Rect src, Rect dst) {
        float scaleX = dst.getWidth() / src.getWidth();
        float scaleY = dst.getHeight() / src.getHeight();
        mat.idt()
                .translate(dst.pos.x, dst.pos.y, 0f)    // смещаем в позицию итогового прямоугольника
                .scale(scaleX, scaleY, 1f)          // скалируем по рассчитанным значениям
                .translate(-src.pos.x, -src.pos.y, 0f); // смещаем исходный прямоугольник в начала координат
    }

    /**
     * Расчёт матрицы перехода 3x3
     *
     * @param mat итоговая матрица преобразований
     * @param src исходный прямоугольник
     * @param dst итоговый прямоугольник
     */
    public static void calcTransitionMatrix(Matrix3 mat, Rect src, Rect dst) {
        float scaleX = dst.getWidth() / src.getWidth();
        float scaleY = dst.getHeight() / src.getHeight();
        mat.idt().translate(dst.pos.x, dst.pos.y).scale(scaleX, scaleY).translate(-src.pos.x, -src.pos.y);
    }
}
