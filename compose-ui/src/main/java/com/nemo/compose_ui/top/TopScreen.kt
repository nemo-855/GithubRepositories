package com.nemo.compose_ui.top

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemo.compose_ui.designsystem.GithubTypography
import com.nemo.githubrepositories.composeui.R

@Composable
fun TopScreen(
    modifier: Modifier = Modifier,
    viewModel: TopViewModel = hiltViewModel(),
) {
    val state: TopUiState by viewModel.uiState.collectAsState()

    TopScreenContent(
        modifier = modifier,
        uiState = state,
    )
}

@Composable
fun TopScreenContent(
    uiState: TopUiState,
    modifier: Modifier,
) {
    if (uiState.hasErrorOccurred) {
        Column(modifier = modifier) {
            Spacer(modifier = Modifier.height(32.dp))
            ErrorOccurred()
        }
    } else if (uiState.isInitial) {
        Column(modifier = modifier) {
            Spacer(modifier = Modifier.height(32.dp))
            SearchUsername()
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
