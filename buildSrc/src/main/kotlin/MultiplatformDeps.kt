class MultiplatformDeps(
    val android: String? = null,
    val jvm: String? = null,
    val js: String? = null,
    val common: String? = null,
    val ios: String? = null,
    val nodeJs: String? = null,
    val browser: String? = null
) {


    constructor(
        android: String? = null,
        jvm: String? = null,
        js: String? = null,
        common: String? = null,
        ios: String? = null
    ) : this(android, jvm, js, common, ios, js, js)
}