package org.acme.service

import org.acme.domain.User
import org.acme.entity.UserEntity
import org.acme.exception.ErrorCode
import org.acme.exception.NotFoundException
import org.acme.repository.UserEntityRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class UserService {

    @Inject
    lateinit var userEntityRepository: UserEntityRepository

    fun getUser(name: String): User {
        val userEntity: UserEntity = userEntityRepository.findByName(name) ?: throw NotFoundException(ErrorCode.USER_NOT_FOUND)
        return userEntity.toDomain()
    }

}
