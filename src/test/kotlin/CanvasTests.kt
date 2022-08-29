import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CanvasTests {
	// creating a canvas
	@Test
	fun creatingCanvas() {
		val c = Canvas(10, 20)

		assertEquals(c.width, 10)
		assertEquals(c.height, 20)

		assertArrayEquals(c.pixels, Array(10*20) {Colour(0, 0, 0)})
	}

	// writing pixels to a canvas
	@Test
	fun writingToCanvas() {
		val canvas = Canvas(10, 20)
		val red = Colour(1, 0, 0)

		canvas[3, 4] = red
		assertEquals(canvas[3,4], red)
	}

	// constructing ppm header
	@Test
	fun constructingPPMHeader() {
		val canvas = Canvas(5, 3)
		val header = canvas.toPPM().lines().take(3)

		assertEquals(header[0], "P3")
		assertEquals(header[1], "5 3")
		assertEquals(header[2], "255")
	}

	// constructing ppm data
	@Test
	fun constructingPPMData() {
		val canvas = Canvas(5, 3)
		val c1 = Colour(1.5, 0.0, 0.0)
		val c2 = Colour(0.0, 0.5, 0.0)
		val c3 = Colour(-0.5, 0.0, 1.0)
		canvas[0,0] = c1
		canvas[2,1] = c2
		canvas[4,2] = c3

		val target = """
			255 0 0 0 0 0 0 0 0 0 0 0 0 0 0
			0 0 0 0 0 0 0 128 0 0 0 0 0 0 0
			0 0 0 0 0 0 0 0 0 0 0 0 0 0 255""".trimIndent().lines()

		// test against lines 3 to 5, which is the last 3 lines minus the trailing newline
		assertLinesMatch(canvas.toPPM().lines().slice(3..5), target)
	}

	// splitting long lines in ppm file
	@Test
	fun longPPMData() {
		val c = Canvas(10,2)
		c.pixels.fill(Colour(1, 0.8, 0.6))

		// check that no line is longer than 70
		for (line in c.toPPM().lines().slice(3..6)) {
			assert(line.length <= 70)
		}
	}

	// PPM files are terminated by a newline character
	@Test
	fun endsInNewline() {
		val c = Canvas(5, 3)
		assertEquals(c.toPPM().last(), '\n')
	}
}