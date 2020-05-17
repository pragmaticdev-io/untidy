package io.pragmaticdev.untidy.core.service

import io.pragmaticdev.untidy.core.model.TidyURL
import io.pragmaticdev.untidy.core.repository.TidyURLRepository
import io.pragmaticdev.untidy.extension.md5
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class TidyURLService {

    @Autowired
    lateinit var tidyURLRepository: TidyURLRepository

    fun createTidyURL(tidyURL: TidyURL): TidyURL {
        var id = hash(tidyURL.originalURL)
        while (tidyURLRepository.existsById(id)) {
            id = hash(tidyURL.originalURL)
        }
        return tidyURLRepository.save(tidyURL.copy(id = id))
    }

    fun findByIdOrNull(id: String): TidyURL? {
        return tidyURLRepository.findByIdOrNull(id)
    }

    fun redirect(id: String) =
            tidyURLRepository.findByIdOrNull(id)?.originalURL
                    ?: System.getenv("UNTIDY_DEFAULT_REDIRECT")
                    ?: "https://blog.pragmaticdev.io"
}

fun hash(s: String): String {
    val uuid = UUID.randomUUID()
    return Base64.getUrlEncoder().encodeToString((s + uuid).md5().toByteArray()).substring(0, 7)
}