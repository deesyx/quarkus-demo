package org.acme.service

import io.vertx.core.eventbus.EventBus
import org.acme.domain.Info
import org.acme.domain.User
import org.acme.entity.UserEntity
import org.acme.exception.BadRequestException
import org.acme.exception.ErrorCode
import org.acme.exception.NotFoundException
import org.acme.exception.ServerException
import org.acme.external.Country
import org.acme.external.CountryClient
import org.acme.repository.UserEntityRepository
import org.acme.resource.UserAddRequest
import org.eclipse.microprofile.rest.client.inject.RestClient
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class UserService {

    @Inject
    lateinit var userEntityRepository: UserEntityRepository

    @Inject
    @RestClient
    lateinit var countryClient: CountryClient

    @Inject
    lateinit var eventBus: EventBus

    fun addUser(userAddRequest: UserAddRequest): User {

        val country = countryClient.getByName(userAddRequest.country!!)[0]

        val user = User(
                name = userAddRequest.name,
                country = country.name,
                countryCode = country.alpha2Code,
                countryCapital = country.capital,
                info = Info(a = "haha", b = LocalDateTime.now())
        )
        userEntityRepository.save(UserEntity.fromDomain(user))
        return user
    }

    fun getUser(name: String): User {
        val userEntity: UserEntity = userEntityRepository.findByName(name)
                ?: throw NotFoundException(ErrorCode.USER_NOT_FOUND)
        return userEntity.toDomain()
    }

    fun updateUser(name: String): User {
        val userEntity = userEntityRepository.findByName(name)
                ?: throw NotFoundException(ErrorCode.USER_NOT_FOUND)
        userEntity.info!!.a = userEntity.info!!.a + "1"
        val newUserEntity = UserEntity().apply {
            this.id = userEntity.id
            this.countryCapital = userEntity.countryCapital
            this.country = userEntity.country
            this.countryCode = userEntity.countryCode
            this.name = userEntity.name
            this.info = userEntity.info
        }
        userEntityRepository.update(newUserEntity)
        val userEntity1 = (userEntityRepository.findByName(name)
                ?: throw NotFoundException(ErrorCode.USER_NOT_FOUND))
        eventBus.publish("user update", userEntity1.name)
        eventBus.publish("user update", userEntity1.name)
        eventBus.publish("user update", userEntity1.name)
        return userEntity1.toDomain()
    }

}
