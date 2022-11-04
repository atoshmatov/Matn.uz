package uz.uicgroup.utils

typealias SingleBlock <T> = (T) -> Unit
typealias DoubleBlock <T, E> = (T, E) -> Unit
typealias ThreeBlock <T, E, D> = (T, E, D) -> Unit
typealias EmptyBlock = () -> Unit
