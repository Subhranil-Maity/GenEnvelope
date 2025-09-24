import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.room)
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
}

kotlin {
    jvm("desktop")
    jvmToolchain(21)

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.room.runtime)
//            implementation(libs.androidx.room.paging)
//            implementation(libs.androidx.room.compiler)
            implementation(libs.sqlite.bundled)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation("org.slf4j:slf4j-simple:2.0.13")
        }
    }
}
compose.desktop {
    application {
        mainClass = "com.subhranil.envelope.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "GenEnvelope"
            packageVersion = "2.0.0"
            windows{
                iconFile.set(project.file("src/desktopMain/composeResources/drawable/icon.ico"))
                shortcut = true
                menu = true
            }
        }
    }
}
room{
    schemaDirectory("$projectDir/schemas")
}
dependencies{
    ksp(libs.androidx.room.compiler)
}
