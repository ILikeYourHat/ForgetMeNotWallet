package com.github.ilikeyourhat.fmnw

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.github.ilikeyourhat.fmnw.db.FakeDatabase

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(
                codes = FakeDatabase.loadValues(),
                onFabClicked = ::onFabClicked
            )
        }
    }

    override fun onResume() {
        super.onResume()
        setContent {
            HomeScreen(
                codes = FakeDatabase.loadValues(),
                onFabClicked = ::onFabClicked
            )
        }
    }

    private fun onFabClicked() {
        val intent = Intent(this, AddCodeActivity::class.java)
        startActivity(intent)
    }
}