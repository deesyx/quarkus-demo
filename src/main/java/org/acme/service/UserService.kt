package org.acme.service

import org.acme.domain.User
import org.acme.entity.UserEntity
import org.acme.exception.BadRequestException
import org.acme.exception.ErrorCode
import org.acme.exception.NotFoundException
import org.acme.exception.ServerException
import org.acme.external.Country
import org.acme.external.CountryClient
import org.acme.repository.UserEntityRepository
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
class UserService {

    @Inject
    lateinit var userEntityRepository: UserEntityRepository

    @Inject
    @RestClient
    lateinit var countryClient: CountryClient

    fun addUser(name: String): User {
        val user = User()
        user.countryCapital = "test"
        user.countryCode = "test"
        user.country = "test"
        user.name = name

        userEntityRepository.save(UserEntity.fromDomain(user))
        return user
    }

    fun getUser(name: String): User {
        val userEntity: UserEntity = userEntityRepository.findByName(name)
                ?: throw NotFoundException(ErrorCode.USER_NOT_FOUND)
        return userEntity.toDomain()
    }

}
