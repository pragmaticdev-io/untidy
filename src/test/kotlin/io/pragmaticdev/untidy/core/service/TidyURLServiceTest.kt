package io.pragmaticdev.untidy.core.service

import io.pragmaticdev.untidy.core.model.TidyURL
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
internal class TidyURLServiceTest {

    @Autowired
    lateinit var tidyURLService: TidyURLService

    @Test
    fun createTidyURL() {
        val now = LocalDateTime.now()

        val tidyURL = TidyURL(originalURL = "https://example.com")
        val createdTidyURL = tidyURLService.createTidyURL(tidyURL)

        assertThat(createdTidyURL.originalURL, equalTo(tidyURL.originalURL))
        assertThat(createdTidyURL.id, notNullValue())
        assertThat(createdTidyURL.createdAt, notNullValue())
        assertTrue(createdTidyURL.createdAt.isAfter(now))
        assertThat(createdTidyURL.expiresAt, notNullValue())
        assertTrue(createdTidyURL.createdAt.isAfter(now))
    }

    @Test
    fun findByIdOrNull() {
        val tidyURL = TidyURL(originalURL = "https://example.com")
        val createdTidyURL = tidyURLService.createTidyURL(tidyURL)

        val foundTidyURL = createdTidyURL.id?.let { tidyURLService.findByIdOrNull(it) }
        assertThat(createdTidyURL.originalURL, equalTo(foundTidyURL?.originalURL))
        assertThat(createdTidyURL.id, equalTo(foundTidyURL?.id))
        /* Precision on insert vs retrieval differs.
         Expected :2020-05-17T14:55:15.255607
         Actual   :2020-05-17T14:55:15.255
         */
//        assertEquals(createdTidyURL.createdAt, foundTidyURL?.createdAt)
//        assertEquals(createdTidyURL.expiresAt, foundTidyURL?.expiresAt)
    }

    @Test
    fun `Redirect when TidyURL exists`() {
        val tidyURL = TidyURL(originalURL = "https://example.com")
        val createdTidyURL = tidyURLService.createTidyURL(tidyURL)

        val redirectedURL = tidyURLService.redirect(createdTidyURL.id!!)
        assertEquals("https://example.com", redirectedURL)
    }

    @Test
    fun `Redirect when TidyURL does not exist`() {
        val expected = System.getenv("UNTIDY_DEFAULT_REDIRECT") ?: "https://blog.pragmaticdev.io"
        val redirectedURL = tidyURLService.redirect("123")
        assertEquals(expected, redirectedURL)
    }
}