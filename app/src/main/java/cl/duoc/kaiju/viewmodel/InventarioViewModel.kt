package cl.duoc.kaiju.viewmodel

import androidx.lifecycle.ViewModel
import cl.duoc.kaiju.model.Categoria
import cl.duoc.kaiju.model.Producto
import cl.duoc.kaiju.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.viewModelScope

class InventarioViewModel(
    private val repository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(repository.getProductosIniciales())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    private val _categorias = MutableStateFlow<List<Categoria>>(repository.getCategorias())
    val categorias: StateFlow<List<Categoria>> = _categorias.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategoria = MutableStateFlow("Todas")
    val selectedCategoria: StateFlow<String> = _selectedCategoria.asStateFlow()

    val productosFiltrados: StateFlow<List<Producto>> = combine(
        _productos,
        _searchQuery,
        _selectedCategoria
    ) { lista, query, cat ->
        lista.filter { producto ->
            (query.isBlank() || producto.nombre.contains(query, ignoreCase = true) || producto.codigo.contains(query, ignoreCase = true)) &&
            (cat == "Todas" || producto.categoria.equals(cat, ignoreCase = true))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = repository.getProductosIniciales()
    )

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onCategoriaSelected(categoria: String) {
        _selectedCategoria.value = categoria
    }

    fun agregarProducto(producto: Producto) {
        _productos.value = _productos.value + producto
    }
}
