import com.google.inject.Guice
import com.google.inject.grapher.graphviz.GraphvizGrapher
import com.google.inject.grapher.graphviz.GraphvizModule
import java.io.File
import java.io.PrintWriter

fun main(args: Array<String>) {

    val injector = Guice.createInjector(PlaygroundModule())
    val myService = injector.getInstance(MyService::class.java)

    println("Output: ${myService.sendMessage("My test message")}")

    println("Message saved: ${DataAccessorInterceptor.lastMessage}")


    // Output a graph displaying the current Guice context
    val graphVizInjector = Guice.createInjector(GraphvizModule())
    val grapher = graphVizInjector.getInstance(GraphvizGrapher::class.java)

    grapher.setOut(PrintWriter(File("guice-graph.dot"), "UTF-8"))
    grapher.setRankdir("TB")
    grapher.graph(injector)
}