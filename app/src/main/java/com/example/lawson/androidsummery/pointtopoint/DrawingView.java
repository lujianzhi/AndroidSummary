package com.example.lawson.androidsummery.pointtopoint;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;


public class DrawingView extends View{

    private static int gridRows = 8;
    private static int gridColumns = 5;

    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0xFF0000FF;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;
    // backup bitmap for erasing last draw
    //private Bitmap backupBitmap;
    // keep track of how many times ACTION_DOWN
    private int numberActionDown = 0;
    private ShapeDrawable[][] gridArray = new ShapeDrawable[gridRows][gridColumns];
    private int[][] gridCoordArray = new int[gridRows * gridColumns][2];

    private int width;
    private int height;

    private int lastTouchedX = 0;
    private int lastTouchedY = 0;

    private Path savedPath;
    private boolean pathDrawn = false;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing(){
        //get drawing area setup for interaction
        drawPath = new Path();
        drawPaint = new Paint();

        // set initial paint color and properties
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(10);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        // instantiate canvas
        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }

    private void setupGrid(int viewWidth, int viewHeight){
        // setup grid drawables
        int bufferWidth = viewWidth / 10;
        int bufferHeight = viewHeight / 20;

        int x = bufferWidth;
        int y = bufferHeight;
        width = bufferWidth;
        height = bufferWidth;

        int widthBetweenGridPoints = ((viewWidth - (2 * bufferWidth)) - (gridColumns * width)) /
                (gridColumns - 1);
        int heightBetweenGridPoints = ((viewHeight - (2 * bufferHeight)) - (gridRows * height)) /
                (gridRows - 1);

        int gridCoordArrayCount = 0;

        for (int i = 0; i < gridRows; ++i){
            for (int j = 0; j < gridColumns; ++j){
                gridArray[i][j] = new ShapeDrawable(new OvalShape());
                gridArray[i][j].getPaint().setColor(0xff74AC23);
                gridArray[i][j].setBounds(x, y, x + width, y + height);
                gridCoordArray[gridCoordArrayCount][0] = x;
                gridCoordArray[gridCoordArrayCount][1] = y;
                ++gridCoordArrayCount;
                x = x + width + widthBetweenGridPoints;
            }

            x = bufferWidth;
            y = y + height + heightBetweenGridPoints;
        }
    }

    private boolean checkIfGridPointPressed(float touchX, float touchY){
        for (int i = 0; i < (gridRows * gridColumns); ++i){
            if (gridCoordArray[i][0] <= touchX && touchX <= (gridCoordArray[i][0] + width)
                    && gridCoordArray[i][1] <= touchY && touchY <= (gridCoordArray[i][1] + height)){
                lastTouchedX = gridCoordArray[i][0];
                lastTouchedY = gridCoordArray[i][1];
                return true;
            }
        }

        return false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //view given size
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        setupGrid(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

        for (int i = 0; i < gridRows; ++i){
            for (int j = 0; j < gridColumns; ++j){
                gridArray[i][j].draw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (numberActionDown == 0 && checkIfGridPointPressed(touchX, touchY)){
                    drawPath.moveTo(lastTouchedX + (width / 2), lastTouchedY + (height / 2));
                    ++numberActionDown;
                }
                else if (numberActionDown == 0 && !checkIfGridPointPressed(touchX, touchY)){
                    drawPath.reset();
                    numberActionDown = 0;
                }
                else if(numberActionDown == 1 && checkIfGridPointPressed(touchX, touchY)){
                    drawPath.lineTo(lastTouchedX + (width / 2), lastTouchedY + (height / 2));
                    drawCanvas.drawPath(drawPath, drawPaint);
                    savedPath = drawPath;
                    pathDrawn = true;
                    drawPath.reset();
                    numberActionDown = 0;
                }
                else if(numberActionDown == 1 && !checkIfGridPointPressed(touchX, touchY)){
                    drawPath.reset();
                    numberActionDown = 0;
                }
                break;
//            case MotionEvent.ACTION_MOVE:
//                if (pathDrawn){
//                    if (checkIfGridPointPressed(touchX, touchY)){
//                        drawPath = savedPath;
//                        drawPath.lineTo(lastTouchedX + (width / 2), lastTouchedY + (height / 2));
//                        drawCanvas.drawPath(drawPath, drawPaint);
//                        savedPath = drawPath;
//                        drawPath.reset();
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }


}
