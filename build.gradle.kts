import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    jacoco
}

group = "io.pragmaticdev"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    jcenter()
}

val springVersion = "2.3.0.RELEASE"
dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    // Spring Boot
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web", version = springVersion)
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-actuator", version = springVersion)
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-cassandra", version = springVersion)
    developmentOnly(group = "org.springframework.boot", name = "spring-boot-devtools", version = springVersion)
    testImplementation(group = "org.springframework.boot", name = "spring-boot-starter-test", version = springVersion) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // Java Help
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation(group = "org.cassandraunit", name = "cassandra-unit-spring", version = "4.3.1.0") {
        exclude(group = "org.cassandraunit", module = "cassandra-unit")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.isEnabled = true
        csv.isEnabled = true
        html.destination = file("${buildDir}/jacocoHtml")
    }
}