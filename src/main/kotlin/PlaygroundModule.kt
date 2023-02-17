import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.matcher.Matchers

class PlaygroundModule: AbstractModule() {
    override fun configure() {
        bind(Accessor::class.java).to(DataAccessor::class.java)

        val dataAccessorInterceptor = DataAccessorInterceptor()
        requestInjection(dataAccessorInterceptor)

        bindInterceptor(
            Matchers.subclassesOf(Accessor::class.java),
            Matchers.annotatedWith(MessageSaver::class.java),
            dataAccessorInterceptor
        )
    }

    // This provides function breaks the interceptor. When we create a new DataAccessor instance
    // in the provider method and pass it into our MyService class, we don't give Guice a chance
    // to build and weave the DataAccessorInterceptor implementation into our DataAccessor class.
    @Singleton
    @Provides
    fun buildMyServiceBuggyVersion(): MyService {
        return MyService(DataAccessor())
    }

//    @Singleton
//    @Provides
//    fun buildMyService(accessor: Accessor): MyService {
//        return MyService(accessor)
//    }

}
