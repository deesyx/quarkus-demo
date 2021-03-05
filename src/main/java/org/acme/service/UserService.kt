package org.acme.service

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

}
