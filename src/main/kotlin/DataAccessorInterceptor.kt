import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

open class DataAccessorInterceptor: MethodInterceptor {

    companion object {
        var lastMessage: String? = null
    }

    override fun invoke(invocation: MethodInvocation?): Any? {
        println("Called interceptor")
        lastMessage = invocation?.arguments?.get(0) as? String
        return invocation?.proceed()
    }
}

@Target(AnnotationTarget.FUNCTION)
annotation class MessageSaver