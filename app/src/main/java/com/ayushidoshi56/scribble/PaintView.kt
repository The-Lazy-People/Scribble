package com.ayushidoshi56.scribble

import android.app.ActionBar
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Parcel
import android.os.Parcelable
import android.view.MotionEvent
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.view.View as View1

var brush = Paint()
var path = Path()
lateinit var params:LinearLayout.LayoutParams

class PaintView(context: Context?): android.view.View(context) {

    init{
        brush.isAntiAlias =true
        brush.setColor(Color.BLACK)
        brush.style=Paint.Style.STROKE
        brush.strokeJoin=Paint.Join.ROUND
        brush.strokeWidth=18f

        params= LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }
    public fun co(x:Float,y:Float)
    {
        path.moveTo(x, y);
        path.lineTo(x+4, y+4)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var pointX = event.x
        var pointY = event.y

        when(event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY);
                return true
            }
            MotionEvent.ACTION_UP -> {
                path.moveTo(pointX, pointY);
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
            }
            else -> return false
        }
       postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas)
    {
        canvas.drawPath(path,brush)
    }
}