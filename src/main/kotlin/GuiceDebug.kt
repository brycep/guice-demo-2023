
import java.util.logging.*


/**
 * Enable or disable Guice debug output
 * on the console.
 */
object GuiceDebug {
    private var HANDLER: Handler? = null

    init {
        HANDLER = StreamHandler(System.out, object : Formatter() {
            override fun format(record: LogRecord): String {
                return String.format(
                    "[Guice %s] %s%n", record.level.name, record.message
                )
            }
        })
        HANDLER?.setLevel(Level.ALL)
    }

    val logger: Logger
        get() = Logger.getLogger("com.google.inject")

    fun enable() {
        val guiceLogger: Logger = logger
        guiceLogger.addHandler(HANDLER)
        guiceLogger.setLevel(Level.ALL)
    }

    fun disable() {
        val guiceLogger: Logger = logger
        guiceLogger.setLevel(Level.OFF)
        guiceLogger.removeHandler(HANDLER)
    }
}