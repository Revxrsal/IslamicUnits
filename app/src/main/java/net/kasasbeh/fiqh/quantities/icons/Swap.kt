package net.kasasbeh.fiqh.quantities.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Swap: ImageVector
    get() {
        if (_Swap_vert != null) {
            return _Swap_vert!!
        }
        _Swap_vert = ImageVector.Builder(
            name = "Swap_vert",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(320f, 520f)
                verticalLineToRelative(-287f)
                lineTo(217f, 336f)
                lineToRelative(-57f, -56f)
                lineToRelative(200f, -200f)
                lineToRelative(200f, 200f)
                lineToRelative(-57f, 56f)
                lineToRelative(-103f, -103f)
                verticalLineToRelative(287f)
                close()
                moveTo(600f, 880f)
                lineTo(400f, 680f)
                lineToRelative(57f, -56f)
                lineToRelative(103f, 103f)
                verticalLineToRelative(-287f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(287f)
                lineToRelative(103f, -103f)
                lineToRelative(57f, 56f)
                close()
            }
        }.build()
        return _Swap_vert!!
    }

private var _Swap_vert: ImageVector? = null
