import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
}

allprojects {
    group = "com.github.lemartin"
    version = "1.0-SNAPSHOT"

    apply(plugin = "idea")
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}