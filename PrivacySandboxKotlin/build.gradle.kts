//// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("kotlin_version", "1.9.10")
        set("ksp_version", "1.9.10-1.0.13")
        set("privacy_sandbox_activity_version", "1.0.0-alpha01")
        set("privacy_sandbox_sdk_runtime_version", "1.0.0-alpha13")
        set("privacy_sandbox_tools_version", "1.0.0-alpha07")
        set("privacy_sandbox_ui_version", "1.0.0-alpha07")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

    // These two plugins do annotation processing and code generation for the sdk-implementation.
    alias(libs.plugins.privacysandbox.library) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
}