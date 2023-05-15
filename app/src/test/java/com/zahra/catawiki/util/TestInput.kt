package com.zahra.catawiki.util

import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.regex.Matcher
import java.util.regex.Pattern

@RunWith(JUnit4::class)
class  UtilsTest {
    @Test
    fun `assert url extract id regex is correct`() {
        val url = "https://pokeapi.co/api/v2/pokemon-species/7/"
        val regex = "(\\d+)(?!.*\\d)"
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(url)
        assertTrue((matcher.group(1) ?: "") == "7")
    }
}

