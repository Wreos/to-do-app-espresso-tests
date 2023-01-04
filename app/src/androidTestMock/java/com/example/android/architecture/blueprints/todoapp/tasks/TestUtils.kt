package com.example.android.architecture.blueprints.todoapp.tasks

import android.app.Activity
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.util.EspressoIdlingResource
import com.example.android.architecture.blueprints.todoapp.util.*

private val dataBindingIdlingResource = DataBindingIdlingResource()

fun <T : Activity> ActivityScenario<T>.getToolbarNavigationContentDescription(): String {
    var description = ""
    onActivity {
        description =
            it.findViewById<Toolbar>(R.id.toolbar).navigationContentDescription as String
    }
    return description
}


fun logTest(log: String) {
    Log.v("Espresso Test", log);
 }

fun registerIdlingResource() {
    IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    IdlingRegistry.getInstance().register(dataBindingIdlingResource)
}

fun unregisterIdlingResource() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
}

