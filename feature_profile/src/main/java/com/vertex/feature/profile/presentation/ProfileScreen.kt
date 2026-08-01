package com.vertex.feature.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp)) {
        Text("Perfil")
        Text("Conta, idioma, preferências e custos consolidados da plataforma.")
    }
}
