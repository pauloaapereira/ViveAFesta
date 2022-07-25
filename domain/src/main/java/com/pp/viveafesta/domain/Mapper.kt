package com.pp.viveafesta.domain

interface Mapper<I, O> {
    fun mapFrom(input: I): O
    fun mapTo(input: O): I
}