package com.github.ilikeyourhat.fmnw.db

object FakeDatabase {

    private val values = mutableListOf<String>()

    fun loadValues(): List<String> {
        return values.toList()
    }

    fun addValue(value: String) {
        values += value
    }
}