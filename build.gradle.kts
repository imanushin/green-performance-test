plugins {
    java
    jacoco
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.me.champeau.jmh)
    alias(libs.plugins.com.github.ben.manes.versions)
    alias(libs.plugins.io.gitlab.arturbosch.detekt)
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.coroutines)
    api(kotlin("stdlib"))

    testRuntimeOnly(kotlin("reflect")) // to see function names in tests
    testImplementation(libs.jupiter)
}

// there are several issues with JMH plugin on Windows (it doesn't have fork method, so jmh tries to simulates that):
// 1. gradle daemons should be stopped before, so execute .\gradlew.bat --stop before
// 2. jmh plugin is unable to compile code incrementally, so execute .\gradlew.bat clean before
// 3. jmh plugin has huge classpath, to change GRADLE_HOME folder to short path, like C:\g\
jmh {
    jmhVersion = libs.versions.jmh.get()
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${libs.versions.detekt.get()}")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.required = false
    }
}
