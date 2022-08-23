fun main(args: Array<String>) {
	data class Projectile(var position: Tuple, var velocity: Tuple)
	data class Environment(val gravity: Tuple, val wind: Tuple)

	val p = Projectile(Point(0, 1, 0), Vector(1, 1, 0).normalize())
	val e = Environment(Vector(0, -0.1, 0), Vector(-0.01, 0, 0))
	var tick = 0

	while (p.position.y >= 0) {
		println("${p.position.x}\t${p.position.y}")

		p.position += ( p.velocity)
		p.velocity += ( e.gravity + e.wind)

		tick++
	}
}
