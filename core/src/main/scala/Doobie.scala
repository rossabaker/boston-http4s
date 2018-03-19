package boston2018

import cats.effect.IO
import cats.implicits._
import fs2._
import doobie._
import doobie.h2._
import doobie.h2.implicits._
import doobie.implicits._

object DoobieExample {
  val drop = sql"""DROP TABLE IF EXISTS numbers""".update.run

  val create = sql"""
    CREATE TABLE numbers (
      id   SERIAL,
      en   VARCHAR NOT NULL,
    )
  """.update.run

  def insert1(id: Int, en: String): Update0 =
    sql"insert into numbers (id, en) values ($id, $en)".update

  def setUp(xa: Transactor[IO]): IO[Unit] = {
    (drop, create).mapN(_ + _).transact(xa) *>
    (Map(
      1 -> "one",
      2 -> "two",
      3 -> "three",
    ).toList.traverse { case (k, v) => insert1(k, v).run.transact(xa) }).void
  }
}
