package io.pragmaticdev.untidy.core.repository

import io.pragmaticdev.untidy.core.model.TidyURL
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface TidyURLRepository : CassandraRepository<TidyURL, String> {
}