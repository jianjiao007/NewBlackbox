package com.jianjiao.bx.util

import android.graphics.Point
import kotlin.math.*

class MathUtil {
    companion object {
        /**
         * Get the distance between two points.
         */
        fun getDistance(x1: Float, y1: Float, x2: Float, y2: Float): Int {
            return sqrt((x1 - x2).toDouble().pow(2.0) + (y1 - y2).toDouble().pow(2.0))
                .toInt()
        }

        /**
         * Get the coordinates of a point on the line by cut length.
         *
         * @param A         Point A
         * @param B         Point B
         * @param cutLength cut length
         * @return the point.
         */
        fun getPointByCutLength(A: Point, B: Point, cutLength: Int): Point {
            val radian = getRadian(A, B)
            return Point(
                A.x + (cutLength * cos(radian.toDouble())).toInt(),
                A.y + (cutLength * sin(radian.toDouble())).toInt()
            )
        }

        /**
         * Get the radian between current line(determined by point A and B) and horizontal line.
         *
         * @param A point A
         * @param B point B
         * @return the radian
         */
        private fun getRadian(A: Point, B: Point): Float {
            val lenA: Int = B.x - A.x
            val lenB: Int = B.y - A.y
            val lenC = sqrt((lenA * lenA + lenB * lenB).toDouble()).toFloat()
            var radian = acos((lenA / lenC).toDouble()).toFloat()

            radian *= if (B.y < A.y) -1 else 1
            return radian
        }
    }
}