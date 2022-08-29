import kotlin.math.roundToInt

class Canvas(val width: Int, val height: Int) {
	val pixels = Array(width * height) { Colour(0, 0, 0) }

	operator fun get(x: Int, y: Int) = pixels[y * width + x]
	operator fun set(x: Int, y: Int, value: Colour) { pixels[y * width + x] = value }

	fun toPPM(): String {
		// header for text based PPM files
		val header = """
			P3
			$width $height
			255
		""".trimIndent()
		val output = StringBuilder()
		val line = StringBuilder()

		output.appendLine(header) // add the header on

		for (y in 0 until height) {
			for (x in 0 until width) {
				// print out the colour
				for (part in arrayOf(this[x, y].red, this[x, y].green, this[x, y].blue)) {
					// for each part of the rgb triple
					val partString = "${(part.coerceIn(0.0, 1.0) * 255).roundToInt()} "
					if (line.length >= 70 - partString.length) {
						// if the line's getting too long add it to the output and clear ready for more
						output.appendLine(line.toString().trim())
						line.clear()
					}

					line.append("${(part.coerceIn(0.0, 1.0) * 255).roundToInt()} ")
					// then add on the colour part
				}
			}
			// add the row to the output and clear ready for the next line
			output.appendLine(line.toString().trim())
			line.clear()
		}

		return output.toString()
	}
}
