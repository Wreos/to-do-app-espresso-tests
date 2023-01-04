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
package com.example.android.architecture.blueprints.todoapp.tasks.tests

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
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.util.EspressoIdlingResource
import com.example.android.architecture.blueprints.todoapp.util.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CreateReadUpdateDeleteTests {

    private lateinit var repository: TasksRepository
    private val dataBindingIdlingResource = DataBindingIdlingResource()
    private val taskTitleOne = "taskTitleOne"
    private val taskTitleTwo = "taskTitleTwo"
    private val taskDescriptionOne = "taskDescriptionOne"
    private val taskDescriptionTwo = "taskDescriptionTwo"

    @Before
    fun init() {
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

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun editTask() {
        repository.saveTaskBlocking(Task(taskTitleOne, taskDescriptionOne))

        val activityScenario = ActivityScenario.launch(TasksActivity::class.java)
        val todoListPage = TodoListPage()
        val taskDetailPage = TaskDetailPage()
        val editTaskPage = TaskPage()

        dataBindingIdlingResource.monitorActivity(activityScenario)

        todoListPage.clickOnViewWithText(taskTitleOne)
        taskDetailPage.clickButton(taskDetailPage.editButton)
        editTaskPage.fillTitle(taskTitleTwo)
        editTaskPage.fillDescription(taskDescriptionTwo)
        editTaskPage.clickButton(editTaskPage.saveTaskButton)
        todoListPage.verifyTodoDisplayed(taskTitleTwo)
        todoListPage.verifyTodoIsNotDisplayed(taskTitleOne)
        activityScenario.close()
    }

    @Test
    fun testCreateTask() {
        val activityScenario = ActivityScenario.launch(TasksActivity::class.java)
        val todoListPage = TodoListPage()
        val editTaskPage = TaskPage()

        dataBindingIdlingResource.monitorActivity(activityScenario)

        todoListPage.clickButton(todoListPage.addTaskButton)
        editTaskPage.fillTitle(taskTitleOne)
        editTaskPage.fillDescription(taskDescriptionOne)
        editTaskPage.clickButton(editTaskPage.saveTaskButton)
        todoListPage.verifyTodoDisplayed(taskTitleOne)
        activityScenario.close()
    }

    @Test
    fun testDeleteTask() {
        repository.saveTaskBlocking(Task(taskTitleOne, taskDescriptionOne))

        val activityScenario = ActivityScenario.launch(TasksActivity::class.java)
        val todoListPage = TodoListPage()
        val taskDetailPage = TaskDetailPage()

        dataBindingIdlingResource.monitorActivity(activityScenario)

        todoListPage.clickOnViewWithText(taskTitleOne)
        taskDetailPage.clickButton(taskDetailPage.deleteButton)
        todoListPage.clickButton(todoListPage.filterButton)
        todoListPage.clickOnViewWithText("All")
        todoListPage.verifyTodoIsNotDisplayed(taskTitleOne)
        activityScenario.close()
    }

    @Test
    fun testTaskDetailInformation() {
        repository.saveTaskBlocking(Task(taskTitleOne, taskDescriptionOne))

        val activityScenario = ActivityScenario.launch(TasksActivity::class.java)
        val todoListPage = TodoListPage()
        val taskDetailPage = TaskDetailPage()

        dataBindingIdlingResource.monitorActivity(activityScenario)

        todoListPage.clickOnViewWithText(taskTitleOne)
        taskDetailPage.checkElementContainsText(taskDetailPage.titleText, taskTitleOne)
        taskDetailPage.checkElementContainsText(taskDetailPage.descriptionText, taskDescriptionOne)
        activityScenario.close()
    }
}
