package ch.coachingglobe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform