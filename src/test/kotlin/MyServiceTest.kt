import com.google.inject.Guice
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MyServiceTest {

    private lateinit var myService: MyService
    private lateinit var dataAccessorInterceptor: DataAccessorInterceptor

    @BeforeEach
    fun setupGuice() {
        GuiceDebug.enable()
        val injector = Guice.createInjector(PlaygroundModule())
        myService = injector.getInstance(MyService::class.java)
        dataAccessorInterceptor = injector.getInstance(DataAccessorInterceptor::class.java)
    }

    @Test
    fun testMyInterceptor() {
        myService.sendMessage("My test message")
        assertEquals("My test message", DataAccessorInterceptor.lastMessage)
    }

}