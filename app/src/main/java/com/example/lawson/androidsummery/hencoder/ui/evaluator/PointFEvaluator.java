package com.example.lawson.androidsummery.hencoder.ui.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Ian.Lu on 2017/12/4.
 * Project : AndroidSummary
 */

public class PointFEvaluator implements TypeEvaluator<PointF> {

    private PointF newPointF = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

        float x = startValue.x + (endValue.x - startValue.x) * fraction;
        float y = startValue.y + (endValue.y - startValue.y) * fraction;
        newPointF.set(x, y);

        return newPointF;
    }
}
