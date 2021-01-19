package org.acme.resource

import kotlinx.coroutines.*
import org.eclipse.microprofile.context.ManagedExecutor
import java.time.LocalDateTime
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import kotlin.coroutines.EmptyCoroutineContext

@Path("/thread")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ThreadResource {

    @Inject
    lateinit var exec: ManagedExecutor

    @GET
    @Path("/test")
    fun test() {
        println(Thread.currentThread().name)
        println(Thread.activeCount())
        exec.execute { println(Thread.currentThread().name) }
        println(Thread.activeCount())
        println(exec.toString())
    }

    @GET
    fun test2() = runBlocking {
        println(Thread.currentThread().name)
        val list = listOf(1, 2, 3, 4, 5)
        println(LocalDateTime.now())
        val coroutineScope = CoroutineScope(EmptyCoroutineContext)

//        val ans = exec.asCoroutineDispatcher()
//                .use { dispatcher ->
//                    list.map { coroutineScope.async(dispatcher) {
//                            println(Thread
//                                    .currentThread().name)
//                            doSomething(it)
//                        }
//                    }.map {
//                        it.await()
//                    }.toList()
//                }


        val ans = list.map {
            coroutineScope.async(exec.asCoroutineDispatcher()) {
                println(Thread.currentThread().name)
                doSomething(it)
            }
        }.map {
            it.await()
        }.toList()

        // 这里不是async后马上await
//        val ans = list.map {
//            withContext(exec.asCoroutineDispatcher()) {
//                println(Thread
//                        .currentThread().name)
//                doSomething(it)
//            }
//        }.toList()

        println(ans)
        println(LocalDateTime.now())
    }

    private fun doSomething(n: Int): Int {
        Thread.sleep(1000)
        return n
    }
}
