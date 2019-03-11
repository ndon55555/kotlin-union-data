import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Skip
import org.spekframework.spek2.style.specification.describe

class CreatePredicateTest : Spek({
    describe("An Int predicate") {
        val intPred = isA<Int>()

        context("test object is an int") {
            it("returns true") {
                assertTrue(intPred(1))
            }
        }

        context("test object is not an int") {
            it("returns false") {
                assertFalse(intPred("foo"))
                assertFalse(intPred(true))
                assertFalse(intPred('x'))
                assertFalse(intPred(listOf<Int>()))
                assertFalse(intPred(null))
            }
        }
    }

    describe("A String predicate with further constraints") {
        val stringPred = asA<String> { length > 5 }

        context("test object is not the same type") {
            it("returns false") {
                assertFalse(stringPred(1))
            }
        }

        context("test object is same type but fails constraints") {
            it("returns false") {
                assertFalse(stringPred("foo"))
            }
        }

        context("test object is same type and passes constraints") {
            it("returns true") {
                assertTrue(stringPred("123456"))
            }
        }
    }

    describe("A List<Int> predicate") {
        val intListPred = isA<List<Int>>()

        context("test object is a list of ints") {
            it("returns true") {
                assertTrue(intListPred(listOf(1, 2, 3)))
            }
        }

        context(
            "test object is not a list of ints",
            Skip.Yes("all generic Lists resolve to raw Lists during compile time on JVM")
        ) {
            it("returns false") {
                assertFalse(intListPred(listOf(1, true, "foo")))
                assertFalse(intListPred(listOf(2.0, 3.0)))
            }
        }
    }
})