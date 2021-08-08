package org.acme.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.acme.entity.TestDataEntity
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class TestDataEntityRepository : PanacheRepository<TestDataEntity> {

    fun findByName(key: String): TestDataEntity? {
        return find("key", key).firstResult()
    }

    @Transactional
    fun save(testDataEntity: TestDataEntity) {
        return this.persist(testDataEntity)
    }

    @Transactional
    fun update(testDataEntity: TestDataEntity) {
        update("set updatedCount = ?1 where key = ?2",
                testDataEntity.updatedCount!!, testDataEntity.key!!)
    }
}
