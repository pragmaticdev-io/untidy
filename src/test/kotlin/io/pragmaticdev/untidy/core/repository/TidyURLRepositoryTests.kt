package io.pragmaticdev.untidy.core.repository

import io.pragmaticdev.untidy.core.model.TidyURL
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TidyURLRepositoryTests {

    @Autowired
    lateinit var tidyURLRepository: TidyURLRepository

    @BeforeEach
    @AfterEach
    fun cleanup() {
        tidyURLRepository.deleteAll()
    }

    @Test
    fun `When findById then return TidyURL`() {
        val google = TidyURL("xad2wSx", "https://google.com")
        tidyURLRepository.save(google)

        val found = tidyURLRepository.findById("xad2wSx")
        assertThat(found.get().id, equalTo("xad2wSx"))
    }

}