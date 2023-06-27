package com.example.githubtrends

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.githubtrends.di.UiModule
import com.example.githubtrends.presentation.adapters.ReposAdapter
import com.example.githubtrends.presentation.ui.TrendingReposFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.testing.TestInstallIn
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Singleton


@RunWith(AndroidJUnit4::class)
@LargeTest
@UninstallModules(UiModule::class)
@HiltAndroidTest
class TrendingReposFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    // single task rule
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        hiltRule.inject()
    }

    private lateinit var scenario: FragmentScenario<TrendingReposFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_GithubTrends)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun recyclerViewIsVisibleSuccess() {
        onView(isRoot()).perform(waitFor(5000))

        try {
            onView(withId(R.id.repos_recyclerview)).check(matches(isDisplayed()))

        } catch (e: AssertionFailedError) {
            // test fails
        }
    }

    @Test
    fun errorViewIsVisibleSuccess() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi disable")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data disable")

        try {
            onView(withId(R.id.error_view)).check(matches(isDisplayed()))
        } catch (e: AssertionFailedError) {
            // test fails
        }
    }

    private fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }


    @Module
    @TestInstallIn(
        components = [FragmentComponent::class],
        replaces = [UiModule::class]
    )
    abstract class TestModule {
        @Binds
        @Singleton
        abstract fun provideReposAdapter(): ReposAdapter
    }


}
