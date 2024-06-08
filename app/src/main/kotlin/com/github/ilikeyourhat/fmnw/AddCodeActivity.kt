package com.github.ilikeyourhat.fmnw

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.github.ilikeyourhat.fmnw.db.FakeDatabase

class AddCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddCodeScreen(
                onDoneClicked = ::onDoneClicked
            )
        }
    }

    private fun onDoneClicked(text: String) {
        FakeDatabase.addValue(text)
        finish()
    }
}