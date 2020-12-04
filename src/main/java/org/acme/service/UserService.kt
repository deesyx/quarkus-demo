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

    fun addUser(name: String, countryName: String): User {
        val countries: List<Country>
        try {
            countries = countryClient.getByName(countryName)
        } catch (e: Exception) {
            throw ServerException(ErrorCode.GET_COUNTRY_FAILED)
        }

        if (countries.isEmpty()) {
            throw BadRequestException(ErrorCode.COUNTRY_NOT_VALID)
        }

        val country = countries[0]
        val user = User()
        user.countryCapital = country.capital
        user.countryCode = country.alpha2Code
        user.country = country.name
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
