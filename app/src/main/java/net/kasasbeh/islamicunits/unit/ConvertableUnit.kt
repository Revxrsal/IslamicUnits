package net.kasasbeh.islamicunits.unit

interface ConvertableUnit<U : ConvertableUnit<U>> {
    val units: List<ScalarUnit<U>>
}