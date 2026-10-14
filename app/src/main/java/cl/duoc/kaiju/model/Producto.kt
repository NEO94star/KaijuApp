package cl.duoc.kaiju.model

data class Producto(
    val codigo: String,
    val nombre: String,
    val descripcion: String = "",
    val categoria: String = "",
    val precio: Double,
    val cantidadDisponible: Int,
    val stockMinimo: Int
)