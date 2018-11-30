
package com.oakenshield.thanhh.qrdemo.googleScan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import com.google.android.gms.vision.barcode.Barcode;
import com.oakenshield.thanhh.qrdemo.R;
import com.oakenshield.thanhh.qrdemo.googleScan.camera.GraphicOverlay;

public class BarcodeGraphic extends GraphicOverlay.Graphic {

    private int mId;
    private Paint mRectPaint;
    private volatile Barcode mBarcode;

    BarcodeGraphic(GraphicOverlay overlay, Context context) {
        super(overlay);

        mRectPaint = new Paint();
        mRectPaint.setColor(context.getResources().getColor(R.color.blue_90));
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(5.0f);

        CornerPathEffect cornerPathEffect =
                new CornerPathEffect(16f);

        mRectPaint.setPathEffect(cornerPathEffect);


    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    void updateItem(Barcode barcode) {
        mBarcode = barcode;
        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        Barcode barcode = mBarcode;
        if (barcode == null) {
            return;
        }

        RectF rect = new RectF(barcode.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, mRectPaint);
    }
}
