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

    // Create a task to build a JAR file with dependencies
    val fatJar by creating(Jar::class) {
        archiveClassifier.set("fat")
        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
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
    implementation("com.googlecode.lanterna:lanterna:3.1.1")
    
}

tasks.test {
    useJUnitPlatform()
}