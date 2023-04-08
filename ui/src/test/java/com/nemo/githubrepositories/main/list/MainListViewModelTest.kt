package com.nemo.githubrepositories.main.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories_kmm.data.models.GithubProject
import com.nemo.githubrepositories_kmm.domain.GithubUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class MainListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()

    @MockK private lateinit var mockGithubUseCase: GithubUseCase

    private lateinit var viewModel: MainListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)

        viewModel = MainListViewModel(
            githubUseCase = mockGithubUseCase
        )
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun onClickSearchButton_when_projectList_is_empty() = runTest {
        val mockUserName = String()

        coEvery {
            mockGithubUseCase.fetchGithubProjects(mockUserName)
        } returns listOf()

        assertThat(viewModel.uiModelListFlow.value).isEqualTo(
            listOf(
                MainListViewModel.MainListUiModel.TextAndImageUiModel(
                    textResId = R.string.input_username,
                    imageResId = R.drawable.magnifying_glass
                )
            )
        )

        viewModel.onClickSearchButton(mockUserName)
        advanceUntilIdle()

        coVerify(exactly = 1) {
            mockGithubUseCase.fetchGithubProjects(mockUserName)
        }

        assertThat(viewModel.uiModelListFlow.value).isEqualTo(
            listOf(
                MainListViewModel.MainListUiModel.TextAndImageUiModel(
                    textResId = R.string.search_result_is_empty,
                    imageResId = R.drawable.crying_face
                )
            )
        )
    }

    @Test
    fun onClickSearchButton_when_projectList_is_not_empty() = runTest {
        val mockUserName = String()
        val mockGithubProject = GithubProject(
            id = 0,
            name = String(),
            isPrivate = false,
            ownerName = String(),
            createdTime = Clock.System.now().toLocalDateTime(timeZone = TimeZone.UTC)
        )

        coEvery {
            mockGithubUseCase.fetchGithubProjects(mockUserName)
        } returns listOf(mockGithubProject)

        assertThat(viewModel.uiModelListFlow.value).isEqualTo(
            listOf(
                MainListViewModel.MainListUiModel.TextAndImageUiModel(
                    textResId = R.string.input_username,
                    imageResId = R.drawable.magnifying_glass
                )
            )
        )

        viewModel.onClickSearchButton(mockUserName)
        advanceUntilIdle()

        coVerify(exactly = 1) {
            mockGithubUseCase.fetchGithubProjects(mockUserName)
        }

        assertThat(viewModel.uiModelListFlow.value).isEqualTo(
            listOf(mockGithubProject).map {
                it.toProjectUiModel()
            }
        )
    }

    @Test
    fun onClickSearchButton_fetchGithubProjects_throws_Exception() = runTest {
        val mockUserName = String()

        coEvery {
            mockGithubUseCase.fetchGithubProjects(mockUserName)
        } throws Exception()

        assertThat(viewModel.uiModelListFlow.value).isEqualTo(
            listOf(
                MainListViewModel.MainListUiModel.TextAndImageUiModel(
                    textResId = R.string.input_username,
                    imageResId = R.drawable.magnifying_glass
                )
            )
        )

        viewModel.onClickSearchButton(mockUserName)
        advanceUntilIdle()

        coVerify(exactly = 1) {
            mockGithubUseCase.fetchGithubProjects(mockUserName)
        }

        assertThat(viewModel.uiModelListFlow.value).isEqualTo(
            listOf(
                MainListViewModel.MainListUiModel.TextAndImageUiModel(
                    textResId = R.string.failed_connection,
                    imageResId = R.drawable.loudly_crying_face
                )
            )
        )
    }
}
