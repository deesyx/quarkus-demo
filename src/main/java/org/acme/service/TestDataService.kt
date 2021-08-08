package org.acme.service

import org.acme.domain.TestData
import org.acme.entity.TestDataEntity
import org.acme.repository.TestDataEntityRepository
import org.eclipse.microprofile.context.ManagedExecutor
import java.util.concurrent.CompletableFuture
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class TestDataService {

    @Inject
    lateinit var testDataEntityRepository: TestDataEntityRepository

    @Inject
    lateinit var managedExecutor: ManagedExecutor

    fun test() {
        val testData = TestData.create()
        testDataEntityRepository.save(TestDataEntity.ModelMapper.from(testData))
        val supplyAsync = CompletableFuture.supplyAsync({ update(testData) }, managedExecutor::execute)
    }

    private fun update(testData: TestData) {
        testData.increaseUpdateCount()
        testDataEntityRepository.update(TestDataEntity.ModelMapper.from(testData))
    }
}
