import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

internal class TupleTests {
	// A tuple with w=1 is a point
	@Test
	fun tupleIsPoint() {
		val a = Tuple(4.3, -4.2, 3.1, 1.0)

		assertEquals(4.3,  a.x)
		assertEquals(-4.2, a.y)
		assertEquals(3.1,  a.z)
		assertEquals(1.0,  a.w)
		assertEquals(true, a.isPoint())
		assertEquals(false, a.isVector())
	}

	// A tuple with w=0 is a point
	@Test
	fun tupleIsVector() {
		val a = Tuple(4.3, -4.2, 3.1, 0.0)

		assertEquals(4.3,  a.x)
		assertEquals(-4.2, a.y)
		assertEquals(3.1,  a.z)
		assertEquals(0.0,  a.w)
		assertEquals(false, a.isPoint())
		assertEquals(true, a.isVector())
	}

	// point() creates tuples with w=1
	@Test
	fun creatingPoint() {
		val p = Point(4, -4, 3)

		assertEquals(p, Tuple(4, -4, 3, 1))
	}

	// vector() creates tuples with w=0
	@Test
	fun creatingVector() {
		val v = Vector(4, -4, 3)

		assertEquals(v, Tuple(4, -4, 3, 0))
	}

	// adding two tuples
	@Test
	fun addingTuples() {
		val a = Tuple(3, -2, 5, 1)
		val b = Tuple(-2, 3, 1, 0)

		assertEquals(a + b, Tuple(1,  1, 6, 1))
	}

	// subtracting two points
	@Test
	fun subtractingPoints() {
		val a = Point(3, 2, 1)
		val b = Point(5, 6, 7)

		assertEquals(a - b, Vector(-2, -4, -6))
	}

	// subtracting a vector for a point
	@Test
	fun subtractingVectorFromPoint() {
		val p = Point(3, 2, 1)
		val v = Vector(5, 6, 7)

		assertEquals(p - v, Point(-2, -4, -6))
	}

	// subtracting two vectors
	@Test
	fun subtractingVectors() {
		val a = Vector(3, 2, 1)
		val b = Vector(5, 6, 7)

		assertEquals(Vector(-2, -4, -6), a - b)
	}

	// subtracting a vector from the zero vector
	@Test
	fun negatedVector() {
		val zero = Vector(0, 0, 0)
		val v = Vector(1, -2, 3)

		assertEquals(zero - v, Vector(-1, 2, -3))
	}

	// negating a tuple
	@Test
	fun negatedTuple() {
		val t = Tuple(1, -2, 3, -4)

		assertEquals(-t, Tuple(-1, 2, -3, 4))
	}

	// multiplying a tuple by a scalar
	@Test
	fun multiplyTupleByScalar() {
		val a = Tuple(1, -2, 3, -4)
		assertEquals(Tuple(3.5, -7, 10.5, -14), a * 3.5)
	}

	// multiplying a tuple by a fraction
	@Test
	fun multiplyTupleByFraction() {
		val a = Tuple(1, -2, 3, -4)
		assertEquals(Tuple(0.5, -1, 1.5, -2), a * 0.5)
	}

	// dividing a tuple by a scalar
	@Test
	fun divideTupleByScalar() {
		val a = Tuple(1, -2, 3, -4)
		assertEquals(Tuple(0.5, -1, 1.5, -2), a / 2)
	}

	// computing the magnitude
	@Test
	fun magnitudes() {
		var v = Vector(1, 0, 0)
		assertEquals(v.magnitude(), 1.0)

		v = Vector(0, 1, 0)
		assertEquals(v.magnitude(), 1.0)

		v = Vector(0, 0, 1)
		assertEquals(v.magnitude(), 1.0)

		v = Vector(1, 2, 3)
		assertEquals(v.magnitude(), sqrt(14.0))

		v = Vector(-1, -2, -3)
		assertEquals(v.magnitude(), sqrt(14.0))
	}

	// normalization
	@Test
	fun normalizing() {
		// normalizing vector(4, 0, 0) gives (1, 0, 0)
		var v = Vector(4, 0, 0)
		assertEquals(v.normalize(), Vector(1, 0, 0))

		// normalizing vector(1, 2, 3)
		v = Vector(1, 2, 3)
		// vector(1 / √14, 2 / √14, 3 / √14)
		assertEquals(v.normalize(), Vector(0.26726, 0.53452, 0.80178))

		// magnitude of a normalized vector
		assertEquals(v.normalize().magnitude(), 1.0)
	}

	// the dot product of two tuples
	@Test
	fun dotProduct() {
		val a = Vector(1, 2, 3)
		val b = Vector(2, 3, 4)
		assertEquals(Tuple.dotProduct(a, b), 20.0)
	}

	// the cross product of two vectors
	@Test
	fun crossProduct() {
		val a = Vector(1, 2, 3)
		val b = Vector(2, 3, 4)
		assertEquals(Tuple.crossProduct(a, b), Vector(-1, 2, -1))
		assertEquals(Tuple.crossProduct(b, a), Vector(1, -2, 1))
	}
}