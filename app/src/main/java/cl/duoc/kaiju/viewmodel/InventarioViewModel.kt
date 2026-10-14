package cl.duoc.kaiju.viewmodel

import androidx.lifecycle.ViewModel
import cl.duoc.kaiju.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InventarioViewModel : ViewModel() {

    // Lista en memoria para probar el estado inicial
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    // Función para agregar un producto
    fun agregarProducto(producto: Producto) {
        _productos.value = _productos.value + producto
    }
}