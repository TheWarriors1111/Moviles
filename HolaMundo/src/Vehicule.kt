open class Vehicule(marca: String, numRuedas: Int, precio: Double) {
    var marca: String
    var numRuedas: Int
    var precio: Double
    init {
        this.marca = marca
        this.numRuedas = numRuedas
        this.precio = precio
        println("Estamos construyendo un vehículo de " + marca)
    }
    constructor(marca: String, precio: Double) : this(marca, numRuedas = 4, precio = precio) {
    }

    companion object {
        fun llamarGrua(){
            println("Vehículo averiado: llamando a la grua ")
        }
    }
}