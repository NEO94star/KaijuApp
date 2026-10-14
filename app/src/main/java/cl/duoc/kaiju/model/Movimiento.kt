package cl.duoc.kaiju.model

enum class TipoMovimiento {
    ENTRADA,
    SALIDA,
    VENTA,
    AJUSTE
}

data class Movimiento(
    val id: String,
    val codigoProducto: String,
    val tipo: TipoMovimiento,
    val cantidad: Int,
    val fecha: String,
    val nota: String = ""
)
