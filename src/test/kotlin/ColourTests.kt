import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ColourTests {
	// colours are (red, green, blue) tuples
	@Test
	fun createColour() {
		val c = Colour(-0.5, 0.4, 1.7)
		assertEquals(c.red, -0.5)
		assertEquals(c.green,0.4)
		assertEquals(c.blue, 1.7)
	}

	@Test
	fun addingColour() {
		val a = Colour(0.9, 0.6, 0.75)
		val b = Colour(0.7, 0.1, 0.25)
		assertEquals(a + b, Colour(1.6, 0.7, 1.0))
	}

	@Test
	fun subtractingColour() {
		val a = Colour(0.9, 0.6, 0.75)
		val b = Colour(0.7, 0.1, 0.25)
		assertEquals(a - b, Colour(0.2, 0.5, 0.5))
	}

	@Test
	fun multiplyingByScalar() {
		val c = Colour(0.2, 0.3, 0.4)
		assertEquals(c * 2, Colour(0.4, 0.6, 0.8))
	}

	@Test
	fun multiplyColours() {
		val a = Colour(1.0, 0.2, 0.4)
		val b = Colour(0.9, 1.0, 0.1)
		assertEquals(a * b, Colour(0.9, 0.2, 0.04))
	}
}