package org.acme.entity

import org.acme.domain.TestData
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "test_data", schema = "public")
class TestDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_data_id_seq")
    var id: Long? = null

    @JoinColumn
    var key: String? = null

    @Column(name = "updated_count")
    var updatedCount: Int? = null

    fun toDomain(): TestData {
        return TestData(
                key = this.key,
                updatedCount = this.updatedCount
        )
    }

    object ModelMapper {
        fun from(testData: TestData): TestDataEntity {
            return TestDataEntity().apply {
                this.key = testData.key
                this.updatedCount = testData.updatedCount
            }
        }
    }
}
