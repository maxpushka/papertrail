plugins {
    id("java")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

group = "com.github.trailpaper"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20231013")
    testImplementation("org.mockito:mockito-core:5.10.0")
    implementation("org.springframework:spring-context:6.1.3")
    implementation("org.springframework:spring-web:6.1.3")
    implementation("org.springframework:spring-core:6.1.3")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.2")

    implementation("org.springframework:spring-webmvc:6.1.3")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0-M1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
