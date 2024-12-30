package net.kasasbeh.fiqh.quantities.unit

interface ConvertableUnit<U : ConvertableUnit<U>> {
    val units: List<ScalarUnit<U>>
}