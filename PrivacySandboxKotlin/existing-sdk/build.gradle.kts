/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

// A standard android library that uses a Runtime-enabled Ad library to show "ads".
android {
    namespace = "com.existing.sdk"

    privacySandbox {
        enable = true
    }

    defaultConfig {
        minSdkPreview = "UpsideDownCakePrivacySandbox"
        compileSdkPreview = "UpsideDownCakePrivacySandbox"
        targetSdkPreview = "UpsideDownCakePrivacySandbox"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    experimentalProperties["android.privacySandboxSdk.apiGenerator"] =
            project.dependencies.create(
                    "androidx.privacysandbox.tools:tools-apigenerator:${rootProject.extra["privacy_sandbox_tools_version"]}")

    experimentalProperties["android.privacySandboxSdk.apiGenerator.generatedRuntimeDependencies"] = listOf(
            project.dependencies.create("org.jetbrains.kotlin:kotlin-stdlib:1.7.20-RC"),
            project.dependencies.create("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1"),
            project.dependencies.create("androidx.privacysandbox.activity:activity-core:${rootProject.extra["privacy_sandbox_activity_version"]}"),
            project.dependencies.create("androidx.privacysandbox.activity:activity-client:${rootProject.extra["privacy_sandbox_activity_version"]}"),
            project.dependencies.create("androidx.privacysandbox.ui:ui-core:${rootProject.extra["privacy_sandbox_ui_version"]}"),
            project.dependencies.create("androidx.privacysandbox.ui:ui-client:${rootProject.extra["privacy_sandbox_ui_version"]}"),
    )
}

dependencies {
    // Note that here we depend on the example-sdk-bundle, not the example-sdk.
    //
    // The example-sdk library contains all the logic, but apps are not compiled using its full
    // classpath. Instead, the bundle contains information about the SDK API and the Android Gradle
    // Plugin uses it to generate sources and compile the app.
    debugImplementation(project(":example-sdk-bundle"))

    // Required for backwards compatibility on devices where SDK Runtime is unavailable.
    implementation(libs.privacysandbox.sdkruntime.client)
    // This is required to display banner ads using the SandboxedUiAdapter interface.
    implementation(libs.privacysandbox.ui.core)
    implementation(libs.privacysandbox.ui.client)
    // This is required to use SDK ActivityLaunchers.
    implementation(libs.privacysandbox.activity.core)
    implementation(libs.privacysandbox.activity.client)

    implementation("androidx.appcompat:appcompat:1.6.1")
}
