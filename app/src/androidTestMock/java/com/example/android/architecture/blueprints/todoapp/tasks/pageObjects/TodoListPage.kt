package com.example.android.architecture.blueprints.todoapp.tasks.pageObjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.EspressoBaseTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.logTest

class TodoListPage : EspressoBaseTest() {

    val addTaskButton = R.id.add_task_fab
    val filterButton = R.id.menu_filter

    fun verifyTodoDisplayed(todoText: String) {
        logTest("Verify task item with $todoText is displayed")
        onView(withText(todoText)).check(matches(isDisplayed()))
    }

    fun verifyTodoIsNotDisplayed(todoText: String) {
        logTest("Verify task item with $todoText is not displayed")
        onView(withText(todoText)).check(doesNotExist())
    }
}