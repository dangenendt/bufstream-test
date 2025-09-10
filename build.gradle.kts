plugins {
    id("com.google.protobuf") version "0.9.4"
    kotlin("jvm") version "2.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.protobuf:protobuf-java:4.28.2")
    implementation("org.apache.kafka:kafka-clients:3.8.0")
    implementation("build.buf:protovalidate:0.14.0")
    testImplementation(kotlin("test"))
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.2"
    }
}

tasks.test {
    useJUnitPlatform()
}

val bufOutDir = layout.projectDirectory.dir("gen/buf/java")

kotlin {
    jvmToolchain(24)
}

sourceSets {
    named("main") {
        java.srcDir(bufOutDir)
    }
}