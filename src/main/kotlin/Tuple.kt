import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.reflect.typeOf

const val EPSILON = 0.00001

open class Tuple(x: Number, y: Number, z: Number, w: Number) {
	var x: Double = x.toDouble()
	var y: Double = y.toDouble()
	var z: Double = z.toDouble()
	var w: Double = w.toDouble()


	fun isPoint(): Boolean {
		return w == 1.0
	}
	fun isVector(): Boolean {
		return w == 0.0
	}

	fun equal(a: Number, b: Number): Boolean {
		return abs(a.toDouble() - b.toDouble()) < EPSILON
	}

	fun magnitude() = sqrt(x.pow(2) + y.pow(2) + z.pow(2) + w.pow(2))

	fun normalize() = Tuple(x / magnitude(), y / magnitude(), z / magnitude(), w / magnitude())

	operator fun plus(value: Tuple) = Tuple(this.x + value.x, this.y + value.y, this.z + value.z, this.w + value.w)
	operator fun minus(value: Tuple) = Tuple(this.x - value.x, this.y - value.y, this.z - value.z, this.w - value.w)
	operator fun times(value: Number): Tuple {
		val num = value.toDouble()
		return Tuple(x * num, y * num, z * num, w * num)
	}
	operator fun div(value: Number): Tuple {
		val num = value.toDouble()
		return Tuple(x / num, y / num, z / num, w / num)
	}
	operator fun unaryMinus() = Tuple(-this.x, -this.y, -this.z, -this.w)

	override fun equals(other: Any?): Boolean {
		return if (other is Tuple) {
			equal(this.x, other.x) && equal(this.y, other.y) && equal(this.z, other.z) && this.w == other.w
		} else {
			false
		}
	}

	override fun toString(): String {
		return "Tuple(x=$x, y=$y, z=$z, w=$w)"
	}

	companion object {
		fun dotProduct(a: Tuple, b: Tuple) = a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w
		fun crossProduct(a: Tuple, b: Tuple) = Vector(
			a.y * b.z - a.z * b.y,
			a.z * b.x - a.x * b.z,
			a.x * b.y - a.y * b.x
		)
	}
}

