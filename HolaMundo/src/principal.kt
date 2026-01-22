fun main() {
    var miVehiculo = Vehicule("Peugeot", 4, 12000.0)
    println(miVehiculo.marca)
    Vehicule.llamarGrua()

    println(miVehiculo.toString())

    var miVehicule2 = Vehicule2("Ford", 4, 20000.0)
    println(miVehicule2.marca)
    Vehicule.llamarGrua()

    println(miVehicule2.toString())

    var miVehicule3 = Coche("BMW",42000.0)
    println(miVehicule3.marca)
    Vehicule.llamarGrua()

    println(miVehicule3.toString())

    miVehicule3.conducir("derecha")
}