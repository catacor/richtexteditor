package com.catacore.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.catacore.richtexteditor.RichEditorActivity
import com.catacore.sample.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener{

            var intent = Intent(this,RichEditorActivity::class.java)
            intent.putExtra(RichEditorActivity.HTML_CONTENT, "<p>Salll</p>")
            startActivityForResult(intent,HTML_RES)

        }
    }

    var HTML_RES = 1;

    override fun onStart() {
        super.onStart()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == HTML_RES)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                var htmlContent = data!!.getStringExtra(RichEditorActivity.HTML_CONTENT)
                Toast.makeText(baseContext,htmlContent,Toast.LENGTH_LONG).show()
            }
            else
            {
                //do nothing
            }
        }

    }
}