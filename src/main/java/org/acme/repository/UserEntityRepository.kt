package org.acme.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.acme.entity.UserEntity
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class UserEntityRepository : PanacheRepository<UserEntity> {

    fun findByName(name: String): UserEntity? {
        return find("name", name).firstResult()
    }

    @Transactional
    fun save(userEntity: UserEntity) {
        return this.persist(userEntity)
    }

    @Transactional
    fun update(userEntity: UserEntity) {
        update("set info = ?1 where name = ?2", userEntity.info!!, userEntity.name!!)
    }
}
