package cl.duoc.kaiju.repository

import cl.duoc.kaiju.model.Categoria
import cl.duoc.kaiju.model.Producto

class ProductoRepository {

    fun getCategorias(): List<Categoria> {
        return listOf(
            Categoria("1", "Todas", "Todas las categorías"),
            Categoria("2", "Figuras", "Figuras coleccionables y estatuas"),
            Categoria("3", "Manga", "Cómics y mangas importados"),
            Categoria("4", "Cartas", "Juegos de cartas coleccionables (TCG)"),
            Categoria("5", "Insumos", "Protectores, fundas y carpetas")
        )
    }

    fun getProductosIniciales(): List<Producto> {
        return listOf(
            Producto(
                codigo = "KJ-001",
                nombre = "Figura Godzilla King of Monsters",
                descripcion = "Figura articulada de 30cm Godzilla Neca importada",
                categoria = "Figuras",
                tipoProducto = "Figuras",
                precio = 45990.0,
                cantidadDisponible = 12,
                stockMinimo = 5
            ),
            Producto(
                codigo = "KJ-002",
                nombre = "Figura Mechagodzilla 1974",
                descripcion = "Edición especial de aniversario Kaiju Classic",
                categoria = "Figuras",
                tipoProducto = "Figuras",
                precio = 52990.0,
                cantidadDisponible = 3,
                stockMinimo = 4
            ),
            Producto(
                codigo = "KJ-003",
                nombre = "Manga Kaiju No. 8 - Vol. 1",
                descripcion = "Tomo 1 en español, edición Ivrea",
                categoria = "Manga",
                tipoProducto = "Manga",
                precio = 8990.0,
                cantidadDisponible = 25,
                stockMinimo = 10
            ),
            Producto(
                codigo = "KJ-004",
                nombre = "Manga Kaiju No. 8 - Vol. 2",
                descripcion = "Tomo 2 en español, edición Ivrea",
                categoria = "Manga",
                tipoProducto = "Manga",
                precio = 8990.0,
                cantidadDisponible = 2,
                stockMinimo = 8
            ),
            Producto(
                codigo = "KJ-005",
                nombre = "Pack Booster Yu-Gi-Oh! Kaiju Strike",
                descripcion = "Sobre de 9 cartas aleatorias de la expansión Kaiju",
                categoria = "Cartas",
                tipoProducto = "Cartas",
                precio = 4990.0,
                cantidadDisponible = 50,
                stockMinimo = 15
            ),
            Producto(
                codigo = "KJ-006",
                nombre = "Fichas / Fundas Protectoras Kaiju Art",
                descripcion = "Paquete de 100 fundas mate con arte de Godzilla",
                categoria = "Insumos",
                tipoProducto = "Insumos",
                precio = 6990.0,
                cantidadDisponible = 1,
                stockMinimo = 5
            )
        )
    }
}
