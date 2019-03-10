typealias Predicate<T> = (T) -> Boolean

/**
 * Returns a union of the given list of predicates.
 * @param constraints The predicates to union.
 * @returns A function for checking if a given object satisfies all [constraints].
 */
fun union(constraints: List<Predicate<Any?>>): Predicate<Any?> = { obj -> constraints.any { it(obj) } }

/**
 * Returns a union of the given predicates.
 * @param constraints The predicates to union.
 * @returns A function for checking if a given object satisfies all [constraints].
 */
fun union(vararg constraints: Predicate<Any?>): Predicate<Any?> = union(listOf(*constraints))

/**
 * Returns a type predicate function.
 * @param T The type to check against.
 * @return A function for checking if a given object is of type [T].
 */
inline fun <reified T> isA(): Predicate<Any?> = { it is T }