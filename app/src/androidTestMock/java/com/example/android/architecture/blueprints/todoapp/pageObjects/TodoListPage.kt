package com.example.android.architecture.blueprints.todoapp.pageObjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.EspressoBaseTest
import com.example.android.architecture.blueprints.todoapp.R

class TodoListPage : EspressoBaseTest() {

    private val detailTitleText = R.id.task_detail_title_text
    val addTaskButton = R.id.add_task_fab
    val filterButton = R.id.menu_filter

    fun verifyTodoDisplayed(todoText: String) {
        onView(withText(todoText)).check(matches(isDisplayed()))
    }

    fun verifyTodoIsNotDisplayed(todoText: String) {
        onView(withText(todoText)).check(doesNotExist())
    }
}