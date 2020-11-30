package com.example.lawson.androidsummery.diyview.bord.view

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.lawson.androidsummery.diyview.MeasureUtil

/**
 * USER：lujianzhi
 * DATE：2020/10/26
 */
class DrawView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr) {

    private val mPaint = Paint()
    private val mPath = Path()
    private var mCanvas: Canvas? = null
    private lateinit var mBitmap: Bitmap
    private var mInitBitmap: Bitmap? = null

    private var preX: Float = 0.0f
    private var preY: Float = 0.0f

    init {
        mPaint.strokeWidth = 10.0F
        mPaint.color = Color.parseColor("#FF0000")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.isAntiAlias = true
    }

    fun saveLayer(){
    }

    fun eraser() {
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    fun paint() {
        mPaint.xfermode = null
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (mInitBitmap == null || mCanvas == null) {
            mBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_4444)
            mCanvas = Canvas(mBitmap)
            mInitBitmap = mBitmap
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(mBitmap, 0F, 0F, null)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x: Float = event?.x ?: 0F
        val y: Float = event?.y ?: 0F

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                //贝塞尔曲线，更加圆滑
                mPath.quadTo(preX, preY, x, y)
                mCanvas?.drawPath(mPath, mPaint)
            }
            MotionEvent.ACTION_UP -> {
                mPath.reset()
            }
            else -> {

            }
        }

        invalidate()
        preX = x
        preY = y
        return true
    }
}