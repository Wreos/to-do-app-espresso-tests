package com.example.android.architecture.blueprints.todoapp.tasks.pageObjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.EspressoBaseTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.logTest

class TodoListPage : EspressoBaseTest() {

    private val noTasksLayout = R.id.no_tasks_layout
    private val noTasksIcon = R.id.no_tasks_icon
    private val noTasksText = R.id.no_tasks_text
    val addTaskButton = R.id.add_task_fab
    val filterButton = R.id.menu_filter

    fun verifyTaskIsDisplayed(todoText: String) {
        logTest("Verify task item with $todoText is displayed")
        onView(withText(todoText)).check(matches(isDisplayed()))
    }

    fun verifyTaskIsNotDisplayed(todoText: String) {
        logTest("Verify task item with $todoText is not displayed")
        onView(withText(todoText)).check(doesNotExist())
    }

    fun verifyTodoListIsEmpty() {
        logTest("Verify to do list is empty")
        onView(withId(noTasksLayout))
            .check(matches(isDisplayed()))
        onView(withId(noTasksText))
            .check(matches(isDisplayed()))
        onView(withId(noTasksIcon))
            .check(matches(isDisplayed()))
    }
}