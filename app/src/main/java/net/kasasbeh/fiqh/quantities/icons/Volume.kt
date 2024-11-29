package net.kasasbeh.fiqh.quantities.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Volume: ImageVector
    get() {
        if (_Glass_cup != null) {
            return _Glass_cup!!
        }
        _Glass_cup = ImageVector.Builder(
            name = "Glass_cup",
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
                moveTo(279f, 880f)
                quadToRelative(-31f, 0f, -53.5f, -20f)
                reflectiveQuadTo(200f, 809f)
                lineToRelative(-80f, -729f)
                horizontalLineToRelative(720f)
                lineToRelative(-80f, 729f)
                quadToRelative(-3f, 31f, -25.5f, 51f)
                reflectiveQuadTo(681f, 880f)
                close()
                moveToRelative(-8f, -160f)
                lineToRelative(9f, 80f)
                horizontalLineToRelative(400f)
                lineToRelative(9f, -80f)
                close()
                moveToRelative(-8f, -80f)
                horizontalLineToRelative(435f)
                lineToRelative(52f, -480f)
                horizontalLineTo(210f)
                close()
                moveToRelative(8f, 160f)
                horizontalLineToRelative(418f)
                close()
            }
        }.build()
        return _Glass_cup!!
    }

private var _Glass_cup: ImageVector? = null
