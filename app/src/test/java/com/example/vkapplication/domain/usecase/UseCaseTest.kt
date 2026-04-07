package com.example.vkapplication.domain.usecase

import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppDetailsRepository
import com.example.vkapplication.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class UseCaseTest {

    @Test
    fun getAppsUseCase_returnsRepositoryResult() = runBlocking {
        val expected = listOf(app(id = "1"), app(id = "2"))
        val repository = FakeAppRepository(apps = expected)
        val useCase = GetAppsUseCase(repository)

        val result = useCase()

        assertEquals(expected, result)
        assertEquals(1, repository.getAppsCalls)
    }

    @Test
    fun getAppByIdUseCase_passesIdAndReturnsResult() = runBlocking {
        val expected = app(id = "app-1")
        val repository = FakeAppRepository(appById = expected)
        val useCase = GetAppByIdUseCase(repository)

        val result = useCase("app-1")

        assertEquals("app-1", repository.lastAppId)
        assertEquals(expected, result)
    }

    @Test
    fun getAppDetailsUseCase_returnsNull_whenRepositoryReturnsNull() = runBlocking {
        val repository = FakeAppDetailsRepository(appDetails = null)
        val useCase = GetAppDetailsUseCase(repository)

        val result = useCase("missing")

        assertEquals("missing", repository.lastGetDetailsId)
        assertNull(result)
    }

    @Test
    fun observeAppDetailsUseCase_returnsFlowFromRepository() = runBlocking {
        val expected = app(id = "flow-id")
        val repository = FakeAppDetailsRepository(observed = flowOf(expected))
        val useCase = ObserveAppDetailsUseCase(repository)

        val result = useCase("flow-id").first()

        assertEquals("flow-id", repository.lastObserveId)
        assertEquals(expected, result)
    }

    @Test
    fun toggleFavoriteUseCase_callsRepositoryToggle() = runBlocking {
        val repository = FakeAppDetailsRepository()
        val useCase = ToggleFavoriteUseCase(repository)

        useCase("fav-id")

        assertEquals("fav-id", repository.lastToggleId)
        assertEquals(1, repository.toggleCalls)
    }

    private fun app(id: String): App = App(
        id = id,
        name = "Name",
        developer = "Dev",
        category = "Cat",
        rating = 4.4f,
        reviewCount = "10",
        downloadsCount = "100",
        size = "20 MB",
        description = "Desc",
        iconUrl = "icon",
        isFree = true,
        isInWishlist = false
    )
}

private class FakeAppRepository(
    private val apps: List<App> = emptyList(),
    private val appById: App? = null
) : AppRepository {
    var getAppsCalls: Int = 0
    var lastAppId: String? = null

    override suspend fun getApps(): List<App> {
        getAppsCalls += 1
        return apps
    }

    override suspend fun getAppById(appId: String): App? {
        lastAppId = appId
        return appById
    }
}

private class FakeAppDetailsRepository(
    private val appDetails: App? = null,
    private val observed: Flow<App> = flowOf()
) : AppDetailsRepository {
    var lastGetDetailsId: String? = null
    var lastObserveId: String? = null
    var lastToggleId: String? = null
    var toggleCalls: Int = 0

    override suspend fun getAppDetails(id: String): App? {
        lastGetDetailsId = id
        return appDetails
    }

    override fun observeAppDetails(id: String): Flow<App> {
        lastObserveId = id
        return observed
    }

    override suspend fun toggleWishlist(id: String) {
        lastToggleId = id
        toggleCalls += 1
    }
}
