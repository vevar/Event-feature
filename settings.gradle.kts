rootProject.name = "Event-feature"



object Modules {

    object Feature {

        private val root = ":feature"

        val event = "${root}:event"

        val list: List<String> = listOf(event)

    }

    object Data {
    }

    object Microservice {

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

Modules.Feature.list.forEach { include(it) }

include(Modules.Tool.Mpp.api)
include(Modules.Tool.Mpp.errorHandling)
include(Modules.Tool.Mpp.env)

include(Modules.Tool.Jvm.webServer)
include(Modules.Tool.Jvm.dataBaseManager)

