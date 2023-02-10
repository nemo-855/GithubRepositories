package com.nemo.githubrepositories.main.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nemo.data.models.GithubProject
import com.nemo.data.repositories.interfaces.GithubRepository
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.androidView.list.MainListViewModel
import com.nemo.githubrepositories.androidView.list.toProjectUiModel
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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.ZonedDateTime

@ExperimentalCoroutinesApi
internal class MainListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()

    @MockK private lateinit var mockGithubRepository: GithubRepository

    private lateinit var viewModel: MainListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)

        viewModel = MainListViewModel(
            githubRepository = mockGithubRepository
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
            mockGithubRepository.fetchGithubProjects(mockUserName)
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
            mockGithubRepository.fetchGithubProjects(mockUserName)
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
            createdTime = ZonedDateTime.now()
        )

        coEvery {
            mockGithubRepository.fetchGithubProjects(mockUserName)
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
            mockGithubRepository.fetchGithubProjects(mockUserName)
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
            mockGithubRepository.fetchGithubProjects(mockUserName)
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
            mockGithubRepository.fetchGithubProjects(mockUserName)
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
