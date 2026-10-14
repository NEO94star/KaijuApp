package cl.duoc.kaiju.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duoc.kaiju.ui.components.ProductoItemCard
import cl.duoc.kaiju.ui.theme.KaijuAppTheme
import cl.duoc.kaiju.viewmodel.InventarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: InventarioViewModel,
    onNavigateToInventario: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToStockBajo: () -> Unit = {}
) {
    // Obtención de estados del ViewModel
    val todosProductos by viewModel.productos.collectAsState()
    val categorias by viewModel.categorias.collectAsState()
    val stockBajoProductos = todosProductos.filter { it.isStockBajo }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kaiju Store - Inicio",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ==========================================
            // SECCIÓN 1: Banner / Header de Bienvenida
            // ==========================================
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .height(56.dp)
                            .width(56.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "KJ",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = "¡Bienvenido a Kaiju!",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Sistema de Gestión y Trazabilidad de Inventario",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            // ==========================================
            // SECCIÓN 2: Métricas Rápidas del Inventario
            // ==========================================
            Text(
                text = "Resumen del Sistema",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Tarjeta 1: Total Productos
                MetricaCard(
                    titulo = "Productos",
                    valor = todosProductos.size.toString(),
                    subtitulo = "en catálogo",
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.weight(1f)
                )

                // Tarjeta 2: Stock Bajo
                MetricaCard(
                    titulo = "Stock Bajo",
                    valor = stockBajoProductos.size.toString(),
                    subtitulo = "requieren compra",
                    containerColor = if (stockBajoProductos.isNotEmpty()) Color(0xFFFFEBEE) else MaterialTheme.colorScheme.surfaceVariant,
                    textColor = if (stockBajoProductos.isNotEmpty()) Color(0xFFD32F2F) else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                // Tarjeta 3: Categorías
                MetricaCard(
                    titulo = "Categorías",
                    valor = (categorias.size - 1).coerceAtLeast(0).toString(),
                    subtitulo = "registradas",
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.weight(1f)
                )
            }

            // ==========================================
            // SECCIÓN 3: Acciones Rápidas
            // ==========================================
            Text(
                text = "Acciones Rápidas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onNavigateToInventario,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "📦 Ver Catálogo e Inventario Completo",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                if (stockBajoProductos.isNotEmpty()) {
                    OutlinedButton(
                        onClick = {
                            onNavigateToStockBajo()
                            onNavigateToInventario()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFFD32F2F)
                        )
                    ) {
                        Text(
                            text = "⚠ Ver ${stockBajoProductos.size} Productos en Stock Bajo",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }

            // ==========================================
            // SECCIÓN 4: Alertas Prioritarias (Stock Bajo)
            // ==========================================
            if (stockBajoProductos.isNotEmpty()) {
                Text(
                    text = "Alertas de Reposición Prioritaria",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    stockBajoProductos.take(2).forEach { producto ->
                        ProductoItemCard(producto = producto)
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricaCard(
    titulo: String,
    valor: String,
    subtitulo: String,
    containerColor: Color,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titulo,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = textColor.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = valor,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = textColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitulo,
                fontSize = 10.sp,
                color = textColor.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KaijuAppTheme {
        HomeScreen(
            viewModel = InventarioViewModel(),
            onNavigateToInventario = {}
        )
    }
}
