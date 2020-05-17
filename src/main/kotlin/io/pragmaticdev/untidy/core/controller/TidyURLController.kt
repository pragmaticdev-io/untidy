package io.pragmaticdev.untidy.core.controller

import io.pragmaticdev.untidy.core.model.TidyURL
import io.pragmaticdev.untidy.core.service.TidyURLService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/v0")
class TidyURLController {

    @Autowired
    lateinit var tidyURLService: TidyURLService

    @PostMapping("/tidylink")
    @ResponseStatus(HttpStatus.CREATED)
    fun createTidyURL(@RequestBody tidyURL: TidyURL) = tidyURLService.createTidyURL(tidyURL)

    @GetMapping("/tidylink/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTidyURL(@PathVariable id: String) = tidyURLService.findByIdOrNull(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "$id not found.")

}

@RestController
@RequestMapping("/")
class RedirectToOriginalController {

    @Autowired
    lateinit var tidyURLService: TidyURLService

    @GetMapping("/{id}")
    fun redirectToOriginalURL(@PathVariable id: String, httpServletResponse: HttpServletResponse) =
            httpServletResponse.sendRedirect(tidyURLService.redirect(id))

}