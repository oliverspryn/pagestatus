import org.gradle.api.JavaVersion

object Versions {
    // region App & Build Tool definitions

    const val COMPILE_SDK = 32
    const val MIN_SDK = 21
    const val TARGET_SDK = 32

    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.0.1"

    // endregion

    // region Core Libraries

    const val ACCOMPANIST = "0.24.10-beta"
    const val ANDROID_APPLICATION_PLUGIN = "7.2.1"
    const val ANDROID_LIBRARY_PLUGIN = "7.2.1"
    const val COMPOSE = "1.2.0-beta03"
    val JVM = JavaVersion.VERSION_11
    val JVM_STRING = JavaVersion.VERSION_11.toString()
    const val KOTLIN = "1.6.21"

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region Application

    const val ACTIVITY_COMPOSE = "1.4.0"
    const val CORE_KTX = "1.8.0"
    const val MATERIAL = "1.6.0-alpha01"
    const val MATERIAL_3 = "1.0.0-alpha13"
    const val NAVIGATION_COMPOSE = "2.4.0-beta02"

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region Unit Tests

    const val JUNIT = "4.13.2"

    // endregion
}
