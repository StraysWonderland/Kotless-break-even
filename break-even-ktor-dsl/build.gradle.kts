import io.kotless.plugin.gradle.dsl.kotless

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kotless_version: String by project

group = "info.novatec"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.3.72" apply true
    id("io.kotless") version "0.1.7-beta-4" apply true
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    implementation("io.kotless", "ktor-lang", "$kotless_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")


kotless {
    config {
        bucket = "my.kotless.bucket"

        terraform {
            profile = "my.kotless.user"
            region = "eu-west-1"
        }
    }
    webapp {
        lambda {
            kotless {
                packages = setOf("info.novatec")
            }
        }
    }
}