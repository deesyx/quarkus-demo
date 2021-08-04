package org.acme.listener

import io.quarkus.vertx.ConsumeEvent
import org.acme.domain.Info
import org.acme.domain.User
import org.acme.entity.UserEntity
import org.acme.exception.ErrorCode
import org.acme.exception.NotFoundException
import org.acme.repository.UserEntityRepository
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.control.ActivateRequestContext
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
@ActivateRequestContext
class UserListener {

    @Inject
    lateinit var userEntityRepository: UserEntityRepository

    @Transactional
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
        throw IllegalArgumentException("test")
    }
}
