package com.example.android.architecture.blueprints.todoapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.tasks.logTest
import org.hamcrest.Matchers.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
open class EspressoBaseTest {

    fun clickButton(resourceId: Int) {
        onView(withId(resourceId))
            .perform(click())
    }

    fun fillInWithText(resourceId: Int, text: String) {
        logTest("Fill in element with $text")
        onView(withId(resourceId))
            .perform(click())
            .perform(ViewActions.typeText(text), ViewActions.closeSoftKeyboard())
    }

    fun clickOnViewWithText(text: String) {
        logTest("Click on view with $text")
        onView(withText(text))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun checkElementContainsText(resourceId: Int, text: String) {
        logTest("Check element contains text $text")
        onView(withId(resourceId))
            .check(matches(withText(text)))
    }

    fun checkElementWithTextExist(text: String) {
        logTest("Check element with $text exist")
        onView(withText(text))
            .check(matches(withText(text)))
    }
}

