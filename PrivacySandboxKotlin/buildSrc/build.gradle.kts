plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal() // so that external plugins can be resolved in dependencies section
}

dependencies {
    // Version Catalog Access in conventions
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.protobuf.gradle.plugin)
    // Jfrog is causing the problem:
    // java.lang.NoSuchMethodError: 'com.google.common.collect.ImmutableMap com.google.common.collect.ImmutableMap$Builder.buildOrThrow()'
    implementation(libs.jfrog.gradle.plugin)
}
