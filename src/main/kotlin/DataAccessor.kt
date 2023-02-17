open class DataAccessor: Accessor {

// Without the "open" keyword, Kotlin creates a class that is final by default. A final class
// prevents Guice from being able to create a subclass of this class in order to inject the @MessageSaver interceptor
// class DataAccessor<DataType>: Accessor<DataType> {

    @MessageSaver
    override fun sendMessage(echo: String): String {
        return echo
    }
}