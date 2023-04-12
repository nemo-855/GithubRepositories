package com.nemo.compose_ui.top

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemo.compose_ui.designsystem.GithubScaffold
import com.nemo.compose_ui.designsystem.GithubTypography
import com.nemo.compose_ui.util.formatTime
import com.nemo.githubrepositories.composeui.R
import com.nemo.githubrepositories_kmm.data.models.GithubProject

@Composable
fun TopScreen(
    modifier: Modifier = Modifier,
    viewModel: TopViewModel = hiltViewModel(),
) {
    val state: TopUiState by viewModel.uiState.collectAsState()

    TopScreenContent(
        modifier = modifier,
        uiState = state,
        onClickSearchButton = {
            viewModel.onClickSearchButton()
        },
        onSearchBarValueChanged = { newValue ->
            viewModel.onSearchBarValueChanged(newValue = newValue)
        },
    )
}

@Composable
private fun TopScreenContent(
    uiState: TopUiState,
    modifier: Modifier,
    onClickSearchButton: () -> Unit,
    onSearchBarValueChanged: (newValue: String) -> Unit,
) {
    GithubScaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                onClickIconButton = onClickSearchButton,
                onValueChanged = onSearchBarValueChanged,
                uiModel = uiState.searchBar,
            )
        }
    ) {
        Column(modifier = modifier) {
            Spacer(modifier = Modifier.height(it.calculateTopPadding()))

            if (uiState.content.hasErrorOccurred) {
                Spacer(modifier = Modifier.height(32.dp))
                ErrorOccurred()
            } else if (uiState.content.hasNotSearched) {
                Spacer(modifier = Modifier.height(32.dp))
                SearchUsername()
            }
        }
    }
}

@Composable
private fun SearchUsername() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_search_24),
            contentDescription = stringResource(id = R.string.search_image),
            modifier = Modifier
                .width(300.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = stringResource(id = R.string.input_username),
            style = GithubTypography.bodyLarge,
        )
    }
}

@Composable
private fun ProjectCard(project: GithubProject) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_link_24),
            contentDescription = stringResource(id = R.string.project_image),
            modifier = Modifier
                .size(40.dp),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = project.ownerName,
                style = GithubTypography.bodyMedium,
            )

            Text(
                text = project.name,
                style = GithubTypography.displaySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_link_24),
                    contentDescription = stringResource(id = R.string.link_button),
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Crop,
                )

                Text(
                    text = stringResource(
                        id = R.string.last_update_time,
                        project.createdTime.formatTime(format = stringResource(id = R.string.date_format)).orEmpty()
                    ),
                    style = GithubTypography.bodyMedium,
                    modifier = Modifier.wrapContentWidth().wrapContentHeight()
                )
            }
        }
    }
}

@Composable
private fun ErrorOccurred() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.crying_face),
            contentDescription = stringResource(id = R.string.error_occurred),
            modifier = Modifier
                .width(300.dp)
                .padding(32.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = stringResource(id = R.string.error_occurred_description),
            style = GithubTypography.bodyLarge,
            textAlign = TextAlign.Center,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    onClickIconButton: () -> Unit,
    onValueChanged: (newValue: String) -> Unit,
    uiModel: SearchBarUiModel,
) {
    CenterAlignedTopAppBar(
        title = {
            TextField(
                value = uiModel.text,
                onValueChange = onValueChanged,
                maxLines = 1,
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_by_username),
                        style = GithubTypography.bodySmall,
                        textAlign = TextAlign.Center,
                    )
                },
            )
        },
        navigationIcon = {
            IconButton(onClick = onClickIconButton) {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = stringResource(id = R.string.username_search_button)
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    )
}