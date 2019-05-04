import com.github.jengelman.gradle.plugins.shadow.ShadowExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.netflix.gradle.plugins.packaging.ProjectPackagingExtension
import com.netflix.gradle.plugins.packaging.SystemPackagingTask
import com.netflix.gradle.plugins.rpm.Rpm

plugins {
    id("application")
    id("nebula.ospackage") version "6.2.0"
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

dependencies {
    implementation(project(":native"))
}

application {
    mainClassName = "com.github.lemartin.sdservice.example.ExampleKt"
}

val shadowJar = tasks.getByName<ShadowJar>("shadowJar") {
    archiveFileName.set("sd-service-example.jar")
}

tasks.getByName("buildDeb")

extensions.configure<ProjectPackagingExtension> {
    packageName = "sd-service-example"

    from(".")
        .include("sd-service-example.service")
        .into("/usr/lib/systemd/system")

    from(shadowJar.outputs)
        .into("/opt/sd-service")

    postInstall(file("post-install.sh"))
    preUninstall(file("pre-uninstall.sh"))
    postUninstall(file("post-uninstall.sh"))
}

tasks.withType<SystemPackagingTask> {
    dependsOn(shadowJar)
}