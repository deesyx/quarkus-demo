package org.acme.entity

import io.quarkiverse.hibernate.types.json.JsonType
import io.quarkiverse.hibernate.types.json.JsonTypes
import org.acme.domain.Info
import org.acme.domain.User
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.*

@Entity
@Table(name = "user", schema = "public")
@TypeDef(
        name = JsonTypes.JSON,
        typeClass = JsonType::class
)
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    var id: Long? = null

    var name: String? = null

    var country: String? = null

    @Column(name = "country_code")
    var countryCode: String? = null

    @Column(name = "country_capital")
    var countryCapital: String? = null

    @Type(type = JsonTypes.JSON)
    @Column(columnDefinition = "jsonb")
    var info: Info? = null

    fun toDomain(): User {
        return User(
                id = this.id,
                name = this.name,
                countryCode = this.countryCode,
                country = this.country,
                countryCapital = this.countryCapital,
                info = this.info
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            val userEntity = UserEntity()
            userEntity.id = user.id
            userEntity.countryCapital = user.countryCapital
            userEntity.country = user.country
            userEntity.countryCode = user.countryCode
            userEntity.name = user.name
            userEntity.info = user.info
            return userEntity
        }
    }
}
