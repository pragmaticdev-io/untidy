package io.pragmaticdev.untidy

import org.springframework.data.repository.CrudRepository

interface TidyURLRepository : CrudRepository<TidyURL, String> {
}