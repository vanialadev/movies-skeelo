import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    apply(plugin = rootProject.libs.plugins.ktlint.get().pluginId)
    apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)

    tasks.withType<Detekt>().configureEach {
        buildUponDefaultConfig = true
        allRules = true
        config.setFrom("${rootProject.rootDir}/config/detekt/detekt.yml")
        baseline = file("${rootProject.rootDir}/config/detekt/baseline.xml")
    }
    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(true)
            html.outputLocation.set(file("$projectDir/config/detekt/reports/detekt.html"))
        }
    }

    afterEvaluate {
        tasks.findByName("preBuild")
            ?.dependsOn("ktlintFormat")
            ?.dependsOn("detekt")
    }
}
