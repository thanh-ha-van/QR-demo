
package com.oakenshield.thanhh.qrdemo.googleScan;

import android.content.Context;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.oakenshield.thanhh.qrdemo.googleScan.camera.GraphicOverlay;

class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode> {
    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;
    private Context mContext;

    BarcodeTrackerFactory(GraphicOverlay<BarcodeGraphic> mGraphicOverlay,
                          Context mContext) {
        this.mGraphicOverlay = mGraphicOverlay;
        this.mContext = mContext;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        BarcodeGraphic graphic = new BarcodeGraphic(mGraphicOverlay, mContext);
        return new BarcodeGraphicTracker(mGraphicOverlay, graphic, mContext);
    }

}

