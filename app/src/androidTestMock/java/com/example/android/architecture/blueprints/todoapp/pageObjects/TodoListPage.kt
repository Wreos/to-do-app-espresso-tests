package com.example.android.architecture.blueprints.todoapp.pageObjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.EspressoBaseTest
import com.example.android.architecture.blueprints.todoapp.R

class TodoListPage : EspressoBaseTest() {

    val addTaskButton = R.id.add_task_fab
    val filterButton = R.id.menu_filter

    fun verifyTodoDisplayed(todoText: String) {
        onView(withText(todoText)).check(matches(isDisplayed()))
    }

    fun verifyTodoIsNotDisplayed(todoText: String) {
        onView(withText(todoText)).check(doesNotExist())
    }
}