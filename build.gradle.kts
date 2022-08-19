plugins {
    id("java")
    id("application")
}

group = "io.github.karen-olson"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "echoServer.Main"
    }
}

application {
    mainClass.set("src/main/java/echoServer/Main.java")
}
