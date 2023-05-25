import net.ltgt.gradle.errorprone.errorprone

plugins {
    id("java")
    id("net.ltgt.errorprone")
}

val errorProneVersion: String by project
val errorProneSupportVersion: String by project
val epAutoPatch: String? by project

dependencies {
    errorprone(project(":refaster-rules"))
    errorprone("com.google.errorprone:error_prone_core:$errorProneVersion")
    errorprone("tech.picnic.error-prone-support:error-prone-contrib:$errorProneSupportVersion")
    errorprone("tech.picnic.error-prone-support:refaster-runner:$errorProneSupportVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.withType<JavaCompile>().configureEach {
    options.errorprone {
        disable(
                "JUnitClassModifiers",
                "Java8ApiChecker",
        )
        // Uncomment for whitelisting checks instead
        // disableAllChecks.set(true)
        // enable("DeadException")
        disableWarningsInGeneratedCode.set(true)
        checkOptions.put(
                // whitelisting ⬇
                // "Refaster:NamePattern", enableRefasterRulesArg(
                // blacklisting ⬇
                "Refaster:NamePattern", disableRefasterRulesArg(
                        "StringRules.StringIsNullOrEmpty"
                )
        )
        // run with ./gradlew build -PepAutoPatch
        if (epAutoPatch != null) {
            logger.lifecycle("Enabling auto-patching of error-prone checks")
            errorproneArgs.addAll(
                    patchChecksOpt(
                            "DeadException", // "MissingOverride", "DefaultCharset",
                    ),
                    "-XepPatchLocation:IN_PLACE"
            )
        }
        excludedPaths.set(excludedPathsArg(
                "${project.group}.errorprone.utils",
        ))
    }
}

tasks {
    compileTestJava {
        // options.errorprone.isEnabled.set(false)
    }
}

tasks.test {
    useJUnitPlatform()
}

// Helper functions for building errorprone command line

fun excludedPathsArg(vararg excludedPaths: String): String {
    return excludedPaths.joinToString(separator = "|",
            prefix = ".*/(",
            postfix = ")/.*")
}

fun patchChecksOpt(vararg checks: String): String {
    val checksJoined = checks.joinToString(separator = ",")
    return "-XepPatchChecks:$checksJoined"
}

fun enableRefasterRulesArg(vararg enabledRules: String): String {
    return enabledRules.joinToString(separator = "|",
            prefix = "^(",
            postfix = ").*",
            transform = { s -> "($s)".replace(".", "\\\$") })
}

fun disableRefasterRulesArg(vararg disabledRules: String): String {
    return disabledRules.joinToString(separator = "|",
            prefix = "^(?!",
            postfix = ").*",
            transform = { s -> "($s)".replace(".", "\\\$") })
}

