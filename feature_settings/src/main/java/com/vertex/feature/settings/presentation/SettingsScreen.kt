package com.vertex.feature.settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp)) {
        Text("Configurações")
        ListItem(headlineContent = { Text("Modelo") }, supportingContent = { Text("Configurável pelo backend") })
        ListItem(headlineContent = { Text("Raciocínio") }, supportingContent = { Text("Balanceado") })
        ListItem(headlineContent = { Text("Memória") }, trailingContent = { Switch(true, null) })
        ListItem(headlineContent = { Text("Privacidade") }, supportingContent = { Text("HTTPS obrigatório e sessão segura") })
    }
}
