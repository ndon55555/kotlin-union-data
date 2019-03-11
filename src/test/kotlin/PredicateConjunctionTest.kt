import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class PredicateConjunctionTest : Spek({
    describe("Conjunction of predicates") {
        val divBy2 = { n: Int -> n % 2 == 0 }
        val divBy3 = { n: Int -> n % 3 == 0 }
        val conj = divBy2 and divBy3

        context("test object only satisfies part of the conjunction") {
            it("returns false") {
                assertFalse(conj(2))
                assertFalse(conj(3))
            }
        }

        context("test object completely satisfies the conjunction") {
            it("returns true") {
                assertTrue(conj(0))
                assertTrue(conj(6))
            }
        }
    }
})