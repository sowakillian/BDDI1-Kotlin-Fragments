inline fun sum(a: Int, b: Int, callback: () -> Unit) {
    callback()
    println(a.plus(b))
}

sum(5,9, {
    println("weweee")
})