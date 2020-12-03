package org.acme.entity

import org.acme.domain.User
import javax.persistence.*

@Entity
@Table(name = "user", schema = "public")
class UserEntity {
    @Id
    @GeneratedValue
    var id: Long? = null

    lateinit var name: String

    fun toDomain(): User {
        return User(this.id, this.name)
    }
}
