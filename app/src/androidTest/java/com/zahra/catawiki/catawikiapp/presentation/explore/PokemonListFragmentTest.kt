package com.zahra.catawiki.catawikiapp.presentation.explore

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers
import org.junit.Test
import com.zahra.catawiki.R


class PokemonListFragmentTest {
//todo change scenario to fix extras
    @Test
    fun isToolbarDisplayed() {
        launchFragmentInContainer<PokemonListFragment>()
        onView(withId(R.id.toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun isTitleDisplayed() {
        launchFragmentInContainer<PokemonListFragment>()
        onView(
            Matchers.allOf(
                withId(R.id.toolbar_title),
                ViewMatchers.withText("Pokemons")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}