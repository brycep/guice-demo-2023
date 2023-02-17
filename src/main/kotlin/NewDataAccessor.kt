
open class NewDataAccessor: Accessor {

    override fun sendMessage(echo: String): String {
        println("Hello, this is the new data accessor")
        return echo
    }
}