package org.acme.domain

data class TestData(
        var key: String? = null,
        var updatedCount: Int? = null
) {
    companion object {
        fun create(): TestData {
            return TestData(
                    key = System.currentTimeMillis().toString(),
                    updatedCount = 0
            )
        }
    }

    fun increaseUpdateCount() {
        this.updatedCount = this.updatedCount!!.plus(1)
    }
}
