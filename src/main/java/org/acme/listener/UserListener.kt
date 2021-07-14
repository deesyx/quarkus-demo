package org.acme.listener

import io.quarkus.vertx.ConsumeEvent
import org.acme.domain.Info
import org.acme.domain.User
import org.acme.entity.UserEntity
import org.acme.exception.ErrorCode
import org.acme.exception.NotFoundException
import org.acme.repository.UserEntityRepository
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.control.ActivateRequestContext
import javax.inject.Inject

@ApplicationScoped
@ActivateRequestContext
class UserListener {

    @Inject
    lateinit var userEntityRepository: UserEntityRepository

    @ConsumeEvent("user update", blocking = true)
    fun onUserUpdate(username: String) {
        val user = User(
                name = "test",
                country = "test",
                countryCode = "test",
                countryCapital = "test",
                info = Info(a = "haha", b = LocalDateTime.now())
        )
        userEntityRepository.save(UserEntity.fromDomain(user))
        val userEntity1 = userEntityRepository.findByName(username)
                ?: throw NotFoundException(ErrorCode.USER_NOT_FOUND)
        println(userEntity1.info)
    }
}
