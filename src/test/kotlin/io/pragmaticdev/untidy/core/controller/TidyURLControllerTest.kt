package io.pragmaticdev.untidy.core.controller

import io.pragmaticdev.untidy.core.model.TidyURL
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@SpringBootTest
internal class TidyURLControllerTest {

    @Autowired
    lateinit var tidyURLController: TidyURLController

    @Test
    fun createTidyURL() {
        val now = LocalDateTime.now()

        val tidyURL = TidyURL(originalURL = "https://example.com")
        val createdTidyURL = tidyURLController.createTidyURL(tidyURL)

        assertThat(createdTidyURL.originalURL, Matchers.equalTo(tidyURL.originalURL))
        assertThat(createdTidyURL.id, Matchers.notNullValue())
        assertThat(createdTidyURL.createdAt, Matchers.notNullValue())
        kotlin.test.assertTrue(createdTidyURL.createdAt.isAfter(now))
        assertThat(createdTidyURL.expiresAt, Matchers.notNullValue())
        kotlin.test.assertTrue(createdTidyURL.createdAt.isAfter(now))
    }

    @Test
    fun getTidyURL() {
        val tidyURL = TidyURL(originalURL = "https://example.com")
        val createdTidyURL = tidyURLController.createTidyURL(tidyURL)

        val foundTidyURL = createdTidyURL.id?.let { tidyURLController.getTidyURL(it) }
        assertThat(createdTidyURL.originalURL, Matchers.equalTo(foundTidyURL?.originalURL))
        assertThat(createdTidyURL.id, Matchers.equalTo(foundTidyURL?.id))

        assertThrows<ResponseStatusException> {
            tidyURLController.getTidyURL("///")
        }
    }
}