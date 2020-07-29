package com.ayushidoshi56.scribble

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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
        path.lineTo(x, y)
    }
    public fun end(x:Float,y:Float)
    {
        path.lineTo(x, y)
        postInvalidate()
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        database = Firebase.database
        postReference = database.reference.child("data")

        when(event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
                uploadToDatabase(pointX , pointY , 0)
                return true
            }
            MotionEvent.ACTION_UP -> {
                path.moveTo(pointX, pointY)
                uploadToDatabase(pointX , pointY , 1)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
                uploadToDatabase(pointX , pointY , 2)
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

    private fun uploadToDatabase(pointX:Float, pointY:Float , type: Int) {
        val info = Information(pointX, pointY, type)
        postReference.push().setValue(info)
    }
}
