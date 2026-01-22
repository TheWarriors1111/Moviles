class Coche(marca: String, precio: Double): Vehicule(marca, precio), Conducir {
    override fun conducir(direccion: String) {
        println("Girando $direccion")
    }
}