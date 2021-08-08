package org.acme.service

import org.acme.domain.TestData
import org.acme.entity.TestDataEntity
import org.acme.repository.TestDataEntityRepository
import org.eclipse.microprofile.context.ManagedExecutor
import org.eclipse.microprofile.context.ThreadContext
import java.lang.IllegalArgumentException
import java.util.concurrent.CompletableFuture
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.transaction.UserTransaction

@Singleton
class TestDataService {

    @Inject
    lateinit var testDataEntityRepository: TestDataEntityRepository

    @Inject
    lateinit var managedExecutor: ManagedExecutor

    @Inject
    lateinit var transaction: UserTransaction

    @Inject
    lateinit var threadContext: ThreadContext

    @Transactional
    fun test() {
        println("thread: ${Thread.currentThread().name}, transaction: $transaction")
        val testData = TestData.create()
        testDataEntityRepository.save(TestDataEntity.ModelMapper.from(testData))
        val supplyAsync = CompletableFuture.supplyAsync({ update(testData) }, managedExecutor::execute)
//        supplyAsync.join()
//        throw IllegalArgumentException("test")
    }

    private fun update(testData: TestData) {
        println("sub-thread-1: ${Thread.currentThread().name}, transaction: $transaction")
        testData.increaseUpdateCount()
        Thread.sleep(5000)
        println("sub-thread-2: ${Thread.currentThread().name}, transaction: $transaction")
        testDataEntityRepository.update(TestDataEntity.ModelMapper.from(testData))
    }
}
