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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.view.View as View1


var brush = Paint()
var path = Path()
lateinit var params:LinearLayout.LayoutParams

class PaintView(context: Context?): android.view.View(context) {
    private lateinit var database: FirebaseDatabase
    private lateinit var postReference: DatabaseReference
    init{
        brush.isAntiAlias =true
        brush.setColor(Color.BLACK)
        brush.style=Paint.Style.STROKE
        brush.strokeJoin=Paint.Join.ROUND
        brush.strokeWidth=18f

        params= LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }
    public fun start(x:Float,y:Float)
    {
        path.moveTo(x, y);
    }
    public fun co(x:Float,y:Float)
    {
        path.lineTo(x+4, y+4)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var pointX = event.x
        var pointY = event.y
        var data= mutableListOf<Float>()
        data.add(pointX)
        data.add(pointY)
        database = FirebaseDatabase.getInstance()
        postReference=database.reference

        when(event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY);
                data.add(0f)
                postReference.child("data").push().setValue(data)
                return true
            }
            MotionEvent.ACTION_UP -> {
                path.moveTo(pointX, pointY);
                data.add(1f)
                postReference.child("data").push().setValue(data)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
                data.add(2f)
                postReference.child("data").push().setValue(data)
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