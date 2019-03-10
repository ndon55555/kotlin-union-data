import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class UnionTest : Spek({
    describe("A union of predicates") {
        val unionPred = union(
            isA<String>(),
            isA<Int>(),
            isA<Boolean>()
        )

        context("test object is a member of the union") {
            it("returns true") {
                assertTrue(unionPred("foo"))
                assertTrue(unionPred(0))
                assertTrue(unionPred(true))
            }
        }

        context("test object is not a member of the union") {
            it("returns false") {
                assertFalse(unionPred(null))
                assertFalse(unionPred(1.1))
            }
        }
    }
})