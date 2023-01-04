package com.example.android.architecture.blueprints.todoapp.pageObjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import com.example.android.architecture.blueprints.todoapp.EspressoBaseTest
import com.example.android.architecture.blueprints.todoapp.R

class TaskDetailPage : EspressoBaseTest() {

    private val titleText = R.id.task_detail_title_text
    private val descriptionText = R.id.task_detail_title_text
    private val taskCompleteCheckbox = R.id.task_detail_complete_checkbox
    val editButton = R.id.edit_task_fab
    val saveTaskButton = R.id.save_task_fab
    val deleteButton = R.id.menu_delete

    fun fillTitle(title: String) {
        onView(withId(titleText)).perform(click())
        onView(withId(titleText)).perform(typeText(title), closeSoftKeyboard())
    }

    fun fillDescription(description: String) {
        onView(withId(titleText)).perform(click())
        onView(withId(titleText)).perform(typeText(description), closeSoftKeyboard())
    }
}