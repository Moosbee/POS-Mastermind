plugins {
    id("java")
}

apply(plugin = "application")

group = "pos.mastermind"
version = "0.0.1"

configure<JavaApplication> {
    mainClass = "pos.mastermind.Main"
}


tasks {
    jar {
        manifest.attributes["Main-Class"] = "pos.mastermind.Main"
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}