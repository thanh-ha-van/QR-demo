package com.oakenshield.thanhh.qrdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import com.oakenshield.thanhh.qrdemo.googleScan.BarcodeCaptureActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val QC_BARCODE_CAPTURE = 9001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_google.setOnClickListener { startQRGoogleVision() }
    }

    private fun startQRGoogleVision() {
        val intent = Intent(this, BarcodeCaptureActivity::class.java)
        startActivityForResult(intent, QC_BARCODE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == QC_BARCODE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {

                val barcode = data.getParcelableExtra<Barcode>(BarcodeCaptureActivity.BarcodeObject)
                tv_message.setText(R.string.barcode_success)
                tv_code.text = barcode.displayValue

            } else {
                tv_message.text = String.format(
                    getString(R.string.barcode_error),
                    CommonStatusCodes.getStatusCodeString(resultCode)
                )
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
