import java.io.File
import kotlin.math.roundToInt

fun main(args: Array<String>) {
	data class Projectile(var position: Tuple, var velocity: Tuple)
	data class Environment(val gravity: Tuple, val wind: Tuple)
	data class Position(val x: Double, val y: Double)

	val p = Projectile(Point(0, 1, 0), Vector(1, 1.8, 0).normalize() * 11.25)
	val e = Environment(Vector(0, -0.1, 0), Vector(-0.01, 0, 0))
	val c = Canvas(900, 550)
	var tick = 0

	while (p.position.y >= 0) {
		c[p.position.x.roundToInt(), c.height - p.position.y.roundToInt()] = Colour(0, 1, 0)
		p.position += (p.velocity)
		p.velocity += (e.gravity + e.wind)

		tick++
	}

	File("trajectory.ppm").writeText(c.toPPM())
}
