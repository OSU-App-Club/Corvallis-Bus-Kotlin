package org.osuappclub.corvallisbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.activity_main.*

/**
 * Created by rikkigibson on 12/27/15.
 */
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainText.text = "Let's get started on Corvallis Bus!"
    }
}