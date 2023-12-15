plugins {
	java
	id("org.springframework.boot") version "3.1.6"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.narensoft"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-activemq")
	implementation("jakarta.jms:jakarta.jms-api:3.1.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}
