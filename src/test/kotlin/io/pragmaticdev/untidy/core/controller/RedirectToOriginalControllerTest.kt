package io.pragmaticdev.untidy.core.controller

import io.pragmaticdev.untidy.core.model.TidyURL
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.servlet.http.HttpServletResponse

@SpringBootTest
internal class RedirectToOriginalControllerTest {

    @Autowired
    lateinit var redirectToOriginalController: RedirectToOriginalController

    @Autowired
    lateinit var tidyURLController: TidyURLController

    @Test
    fun redirectToOriginalURL() {
        val tidyURL = TidyURL(originalURL = "https://example.com")
        val createdTidyURL = tidyURLController.createTidyURL(tidyURL)

        val response: HttpServletResponse = mock(HttpServletResponse::class.java)
        redirectToOriginalController.redirectToOriginalURL(createdTidyURL.id!!, response)
        verify(response).sendRedirect("https://example.com")
    }
}