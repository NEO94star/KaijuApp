package cl.duoc.kaiju

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cl.duoc.kaiju.ui.screens.HomeScreen
import cl.duoc.kaiju.ui.screens.InventarioScreen
import cl.duoc.kaiju.ui.theme.KaijuAppTheme
import cl.duoc.kaiju.viewmodel.InventarioViewModel

class MainActivity : ComponentActivity() {

    private val inventarioViewModel: InventarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KaijuAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KaijuMainApp(viewModel = inventarioViewModel)
                }
            }
        }
    }
}

@Composable
fun KaijuMainApp(viewModel: InventarioViewModel) {
    // 0 = Inicio, 1 = Inventario
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Text(text = "🏠") },
                    label = { Text(text = "Inicio") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Text(text = "📦") },
                    label = { Text(text = "Inventario") }
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> HomeScreen(
                viewModel = viewModel,
                onNavigateToInventario = { selectedTab = 1 },
                modifier = Modifier.padding(innerPadding)
            )
            1 -> InventarioScreen(
                viewModel = viewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
