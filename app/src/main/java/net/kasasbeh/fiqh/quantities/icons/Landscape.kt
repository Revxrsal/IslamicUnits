package net.kasasbeh.fiqh.quantities.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Landscape: ImageVector
    get() {
        if (_Landscape != null) {
            return _Landscape!!
        }
        _Landscape = ImageVector.Builder(
            name = "Landscape",
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
                moveTo(40f, 720f)
                lineToRelative(240f, -320f)
                lineToRelative(180f, 240f)
                horizontalLineToRelative(300f)
                lineTo(560f, 374f)
                lineTo(460f, 506f)
                lineToRelative(-50f, -66f)
                lineToRelative(150f, -200f)
                lineToRelative(360f, 480f)
                close()
                moveToRelative(160f, -80f)
                horizontalLineToRelative(160f)
                lineToRelative(-80f, -107f)
                close()
                moveToRelative(0f, 0f)
                horizontalLineToRelative(160f)
                close()
            }
        }.build()
        return _Landscape!!
    }

private var _Landscape: ImageVector? = null
