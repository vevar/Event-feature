import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object Modules {


    object Feature {

        const val root = ":feature"

        val auth = "$root:auth"
    }

    object Data {
        private const val root = ":sample:data"

        const val auth = "$root:auth-data"

        val list = arrayOf(auth)
    }


    object Microservice {
        const val root = ":sample:microservice"

        const val auth = "$root:auth"

        object Auth {
            const val root = "${Microservice.root}:auth"
            const val grpc = "$root:grpc"
            const val webClient = "$root:web-client"
        }

        val modules = arrayOf(
            Auth.root,
            Auth.grpc,
            Auth.webClient
        )
    }

    object Tool {
        const val root = ":Kotlin.Tool:tools"

        object Mpp {
            const val root = "${Tool.root}:mpp"

            const val errorHandling = "${root}:error"
            const val api = "${root}:api"
            const val env = "${root}:env"

        }

        object Jvm {
            private const val root = "${Tool.root}:jvm"

            const val webServer = "$root:webServer"
            const val dataBaseManager = "$root:databaseManager"
            const val microservice = "$root:microservice"

            val list = listOf(
                webServer,
                dataBaseManager,
                microservice
            )
        }

        object Js {
            const val root = "${Tool.root}:js"
            const val ui = "$root:ui"
        }
    }

}

