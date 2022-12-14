import kotlin.math.roundToInt

class Colour(r: Number, g: Number, b: Number) {
	var red   = r.toDouble()
	var green = g.toDouble()
	var blue  = b.toDouble()

	operator fun plus(value: Colour) = Colour(red + value.red, green + value.green, blue + value.blue)
	operator fun minus(value: Colour) = Colour(red - value.red, green - value.green, blue - value.blue)
	operator fun times(value: Colour) = Colour(red * value.red, green * value.green, blue * value.blue)
	operator fun times(value: Number) = Colour(red * value.toDouble(), green * value.toDouble(), blue * value.toDouble())

	override operator fun equals(other: Any?): Boolean {
		return if (other is Colour)
			Tuple.equal(red, other.red) && Tuple.equal(green, other.green) && Tuple.equal(blue, other.blue)
		else
			false
	}

}