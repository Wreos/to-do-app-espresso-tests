package com.example.android.architecture.blueprints.todoapp.pageObjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import com.example.android.architecture.blueprints.todoapp.EspressoBaseTest
import com.example.android.architecture.blueprints.todoapp.R

class TaskPage : EspressoBaseTest() {

    private val titleText = R.id.add_task_title_edit_text
    private val descriptionText = R.id.add_task_description_edit_text
    val saveTaskButton = R.id.save_task_fab


    fun fillTitle(title: String) {
        onView(withId(titleText)).perform(click())
        onView(withId(titleText)).perform(clearText())
        onView(withId(titleText)).perform(typeText(title), closeSoftKeyboard())
    }

    fun fillDescription(description: String) {
        onView(withId(descriptionText)).perform(click())
        onView(withId(descriptionText)).perform(clearText())
        onView(withId(descriptionText)).perform(typeText(description), closeSoftKeyboard())
    }
}