plugins {
    id("java")
}

group = "com.github.hyeyoom-lecture"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    implementation("org.reflections:reflections:0.10.2")

    // Server
    implementation("org.eclipse.jetty:jetty-server:11.0.15")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}