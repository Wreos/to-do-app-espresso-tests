package com.example.android.architecture.blueprints.todoapp.pageObjects

import com.example.android.architecture.blueprints.todoapp.EspressoBaseTest
import com.example.android.architecture.blueprints.todoapp.R

class TaskDetailPage : EspressoBaseTest() {

    val titleText = R.id.task_detail_title_text
    val descriptionText = R.id.task_detail_description_text
    val editButton = R.id.edit_task_fab
    val deleteButton = R.id.menu_delete

}