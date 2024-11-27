package net.kasasbeh.fiqh.quantities.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Weight: ImageVector
	get() {
		if (_Weight != null) {
			return _Weight!!
		}
		_Weight = ImageVector.Builder(
            name = "Weight",
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
				moveTo(240f, 760f)
				horizontalLineToRelative(480f)
				lineToRelative(-57f, -400f)
				horizontalLineTo(297f)
				close()
				moveToRelative(240f, -480f)
				quadToRelative(17f, 0f, 28.5f, -11.5f)
				reflectiveQuadTo(520f, 240f)
				reflectiveQuadToRelative(-11.5f, -28.5f)
				reflectiveQuadTo(480f, 200f)
				reflectiveQuadToRelative(-28.5f, 11.5f)
				reflectiveQuadTo(440f, 240f)
				reflectiveQuadToRelative(11.5f, 28.5f)
				reflectiveQuadTo(480f, 280f)
				moveToRelative(113f, 0f)
				horizontalLineToRelative(70f)
				quadToRelative(30f, 0f, 52f, 20f)
				reflectiveQuadToRelative(27f, 49f)
				lineToRelative(57f, 400f)
				quadToRelative(5f, 36f, -18.5f, 63.5f)
				reflectiveQuadTo(720f, 840f)
				horizontalLineTo(240f)
				quadToRelative(-37f, 0f, -60.5f, -27.5f)
				reflectiveQuadTo(161f, 749f)
				lineToRelative(57f, -400f)
				quadToRelative(5f, -29f, 27f, -49f)
				reflectiveQuadToRelative(52f, -20f)
				horizontalLineToRelative(70f)
				quadToRelative(-3f, -10f, -5f, -19.5f)
				reflectiveQuadToRelative(-2f, -20.5f)
				quadToRelative(0f, -50f, 35f, -85f)
				reflectiveQuadToRelative(85f, -35f)
				reflectiveQuadToRelative(85f, 35f)
				reflectiveQuadToRelative(35f, 85f)
				quadToRelative(0f, 11f, -2f, 20.5f)
				reflectiveQuadToRelative(-5f, 19.5f)
				moveTo(240f, 760f)
				horizontalLineToRelative(480f)
				close()
			}
		}.build()
		return _Weight!!
	}

private var _Weight: ImageVector? = null
