plugins {
	id 'fabric-loom' version '1.7-SNAPSHOT'
	id 'maven-publish'
}

version = '1.0.0'
group = 'com.chaoscube'
archivesBaseName = 'chaoscube'

repositories {
	maven { url 'https://maven.fabricmc.net/' }
}

dependencies {
	// Use the Minecraft version string that Fabric uses for 26.2
	// Check https://fabricmc.net/develop/ for exact mappings version
	minecraft 'com.mojang:minecraft:26.2'
	mappings "net.fabricmc:yarn:26.2+build.1:v2"
	modImplementation "net.fabricmc:fabric-loader:0.16.0"
	modImplementation "net.fabricmc.fabric-api:fabric-api:0.100.0+26.2"
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
	withSourcesJar()
}

jar {
	from("LICENSE") { rename { "${it}_${archivesBaseName}" } }
}

// Update mappings when exact yarn build is known
// Visit: https://fabricmc.net/develop/ and select Minecraft 26.2
