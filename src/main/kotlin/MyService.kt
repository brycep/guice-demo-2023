import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MyService @Inject constructor(
    private val accessor: Accessor
) {
    fun sendMessage(message:String): String {
        return accessor.sendMessage(message)
    }
}