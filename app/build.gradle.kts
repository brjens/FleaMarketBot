plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    // Apply the shadow plugin to create a JAR with dependencies.
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(kotlin("stdlib"))

    // This dependency is used by the application.
    implementation(libs.guava)

    // This dependency is for JDA ( Java Discord )
    implementation("net.dv8tion:JDA:5.0.0-beta.20")

    //this dependacy is used for json-simple, a json Java library
    implementation ("com.googlecode.json-simple:json-simple:1.1.1")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    // Define the main class for the application.
    mainClass = "org.example.App"
}

tasks.test {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

tasks {
    // Create a fat JAR using the Shadow plugin
    shadowJar {
        archiveFileName.set("app.jar")
        destinationDirectory.set(file("build/libs"))
        manifest {
            attributes["Main-Class"] = "org.example.App"
        }
    }
}