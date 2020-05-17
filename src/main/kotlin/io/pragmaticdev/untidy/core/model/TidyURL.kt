package io.pragmaticdev.untidy.core.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDateTime

@Table("url")
data class TidyURL(
        @PrimaryKey val id: String? = null,
        @JsonProperty(value = "original_url") @Column("original_url") val originalURL: String,
        @JsonProperty(value = "created_at") @Column("created_at") val createdAt: LocalDateTime = LocalDateTime.now(),
        @JsonProperty(value = "expires_at") @Column("expires_at") val expiresAt: LocalDateTime = LocalDateTime.now().plusYears(2L)
)