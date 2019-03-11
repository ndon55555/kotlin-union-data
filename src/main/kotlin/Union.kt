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
 * @param T The type to check for.
 * @return A function for checking if a given object is of type [T].
 */
inline fun <reified T> isA(): Predicate<Any?> = { it is T }

/**
 * Returns a conjunction of this predicate and the given predicate.
 * @param p The other predicate.
 * @return A conjunction of this and the given predicates.
 */
inline infix fun <reified T> Predicate<T>.and(crossinline p: Predicate<T>): Predicate<T> = {
    this(it) && p(it)
}

/**
 * Returns a type predicate with an additional constraint.
 * @param T The type to check for.
 * @param constraint A condition to satisfy when a given object is a [T].
 * @return A type predicate with an additional constraint.
 */
inline fun <reified T> asA(crossinline constraint: T.() -> Boolean): Predicate<Any?> =
    isA<T>() and { (it as T).constraint() }