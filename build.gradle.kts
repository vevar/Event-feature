allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
        mavenCentral()
    }
}

buildscript {
    repositories {
        jcenter()
        mavenCentral()
        maven { url = java.net.URI("https://plugins.gradle.org/m2/") }
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = Versions.Kotlin.kotlin))
        classpath(kotlin("serialization", version = Versions.Kotlin.kotlin))
    }
}