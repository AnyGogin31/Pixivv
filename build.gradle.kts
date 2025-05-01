import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.ktlint) apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<KtlintExtension> {
        android.set(true)
        verbose.set(true)
        outputToConsole.set(true)
        reporters {
            reporter(ReporterType.CHECKSTYLE)
            reporter(ReporterType.PLAIN)
        }
    }
}
