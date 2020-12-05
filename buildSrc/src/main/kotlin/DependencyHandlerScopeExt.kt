import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project


fun MppDepsSettings.mppModule(name: String) {
    with(dependencyScope) {
        if (android) "androidMainImplementation"(dependencies.project(path = name))
        if (common) "commonMainApi"(dependencies.project(path = name))
        if (ios) "iosX64MainImplementation"(dependencies.project(path = name))
        if (ios) "iosArm64MainImplementation"(dependencies.project(path = name))
        if (browser) "browserMainImplementation"(dependencies.project(path = name))
        if (nodeJs) "nodeJsMainImplementation"(dependencies.project(path = name))
        if (js) "jsMainImplementation"(dependencies.project(path = name))
        if (jvm) "jvmMainImplementation"(dependencies.project(name))
    }
}

fun DependencyHandlerScope.androidModule(name: String) {
    "androidMainImplementation"(dependencies.project(path = name))
}

fun DependencyHandlerScope.projectModule(name: String) {
    "implementation"(dependencies.project(path = name))
}

class MppDepsSettings(
    val dependencyScope: DependencyHandlerScope
) {
    var android: Boolean = false
    var jvm: Boolean = false
    var js: Boolean = false
    var common: Boolean = true
    var ios: Boolean = false
    var nodeJs: Boolean = false
    var browser: Boolean = false
}

fun DependencyHandlerScope.mppSetup(block: MppDepsSettings.() -> Unit) {
    MppDepsSettings(this).block()
}


fun MppDepsSettings.mppLibrary(dependency: MultiplatformDeps) {
    with(dependencyScope) {
        if (common) dependency.common?.let { "commonMainApi"(it) }
        if (nodeJs) dependency.nodeJs?.let { "nodeJsMainImplementation"(it) }
        if (browser) dependency.browser?.let { "browserMainImplementation"(it) }
        if (js) dependency.js?.let { "jsMainImplementation"(it) }
        if (android) dependency.android?.let { "androidMainImplementation"(it) }
        if (jvm) dependency.jvm?.let { "jvmMainImplementation"(it) }
        if (ios) dependency.ios?.let {
            "iosX64MainImplementation"(it)
            "iosArm64MainImplementation"(it)
        }
    }
}

fun DependencyHandlerScope.androidLibrary(name: String) {
    "androidMainImplementation"(name)
}

fun DependencyHandlerScope.kaptLibrary(name: String) {
    "kapt"(name)
}
