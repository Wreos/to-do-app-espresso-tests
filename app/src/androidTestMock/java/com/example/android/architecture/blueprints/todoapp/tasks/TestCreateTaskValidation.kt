/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.android.architecture.blueprints.todoapp.ServiceLocator
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.pageObjects.TaskDetailPage
import com.example.android.architecture.blueprints.todoapp.pageObjects.TaskPage
import com.example.android.architecture.blueprints.todoapp.pageObjects.TodoListPage
import com.example.android.architecture.blueprints.todoapp.util.DataBindingIdlingResource
import com.example.android.architecture.blueprints.todoapp.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Large End-to-End test for the tasks module.
 *
 * UI tests usually use [ActivityTestRule] but there's no API to perform an action before
 * each test. The workaround is to use `ActivityScenario.launch()` and `ActivityScenario.close()`.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class TestCreateTaskValidation {

    private lateinit var repository: TasksRepository

    // An Idling Resource that waits for Data Binding to have no pending bindings
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun init() {
        // Run on UI thread to make sure the same instance of the SL is used.
        runOnUiThread {
            ServiceLocator.createDataBase(getApplicationContext(), inMemory = true)
            repository = ServiceLocator.provideTasksRepository(getApplicationContext())
            repository.deleteAllTasksBlocking()
        }
    }

    @After
    fun reset() {
        runOnUiThread {
            ServiceLocator.resetRepository()
        }
    }

    /**
     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
     * are not scheduled in the main Looper (for example when executed on a different thread).
     */
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun editTask() {
        repository.saveTaskBlocking(Task("TITLE1", "DESCRIPTION"))

        val activityScenario = ActivityScenario.launch(TasksActivity::class.java)
        val todoListPage = TodoListPage()
        val taskDetailPage = TaskDetailPage()
        val editTaskPage = TaskPage()

        dataBindingIdlingResource.monitorActivity(activityScenario)

        todoListPage.clickOnViewWithText("TITLE1")
        taskDetailPage.clickButton(taskDetailPage.editButton)
        editTaskPage.fillTitle("TITLE2")
        editTaskPage.fillDescription("DESCRIPTION2")
        editTaskPage.clickButton(editTaskPage.saveTaskButton)
        todoListPage.verifyTodoDisplayed("TITLE2")
        todoListPage.verifyTodoIsNotDisplayed("TITLE1")
        activityScenario.close()
    }

    @Test
    fun testCreateTask1() {
        val activityScenario = ActivityScenario.launch(TasksActivity::class.java)
        val todoListPage = TodoListPage()
        val editTaskPage = TaskPage()

        dataBindingIdlingResource.monitorActivity(activityScenario)

        todoListPage.clickButton(todoListPage.addTaskButton)
        editTaskPage.fillTitle("TITLE")
        editTaskPage.fillDescription("DESCRIPTION")
        editTaskPage.clickButton(editTaskPage.saveTaskButton)
        todoListPage.checkToastTextIsDisplayed("Tasks cannot be empty")
        activityScenario.close()
    }

}
