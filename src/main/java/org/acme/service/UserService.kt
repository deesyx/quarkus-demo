package org.acme.service

import io.vertx.core.eventbus.EventBus
import org.acme.domain.Info
import org.acme.domain.User
import org.acme.entity.UserEntity
import org.acme.exception.ErrorCode
import org.acme.exception.NotFoundException
import org.acme.external.CountryClient
import org.acme.repository.UserEntityRepository
import org.acme.resource.UserAddRequest
import org.eclipse.microprofile.context.ManagedExecutor
import org.eclipse.microprofile.context.ThreadContext
import org.eclipse.microprofile.rest.client.inject.RestClient
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture
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

    @Inject
    lateinit var eventBus: EventBus

    @Inject
    lateinit var threadContext: ThreadContext

    @Inject
    lateinit var managedExecutor: ManagedExecutor

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

    @Transactional
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
//        eventBus.publish("user update", userEntity1.name)

        println("0: " + Thread.currentThread().name)
        val supplyAsync = CompletableFuture.supplyAsync({ onUserUpdate(userEntity1.name!!) }, managedExecutor::execute)
//        supplyAsync.join()

//        if (userEntity1.name == "a") {
//            throw IllegalArgumentException("test")
//        }

        return userEntity1.toDomain()
    }

    fun onUserUpdate(username: String): User {
        println("1: " + Thread.currentThread().name)
        val user = User(
                name = "test",
                country = "test",
                countryCode = "test",
                countryCapital = "test",
                info = Info(a = "haha", b = LocalDateTime.now())
        )
        Thread.sleep(10000L)
        userEntityRepository.save(UserEntity.fromDomain(user))
        println("sub thread finish")
//        if (user.name == "test") {
//            throw IllegalArgumentException("test")
//        }
        return user
    }

}
