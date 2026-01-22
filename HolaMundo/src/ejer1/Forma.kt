package ejer1

open class Forma(nombre: String, tipoforma: Tipos) {
    var nombre: String = nombre
    var tipoforma: Tipos = tipoforma

    override fun toString(): String {
        return "La forma se llama $nombre y es de tipo $tipoforma"
    }
}