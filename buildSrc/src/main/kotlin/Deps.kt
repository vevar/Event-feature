object Deps {

    object Multiplatform {

        val kotlinStdlib = MultiplatformDeps(
            jvm = Jvm.kotlinStdlib,
            common = Common.kotlinStdlib,
            js = Js.kotlinStdlib
        )

        val coroutines = MultiplatformDeps(
            jvm = Jvm.coroutines,
            common = Common.coroutines,
            js = Js.coroutines
        )

        val ktorClient = MultiplatformDeps(
            js = Js.ktorClient,
            common = Common.ktorClient,
            jvm = Jvm.ktorClient
        )

        val ktorSerialization = MultiplatformDeps(
            common = Common.ktorSerialization,
            js = Js.ktorSerialization,
            jvm = Jvm.ktorSerialization
        )
        val ktorJson = MultiplatformDeps(
            common = Common.ktorJson,
            js = Js.ktorJson,
            jvm = Jvm.ktorJson
        )

        val kodein = MultiplatformDeps(
            js = Js.kodein,
            jvm = Jvm.kodein,
            common = "org.kodein.di:kodein-di:${Versions.Kotlin.kodein}"
        )

        val kodeinConf = MultiplatformDeps(
            js = Js.kodeinConf,
            jvm = Jvm.kodeinConf,
            common = "org.kodein.di:kodein-di-conf:${Versions.Kotlin.kodein}"
        )

        val kotlinSerialization =
            MultiplatformDeps(common = "org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.serialization}")

        val kotlinSerializationRuntime =
            MultiplatformDeps(
                common = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.serializationRuntime}"
            )

        val kotlinSerializationJson = MultiplatformDeps(
            common = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.serializationJson}"
        )
    }

    object Js {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-js:${Versions.Kotlin.kotlin}"
        const val kotlinSerializationRuntime =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.Kotlin.serializationRuntime}"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.Kotlin.coroutines}"

        const val ktorClient = "io.ktor:ktor-client-js:${Versions.Kotlin.ktor}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization-js:${Versions.Kotlin.ktor}"
        const val ktorJson = "io.ktor:ktor-client-json-js:${Versions.Kotlin.ktor}"

        const val kodein = "org.kodein.di:kodein-di-js:${Versions.Kotlin.kodein}"
        const val kodeinConf = "org.kodein.di:kodein-di-conf-js:${Versions.Kotlin.kodein}"


        object Frontend {
            const val kotlinReact = "org.jetbrains:kotlin-react:${Versions.Js.Frontend.kotlinReact}"
            const val kotlinReactDom = "org.jetbrains:kotlin-react-dom:${Versions.Js.Frontend.kotlinReactDom}"
            const val kotlinReactRouting =
                "org.jetbrains:kotlin-react-router-dom:${Versions.Js.Frontend.kotlinReactRouting}"
            const val kotlinCss = "org.jetbrains:kotlin-css:${Versions.Js.Frontend.kotlinCss}"
            const val kotlinStyled = "org.jetbrains:kotlin-styled:${Versions.Js.Frontend.kotlinStyled}"
            const val kotlinHtmlJs = "org.jetbrains.kotlinx:kotlinx-html-js:0.6.12"

            object Npm {

            }
        }
    }

    object Jvm {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin.kotlin}"

        const val kotlinHTML = "org.jetbrains.kotlinx:kotlinx-html-jvm:${Versions.Kotlin.kotlinHTML}"

        //Ktor
        const val ktorServerCore = "io.ktor:ktor-server-core:${Versions.Kotlin.ktor}"
        const val ktorServetNetty = "io.ktor:ktor-server-netty:${Versions.Kotlin.ktor}"
        const val ktorGson = "io.ktor:ktor-gson:${Versions.Kotlin.ktor}"
        const val ktorHtmlBuilder = "io.ktor:ktor-html-builder:${Versions.Kotlin.ktor}"

        //Ktor test
        const val ktorServerTestHost = "io.ktor:ktor-server-test-host:${Versions.Kotlin.ktor}"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.Kotlin.coroutines}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
        const val jetbrainExposedCore = "org.jetbrains.exposed:exposed-core:${Versions.Jvm.jetbrainExposed}"
        const val jetbrainExposedDao = "org.jetbrains.exposed:exposed-dao:${Versions.Jvm.jetbrainExposed}"
        const val jetbrainExposedJdbc = "org.jetbrains.exposed:exposed-jdbc:${Versions.Jvm.jetbrainExposed}"

        const val ktorClient = "io.ktor:ktor-client-jetty:${Versions.Kotlin.ktor}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.Kotlin.ktor}"
        const val ktorJson = "io.ktor:ktor-client-json-jvm:${Versions.Kotlin.ktor}"

        const val ktorAuth = "io.ktor:ktor-auth:${Versions.Kotlin.ktor}"
        const val ktorAuthJwt = "io.ktor:ktor-auth-jwt:${Versions.Kotlin.ktor}"
        const val ktorLocation = "io.ktor:ktor-locations:${Versions.Kotlin.ktor}"

        const val koinCore = "org.koin:koin-core:${Versions.Jvm.koin}"
        const val koinExt = "org.koin:koin-core-ext:${Versions.Jvm.koin}"
        const val koinTest = "org.koin:koin-test:${Versions.Jvm.koin}"
        const val koinKtor = "org.koin:koin-ktor:${Versions.Jvm.koin}"

        const val kodein = "org.kodein.di:kodein-di-jvm:${Versions.Kotlin.kodein}"
        const val kodeinConf = "org.kodein.di:kodein-di-conf-jvm:${Versions.Kotlin.kodein}"

        const val kodeinKtorServer = "org.kodein.di:kodein-di-framework-ktor-server-jvm:${Versions.Kotlin.kodein}"
        const val hikariCp = "com.zaxxer:HikariCP:${Versions.Jvm.hikariCp}"
        const val postgreSQL = "org.postgresql:postgresql:${Versions.Jvm.postgreSQL}"
        const val pgJdbcNg = "com.impossibl.pgjdbc-ng:pgjdbc-ng:${Versions.Jvm.pgJdbcNg}"

        const val logback = "ch.qos.logback:logback-classic:1.2.3"

        // gRPC
        const val grpcNettyShaded = "io.grpc:grpc-netty-shaded:${Versions.Jvm.grpc}"
        const val grpcProtoBuf = "io.grpc:grpc-protobuf:${Versions.Jvm.grpc}"
        const val grpcStub = "io.grpc:grpc-stub:${Versions.Jvm.grpc}"
        const val grpcStubKotlin = "io.grpc:grpc-kotlin-stub:${Versions.Jvm.grpcKotlin}"

        const val grpcProtocGen = "io.grpc:protoc-gen-grpc-java:${Versions.Jvm.grpc}"
        const val grpcProtocGenKotlin = "io.grpc:protoc-gen-grpc-kotlin:${Versions.Jvm.grpcKotlin}"

        // For Protobuf
        const val protobufProtoc = "com.google.protobuf:protoc:${Versions.Jvm.protobuf}"

        const val authJwt = "com.auth0:java-jwt:3.10.3"
    }

    object Common {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.Kotlin.kotlin}"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"

        const val ktorClient = "io.ktor:ktor-client-core:${Versions.Kotlin.ktor}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.Kotlin.ktor}"
        const val ktorJson = "io.ktor:ktor-client-json:${Versions.Kotlin.ktor}"


    }
}