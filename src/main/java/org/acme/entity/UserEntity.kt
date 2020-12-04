package org.acme.entity

import org.acme.domain.User
import javax.persistence.*

@Entity
@Table(name = "user", schema = "public")
class UserEntity {
    @Id
    @GeneratedValue
    var id: Long? = null

    var name: String? = null

    var country: String? = null

    @Column(name = "country_code")
    var countryCode: String? = null

    @Column(name = "country_capital")
    var countryCapital: String? = null

    fun toDomain(): User {
        val user = User()
        user.id = this.id
        user.name = this.name
        user.countryCode = this.countryCode
        user.country = this.country
        user.countryCapital = this.countryCapital
        return user
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            val userEntity = UserEntity()
            userEntity.countryCapital = user.countryCapital
            userEntity.country = user.country
            userEntity.countryCode = user.countryCode
            userEntity.name = user.name
            userEntity.id = user.id
            return userEntity
        }
    }
}
