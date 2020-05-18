package br.com.ebss.desafio_android_eduardo_seguro

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import br.com.ebss.desafio_android_eduardo_seguro.adapter.CharacterListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun downScroll() {
        Thread.sleep(1000)
        for (i in 0..10) {
            onView(withId(R.id.rv_characters_list))
                .perform(ViewActions.swipeUp())
            Thread.sleep(500)
        }

    }

    @Test
    fun selectCharacter() {
        Thread.sleep(1000)
        onView(withId(R.id.rv_characters_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CharacterListAdapter.ViewHolder>(
                    1,
                    click()
                )
            )
        Thread.sleep(500)
        onView(withId(R.id.iv_char_detail_back_arrow))
            .perform(click())
        Thread.sleep(500)
        onView(withId(R.id.rv_characters_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CharacterListAdapter.ViewHolder>(
                    9,
                    click()
                )
            )
        Thread.sleep(500)
        onView(withId(R.id.iv_char_detail_back_arrow))
            .perform(click())
        Thread.sleep(500)
    }

}