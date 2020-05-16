package io.pragmaticdev.untidy

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDateTime

@Table("untidy_url")
data class TidyURL(
        @PrimaryKey var id: String,
        @JsonProperty(value = "original_url") @Column("original_url") var originalURL: String,
        @JsonProperty(value = "created_at") @Column("created_at") var createdAt: LocalDateTime = LocalDateTime.now(),
        @JsonProperty(value = "expires_at") @Column("expires_at") var expiresAt: LocalDateTime = LocalDateTime.now().plusYears(2L)
)