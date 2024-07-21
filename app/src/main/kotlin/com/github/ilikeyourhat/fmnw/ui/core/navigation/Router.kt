package com.github.ilikeyourhat.fmnw.ui.core.navigation

import androidx.appcompat.app.AppCompatActivity
import com.hadilq.liveevent.LiveEvent
import javax.inject.Inject

class Router @Inject constructor() {

    private val navigationEvents = LiveEvent<Navigation>()

    fun navigate(navigation: Navigation) {
        navigationEvents.postValue(navigation)
    }

    fun attachTo(activity: AppCompatActivity) {
        navigationEvents.observe(activity) { it.navigate(activity) }
    }
}