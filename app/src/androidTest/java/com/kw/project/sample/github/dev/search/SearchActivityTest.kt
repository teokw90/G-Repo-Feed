package com.kw.project.sample.github.dev.search

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.kw.project.sample.github.dev.R
import com.kw.project.sample.github.dev.data.source.SearchRepository
import com.kw.project.sample.github.dev.data.source.SearchRepositoryImpl
import com.kw.project.sample.github.dev.data.source.remote.SearchDataSource
import com.kw.project.sample.github.dev.data.source.remote.SearchRemoteDataSource
import com.kw.project.sample.github.dev.search.utils.DataBindingIdlingResource
import com.kw.project.sample.github.dev.search.utils.monitorActivity
import com.kw.project.sample.github.dev.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Kuan Wah Teo on 03/07/2020
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchActivityTest {
    // An idling resource that waits for Data Binding to have no pending bindings.
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    private lateinit var repository: SearchRepository

    @Before
    fun init() {
        repository = SearchRepositoryImpl(SearchRemoteDataSource())
    }

    /**
     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
     * are not scheduled in the main lopper (for example when executed on a different thread).
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
    fun getGithubTrendingRepositories() {
        // Start up search screen.
        val activityScenario = ActivityScenario.launch(SearchActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // Make sure the activity is closed before resetting the db.
        activityScenario.close()
    }
}

