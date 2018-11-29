
package com.oakenshield.thanhh.qrdemo.googleScan;

import android.content.Context;
import android.support.annotation.UiThread;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.oakenshield.thanhh.qrdemo.googleScan.camera.GraphicOverlay;

/**
 * Generic tracker which is used for tracking or reading a barcode (and can really be used for
 * any type of item).  This is used to receive newly detected items, add a graphical representation
 * to an overlay, update the graphics as the item changes, and remove the graphics when the item
 * goes away.
 */
public class BarcodeGraphicTracker extends Tracker<Barcode> {
    private GraphicOverlay<BarcodeGraphic> mOverlay;
    private BarcodeGraphic mGraphic;

    private BarcodeUpdateListener mBarcodeUpdateListener;

    public interface BarcodeUpdateListener {
        @UiThread
        void onBarcodeDetected(Barcode barcode);
    }

    BarcodeGraphicTracker(GraphicOverlay<BarcodeGraphic> mOverlay, BarcodeGraphic mGraphic,
                          Context context) {
        this.mOverlay = mOverlay;
        this.mGraphic = mGraphic;
        if (context instanceof BarcodeUpdateListener) {
            this.mBarcodeUpdateListener = (BarcodeUpdateListener) context;
        } else {
            throw new RuntimeException("Hosting activity must implement BarcodeUpdateListener");
        }
    }

    @Override
    public void onNewItem(int id, Barcode item) {
        mGraphic.setId(id);
        mBarcodeUpdateListener.onBarcodeDetected(item);
    }

    @Override
    public void onUpdate(Detector.Detections<Barcode> detectionResults, Barcode item) {
        mOverlay.add(mGraphic);
        mGraphic.updateItem(item);
    }

    @Override
    public void onMissing(Detector.Detections<Barcode> detectionResults) {
        mOverlay.remove(mGraphic);
    }

    @Override
    public void onDone() {
        mOverlay.remove(mGraphic);
    }
}
