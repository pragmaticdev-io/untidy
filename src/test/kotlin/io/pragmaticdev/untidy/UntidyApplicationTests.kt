package io.pragmaticdev.untidy

import org.cassandraunit.spring.CassandraDataSet
import org.cassandraunit.spring.CassandraUnit
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener


@CassandraDataSet(keyspace = "unitTest", value = ["cql/dataset.cql"])
@CassandraUnit
@TestExecutionListeners(value = [CassandraUnitDependencyInjectionTestExecutionListener::class, DependencyInjectionTestExecutionListener::class])
@SpringBootTest
class UntidyApplicationTests {

    @Test
    fun contextLoads() {
    }
}
