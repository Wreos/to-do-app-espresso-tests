package com.example.android.architecture.blueprints.todoapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.tasks.logTest


open class EspressoBaseTest {

    fun clickButton(resourceId: Int) {
        logTest("Click on $resourceId button")
        onView(withId(resourceId))
            .perform(click())
    }

    fun fillInWithText(resourceId: Int, text: String) {
        logTest("Fill in $resourceId element with $text")
        onView(withId(resourceId))
            .perform(click())
            .perform(ViewActions.typeText(text), ViewActions.closeSoftKeyboard())
    }

    fun clickOnViewWithText(text: String) {
        logTest("Click on view which contains $text")
        onView(withText(text))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun checkElementContainsText(resourceId: Int, text: String) {
        logTest("Check $resourceId element contains text $text")
        onView(withId(resourceId))
            .check(matches(withText(text)))
    }

    fun checkElementWithTextExist(text: String) {
        logTest("Check element with $text exist")
        onView(withText(text))
            .check(matches(withText(text)))
    }
}

