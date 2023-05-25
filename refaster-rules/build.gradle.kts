plugins {
    id("java")
    id("net.ltgt.errorprone")
}

val errorProneVersion: String by project
val errorProneSupportVersion: String by project

dependencies {
    implementation("junit:junit:4.13.2")
    compileOnly("com.google.errorprone:error_prone_refaster:$errorProneVersion")
    compileOnly("tech.picnic.error-prone-support:refaster-support:$errorProneSupportVersion")
    annotationProcessor("tech.picnic.error-prone-support:refaster-compiler:$errorProneSupportVersion")
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xplugin:RefasterRuleCompiler")
}
