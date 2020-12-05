plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}



kotlin {
    js {
        browser()
    }

    jvm {
        val main by compilations.getting {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

    }

    sourceSets {
        js().compilations["main"].defaultSourceSet {
            dependencies {

                implementation(Deps.Js.Frontend.kotlinReact)
                implementation(Deps.Js.Frontend.kotlinReactDom)
                implementation(Deps.Js.Frontend.kotlinStyled)

                implementation(Deps.Js.ktorClient)
                implementation(Deps.Js.ktorJson)
                implementation(Deps.Js.ktorSerialization)


                implementation(npm("react", Versions.Js.Frontend.Npm.react))
                implementation(npm("react-dom", Versions.Js.Frontend.Npm.react))

                implementation(npm("styled-components", Versions.Js.Frontend.Npm.styledComponents))
                implementation(npm("inline-style-prefixer", Versions.Js.Frontend.Npm.inlineStylePrefixer))
                implementation(npm("css-loader", Versions.Js.Frontend.Npm.cssLoader))
                implementation(npm("style-loader", Versions.Js.Frontend.Npm.styleLoader))

                implementation(npm("@material/base", Versions.Js.Frontend.Npm.material))
                implementation(npm("@material/animation", Versions.Js.Frontend.Npm.material))
                implementation(npm("@material/feature-targeting", Versions.Js.Frontend.Npm.material))
                implementation(npm("@material/theme", Versions.Js.Frontend.Npm.material))
                implementation(npm("@material/elevation", Versions.Js.Frontend.Npm.material))


            }

        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {

                implementation(Deps.Jvm.ktorServetNetty)
                implementation(Deps.Jvm.ktorGson)
                implementation(Deps.Jvm.ktorAuthJwt)
                implementation(Deps.Jvm.ktorLocation)

                implementation(Deps.Jvm.kodeinKtorServer)
                implementation(Deps.Jvm.logback)

                implementation(project(Modules.Tool.Jvm.webServer))
            }
        }
    }
}

dependencies {
    mppSetup {
        js = true
        jvm = true

        mppLibrary(Deps.Multiplatform.kotlinStdlib)
        mppLibrary(Deps.Multiplatform.coroutines)
        mppLibrary(Deps.Multiplatform.kodein)
        mppLibrary(Deps.Multiplatform.kodeinConf)

        mppLibrary(Deps.Multiplatform.ktorSerialization)
        mppLibrary(Deps.Multiplatform.kotlinSerialization)
        mppLibrary(Deps.Multiplatform.kotlinSerializationRuntime)

        mppModule(Modules.Tool.Mpp.errorHandling)

    }
}

