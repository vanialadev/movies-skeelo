package com.vaniala.movies.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaniala.movies.domain.model.profile.ProfileDetails
import com.vaniala.movies.ui.components.AsyncImageProfile

@Composable
fun ProfileScreen(state: ProfileUiState = ProfileUiState()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
        Modifier
            .fillMaxSize(),
    ) {
        AsyncImageProfile(state.profileDetails?.avatar)
        state.nameUpdate?.let { name ->
            Text(
                text = name,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(ProfileUiState(profileDetails = ProfileDetails(username = "vaniala")))
}
