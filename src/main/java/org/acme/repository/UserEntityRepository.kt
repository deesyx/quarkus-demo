package org.acme.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.acme.entity.UserEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserEntityRepository : PanacheRepository<UserEntity> {

    fun findByName(name: String): UserEntity? {
//        var userEntity = UserEntity()
//        userEntity.name = "name"
//        userEntity.id = 1
        return find("name", name).firstResult()
    }
}
