package com.example.lawson.androidsummery.diyview.bord

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.lawson.androidsummery.R
import com.example.lawson.androidsummery.diyview.bord.draw.DrawActivity
import kotlinx.android.synthetic.main.activity_bord.*


/**
 * USER：lujianzhi
 * DATE：2020/10/26
 */
class BordActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bord)

        draw.setOnClickListener { startActivity(Intent(this@BordActivity, DrawActivity::class.java)) }
        changeCanvas1.setOnClickListener { }
        changeCanvas2.setOnClickListener { }
        export1.setOnClickListener { }
        export2.setOnClickListener { }
        eraser.setOnClickListener { drawView.eraser() }
        paint.setOnClickListener { drawView.paint() }
    }
}