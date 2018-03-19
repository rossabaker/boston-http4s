package boston2018

import cats._
import cats.implicits._
import fs2.Stream
import scala.concurrent.Future

object Translator {
  def future(s: String): Future[String] =
    apply[Future](s)

  def apply[F[_]: Applicative](s: String): F[String] = 
    s match {
      case "one" => "uno".pure[F]
      case "Hello, Boston!" => "¡Hola, Boston!".pure[F]
      case PaulReveresRide => PaulReverseRideEs.pure[F]
    }

  def streaming[F[_]](s: Stream[F, String]): Stream[F, String] = {
    val _ = s
    Stream.emit(PaulReverseRideEs).through(fs2.text.lines)
  }
}
