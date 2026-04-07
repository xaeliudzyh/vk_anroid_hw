package com.example.vkapplication.data.repository

import com.example.vkapplication.data.appdetails.local.AppDetailsDao
import com.example.vkapplication.data.appdetails.local.AppDetailsEntity
import com.example.vkapplication.data.appdetails.local.AppDetailsEntityMapper
import com.example.vkapplication.data.appdetails.mapper.AppDetailsMapper
import com.example.vkapplication.data.dto.NetworkAppDto
import com.example.vkapplication.data.network.CatalogApi
import com.example.vkapplication.domain.model.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RepositoryTest {

    @Test
    fun appRepository_getApps_returnsMappedList() = runBlocking {
        val api = FakeCatalogApi(
            catalog = listOf(
                networkApp(id = "1", name = "One"),
                networkApp(id = "2", name = "Two")
            )
        )
        val repository = AppRepositoryImpl(api)

        val result = repository.getApps()

        assertEquals(2, result.size)
        assertEquals("1", result[0].id)
        assertEquals("One", result[0].name)
        assertEquals("2", result[1].id)
    }

    @Test
    fun appRepository_getAppById_returnsMappedApp() = runBlocking {
        val api = FakeCatalogApi(appById = networkApp(id = "42", name = "Answer", isFree = false))
        val repository = AppRepositoryImpl(api)

        val result = repository.getAppById("42")

        assertNotNull(result)
        assertEquals("42", result?.id)
        assertEquals("Answer", result?.name)
        assertEquals(false, result?.isFree)
    }

    @Test
    fun appRepository_getAppById_returnsNull_whenApiThrows() = runBlocking {
        val api = FakeCatalogApi(throwOnAppById = true)
        val repository = AppRepositoryImpl(api)

        val result = repository.getAppById("missing")

        assertNull(result)
    }

    @Test
    fun appDetailsRepository_getAppDetails_returnsFromDb_whenExists() = runBlocking {
        val dao = FakeAppDetailsDao(
            initial = entity(id = "db-id", name = "From DB", isInWishlist = true)
        )
        val api = FakeCatalogApi(appById = networkApp(id = "api-id", name = "From API"))
        val repository = AppDetailsRepositoryImpl(
            appApi = api,
            dao = dao,
            appDetailsMapper = AppDetailsMapper(),
            appDetailsEntityMapper = AppDetailsEntityMapper()
        )

        val result = repository.getAppDetails("db-id")

        assertNotNull(result)
        assertEquals("db-id", result?.id)
        assertEquals("From DB", result?.name)
        assertEquals(true, result?.isInWishlist)
        assertEquals(0, api.getAppByIdCalls)
    }

    @Test
    fun appDetailsRepository_getAppDetails_fetchesFromApiAndSaves_whenDbEmpty() = runBlocking {
        val dao = FakeAppDetailsDao(initial = null)
        val api = FakeCatalogApi(appById = networkApp(id = "api-id", name = "From API", isFree = false))
        val repository = AppDetailsRepositoryImpl(
            appApi = api,
            dao = dao,
            appDetailsMapper = AppDetailsMapper(),
            appDetailsEntityMapper = AppDetailsEntityMapper()
        )

        val result = repository.getAppDetails("api-id")

        assertNotNull(result)
        assertEquals("api-id", result?.id)
        assertEquals("From API", result?.name)
        assertEquals(1, api.getAppByIdCalls)
        assertNotNull(dao.inserted)
        assertEquals("api-id", dao.inserted?.id)
    }

    @Test
    fun appDetailsRepository_toggleWishlist_updatesStatusInDao() = runBlocking {
        val dao = FakeAppDetailsDao(initial = entity(id = "id", isInWishlist = false))
        val repository = AppDetailsRepositoryImpl(
            appApi = FakeCatalogApi(),
            dao = dao,
            appDetailsMapper = AppDetailsMapper(),
            appDetailsEntityMapper = AppDetailsEntityMapper()
        )

        repository.toggleWishlist("id")

        assertEquals("id", dao.lastUpdatedId)
        assertEquals(true, dao.lastUpdatedStatus)
        assertEquals(true, dao.getAppDetails("id").first()?.isInWishlist)
    }

    @Test
    fun appDetailsRepository_observeAppDetails_emitsMappedDomain() = runBlocking {
        val dao = FakeAppDetailsDao(initial = entity(id = "obs", name = "Observed"))
        val repository = AppDetailsRepositoryImpl(
            appApi = FakeCatalogApi(),
            dao = dao,
            appDetailsMapper = AppDetailsMapper(),
            appDetailsEntityMapper = AppDetailsEntityMapper()
        )

        val result = repository.observeAppDetails("obs").first()

        assertEquals("obs", result.id)
        assertEquals("Observed", result.name)
    }

    private fun networkApp(
        id: String = "id",
        name: String = "Name",
        isFree: Boolean = true
    ) = NetworkAppDto(
        id = id,
        name = name,
        developer = "Dev",
        category = "Tools",
        rating = 4.5f,
        reviewCount = "100",
        downloadsCount = "1K",
        size = "12 MB",
        description = "Desc",
        iconUrl = "https://example.com/icon.png",
        isFree = isFree
    )

    private fun entity(
        id: String = "id",
        name: String = "Name",
        isInWishlist: Boolean = false
    ) = AppDetailsEntity(
        id = id,
        name = name,
        developer = "Dev",
        category = "Tools",
        rating = 4.2f,
        reviewCount = "23",
        downloadsCount = "900",
        size = "10 MB",
        description = "Desc",
        iconUrl = "url",
        isFree = true,
        isInWishlist = isInWishlist,
        lastUpdated = 1L
    )
}

private class FakeCatalogApi(
    private val catalog: List<NetworkAppDto> = emptyList(),
    private val appById: NetworkAppDto = NetworkAppDto(
        id = "id",
        name = "name",
        developer = "dev",
        category = "cat",
        rating = 1f,
        reviewCount = "1",
        downloadsCount = "1",
        size = "1",
        description = "d",
        iconUrl = "i",
        isFree = true
    ),
    private val throwOnAppById: Boolean = false
) : CatalogApi {

    var getAppByIdCalls: Int = 0

    override suspend fun getCatalog(): List<NetworkAppDto> = catalog

    override suspend fun getAppById(id: String): NetworkAppDto {
        getAppByIdCalls += 1
        if (throwOnAppById) error("network error")
        return appById
    }
}

private class FakeAppDetailsDao(initial: AppDetailsEntity?) : AppDetailsDao {
    private val state = MutableStateFlow(initial)
    var inserted: AppDetailsEntity? = null
    var lastUpdatedId: String? = null
    var lastUpdatedStatus: Boolean? = null

    override fun getAppDetails(id: String): Flow<AppDetailsEntity?> = state

    override fun insertAppDetails(appDetails: AppDetailsEntity) {
        inserted = appDetails
        state.value = appDetails
    }

    override suspend fun updateWishlistStatus(id: String, isInWishlist: Boolean) {
        lastUpdatedId = id
        lastUpdatedStatus = isInWishlist
        val current = state.value
        if (current != null) {
            state.value = current.copy(isInWishlist = isInWishlist)
        }
    }
}
