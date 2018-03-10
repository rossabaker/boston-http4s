/* Sloppy code full of stubs so the slide deck compiles
 * and looks like it's doing real work.
 * 
 * Generally not to be emulated.
 */

package com.rossabaker.talks.tls2018

import cats._
import cats.effect._
import cats.data.OptionT
import fs2.Stream
import scala.concurrent.Future

sealed trait Language
case object English extends Language
case object Spanish extends Language

object Translator {
  def apply(from: Language, to: Language)(s: String): Future[String] =
    Future.successful {
      s match {
        case "one" => "uno"
      }
    }

  def get[F[_]](from: Language, to: Language)(s: String)(implicit F: Applicative[F]): F[String] =
    F.pure(Poems.PaulReverseRideEs)

  def streaming[F[_]](from: Language, to: Language)(s: Stream[F, String])(implicit F: Applicative[F]): Stream[F, String] =
    Stream.emit(Poems.PaulReverseRideEs).through(fs2.text.lines)
}

object Poems {
  val PaulReveresRide = """
Listen, my children, and you shall hear
Of the midnight ride of Paul Revere,
On the eighteenth of April, in Seventy-Five:
Hardly a man is now alive 
Who remembers that famous day and year.

He said to his friend, “If the British march
By land or sea from the town to-night,
Hang a lantern aloft in the belfry-arch
Of the North-Church-tower, as a signal-light,--
One if by land, and two if by sea;
And I on the opposite shore will be,
Ready to ride and spread the alarm
Through every Middlesex village and farm,
For the country-folk to be up and to arm.”

Then he said “Good night!” and with muffled oar
Silently rowed to the Charlestown shore,
Just as the moon rose over the bay,
Where swinging wide at her moorings lay
The Somerset, British man-of-war:
A phantom ship, with each mast and spar
Across the moon, like a prison-bar,
And a huge black hulk, that was magnified 
By its own reflection in the tide.

Meanwhile, his friend, through alley and street
Wanders and watches with eager ears, 
Till in the silence around him he hears 
The muster of men at the barrack door,
The sound of arms, and the tramp of feet, 
And the measured tread of the grenadiers 
Marching down to their boats on the shore.

Then he climbed to the tower of the church,
Up the wooden stairs, with stealthy tread,
To the belfry-chamber overhead,
And startled the pigeons from their perch
On the sombre rafters, that round him made
Masses and moving shapes of shade,--
By the trembling ladder, steep and tall,
To the highest window in the wall,
Where he paused to listen and look down
A moment on the roofs of the town,
And the moonlight flowing over all.

Beneath, in the churchyard, lay the dead, 
In their night-encampment on the hill, 
Wrapped in silence so deep and still 
That he could hear, like a sentinel’s tread, 
The watchful night-wind, as it went 
Creeping along from tent to tent, 
And seeming to whisper, “All is well!” 
A moment only he feels the spell 
Of the place and the hour, and the secret dread 
Of the lonely belfry and the dead; 
For suddenly all his thoughts are bent 
On a shadowy something far away, 
Where the river widens to meet the bay, --
A line of black, that bends and floats 
On the rising tide, like a bridge of boats.

Meanwhile, impatient to mount and ride, 
Booted and spurred, with a heavy stride, 
On the opposite shore walked Paul Revere.
Now he patted his horse’s side, 
Now gazed on the landscape far and near, 
Then impetuous stamped the earth, 
And turned and tightened his saddle-girth;
But mostly he watched with eager search 
The belfry-tower of the old North Church, 
As it rose above the graves on the hill, 
Lonely and spectral and sombre and still.
And lo! as he looks, on the belfry’s height, 
A glimmer, and then a gleam of light!
He springs to the saddle, the bridle he turns, 
But lingers and gazes, till full on his sight 
A second lamp in the belfry burns!

A hurry of hoofs in a village-street,
A shape in the moonlight, a bulk in the dark, 
And beneath from the pebbles, in passing, a spark 
Struck out by a steed that flies fearless and fleet: 
That was all! And yet, through the gloom and the light, 
The fate of a nation was riding that night; 
And the spark struck out by that steed, in his flight, 
Kindled the land into flame with its heat.

He has left the village and mounted the steep,
And beneath him, tranquil and broad and deep,
Is the Mystic, meeting the ocean tides;
And under the alders, that skirt its edge,
Now soft on the sand, now loud on the ledge,
Is heard the tramp of his steed as he rides.

It was twelve by the village clock
When he crossed the bridge into Medford town.
He heard the crowing of the cock, 
And the barking of the farmer’s dog, 
And felt the damp of the river-fog,
That rises when the sun goes down.

It was one by the village clock,
When he galloped into Lexington. 
He saw the gilded weathercock 
Swim in the moonlight as he passed, 
And the meeting-house windows, blank and bare, 
Gaze at him with a spectral glare, 
As if they already stood aghast 
At the bloody work they would look upon.

It was two by the village clock,
When be came to the bridge in Concord town. 
He heard the bleating of the flock, 
And the twitter of birds among the trees, 
And felt the breath of the morning breeze
Blowing over the meadows brown.
And one was safe and asleep in his bed
Who at the bridge would be first to fall,
Who that day would be lying dead,
Pierced by a British musket-ball.

You know the rest. In the books you have read,
How the British Regulars fired and fled,--
How the farmers gave them ball for ball,
From behind each fence and farmyard-wall,
Chasing the red-coats down the lane,
Then crossing the fields to emerge again
Under the trees at the turn of the road,
And only pausing to fire and load.

So through the night rode Paul Revere;
And so through the night went his cry of alarm
To every Middlesex village and farm,-- 
A cry of defiance, and not of fear,
A voice in the darkness, a knock at the door,
And a word that shall echo forevermore!
For, borne on the night-wind of the Past,
Through all our history, to the last,
In the hour of darkness and peril and need,
The people will waken and listen to hear
The hurrying hoof-beats of that steed,
And the midnight message of Paul Revere.
  """

  val PaulReveresStream = Stream.emit(PaulReveresRide)
    .through(fs2.text.utf8Encode)

  val PaulReverseRideEs = """
Escuchen, hijos míos, y oirán
Del paseo de medianoche de Paul Revere,
El dieciocho de abril, en Setenta y cinco:
Apenas un hombre está vivo
¿Quién recuerda ese famoso día y año?

Le dijo a su amigo: "Si la marcha británica
Por tierra o mar desde el pueblo esta noche,
Cuelgue una linterna en el arco campanario
De la Torre de la Iglesia del Norte, como una luz de señal, -
Uno si por tierra, y dos si por mar;
Y yo en la orilla opuesta,
Listo para montar y difundir la alarma
A través de cada aldea y granja de Middlesex,
Para que la gente del campo se levante y se arme ".

Luego dijo "¡Buenas noches!" Y con el remo amortiguado
En silencio remado a la orilla de Charlestown,
Justo cuando la luna se elevaba sobre la bahía,
Donde se balanceaba de par en par en sus amarras
The Somerset, buque de guerra británico:
Una nave fantasma, con cada mástil y mástil
Al otro lado de la luna, como un bar de la prisión,
Y un gran casco negro, que se magnificó
Por su propio reflejo en la marea.

Mientras tanto, su amigo, a través de callejón y calle
Vaga y mira con los oídos ansiosos,
Hasta en el silencio que lo rodea oye
La reunión de hombres en la puerta del cuartel,
El sonido de los brazos y el ruido de los pies
Y la medida de los granaderos
Marchando hacia abajo a sus barcos en la orilla.

Luego subió a la torre de la iglesia,
Por las escaleras de madera, con banda de rodadura sigilosa,
A la sobrecarga de la cámara del campanario,
Y sorprendió a las palomas desde su posición
En las sombrías vigas, que a su alrededor hizo
Masas y formas móviles de sombra, -
Por la escalera temblorosa, empinada y alta,
A la ventana más alta en la pared,
Donde se detuvo para escuchar y mirar hacia abajo
Un momento en los techos de la ciudad,
Y la luz de la luna fluyendo sobre todos.

Debajo, en el cementerio, yacen los muertos,
En su campamento nocturno en la colina,
Envuelto en silencio tan profundo y quieto
Que podía oír, como la huella de un centinela,
El viento nocturno vigilante, a medida que avanzaba
Arrastrándose de tienda en tienda,
Y parecía susurrar: "¡Todo está bien!"
Un momento solo él siente el hechizo
Del lugar y la hora, y el terror secreto
Del solitario campanario y de los muertos;
Porque de repente todos sus pensamientos están torcidos
En un oscuro algo lejano,
Donde el río se ensancha para encontrarse con la bahía, -
Una línea de negro, que se dobla y flota
En la marea creciente, como un puente de barcos.

Mientras tanto, impaciente por montar y montar,
Booted y espoleado, con un paso pesado,
En la orilla opuesta caminó Paul Revere.
Ahora palmeó el costado de su caballo,
Ahora miraba el paisaje lejos y cerca,
Entonces impetuoso estampado la tierra,
Y giró y apretó la cincha de su silla de montar;
Pero sobre todo él miraba con ansiosa búsqueda
La torre del campanario de la antigua iglesia del norte,
Cuando se elevó sobre las tumbas en la colina,
Solitario y espectral y sombrío e inmóvil.
Y he aquí! mientras mira, en la altura del campanario,
Un destello, y luego un destello de luz!
Él se tira a la silla, la brida gira,
Pero permanece y mira, hasta que se llena de su vista
¡Una segunda lámpara en el campanario quema!

Una prisa de cascos en una calle del pueblo,
Una forma a la luz de la luna, un bulto en la oscuridad,
Y debajo de los guijarros, de paso, una chispa
Golpeado por un corcel que vuela valiente y flota:
¡Eso fue todo! Y, sin embargo, a través de la oscuridad y la luz,
El destino de una nación fue montar esa noche;
Y la chispa golpeada por ese corcel, en su vuelo,
Iluminó la tierra con su calor.

Él ha dejado el pueblo y montado en la pendiente,
Y debajo de él, tranquilo y amplio y profundo,
Es el místico, conociendo las mareas oceánicas;
Y debajo de los alisos, que bordean su borde,
Ahora suave en la arena, ahora fuerte en la repisa,
Se escucha el vagabundo de su corcel mientras cabalga.

Eran las doce por el reloj del pueblo
Cuando cruzó el puente hacia la ciudad de Medford.
Escuchó el canto del gallo,
Y el ladrido del perro del granjero,
Y sintió la humedad de la niebla del río,
Eso sube cuando el sol se pone.

Era uno al lado del reloj de la aldea,
Cuando galopaba hacia Lexington.
Él vio la veleta dorada
Nada a la luz de la luna al pasar,
Y las ventanas de la casa de reuniones, en blanco y desnudas,
Míralo con una mirada espectral,
Como si ya estuvieran horrorizados
En el trabajo sangriento que mirarían.

Eran las dos por el reloj del pueblo,
Cuando llegó al puente en la ciudad de Concord.
Escuchó los balidos del rebaño,
Y el gorjeo de pájaros entre los árboles,
Y sintió el aliento de la brisa de la mañana
Soplando sobre los prados marrones.
Y uno estaba seguro y dormido en su cama
Quién en el puente sería el primero en caer,
¿Quién ese día estaría muerto?
Perforado por un mosquete británico.

Tu sabes el resto. En los libros que has leído,
Cómo dispararon y huyeron los Regulares británicos, -
Cómo los agricultores les dieron pelota por pelota,
Detrás de cada valla y pared de corral,
Persiguiendo a los abrigos rojos por el camino,
Luego cruzando los campos para emerger nuevamente
Debajo de los árboles en el cambio de la carretera,
Y solo haciendo una pausa para disparar y cargar.

Así que durante la noche cabalgó Paul Revere;
Y así a lo largo de la noche fue su grito de alarma
Para cada aldea y granja de Middlesex, -
Un grito de desafío, y no de miedo,
Una voz en la oscuridad, un golpe en la puerta,
¡Y una palabra que resonará para siempre!
Porque, nacido del viento nocturno del pasado,
A través de toda nuestra historia, hasta el último,
En la hora de la oscuridad, el peligro y la necesidad,
La gente se despertará y escuchará
Los apresurados cascos de ese corcel,
Y el mensaje de medianoche de Paul Revere.
"""
}


object Archive {
  def fetch(path: String): Future[Option[Image]] =
    Future.successful {
      path match {
        case "/zoidberg.png" => Some("lobster.png")
        case _ => None
      }
    }

  def fetchT(path: String): OptionT[Future, Image] =
    OptionT(fetch(path))

  def lfetchT(path: String): OptionT[Future, Image] =
    OptionT(Future.successful {
      path match {
        case "/zoidberg.png" =>
          println("Found in archive")
          Some("lobster.png")
        case _ =>
          println("Not in archive")
          None
      }
    })

  def lfetch[F[_]: Sync](path: String): OptionT[F, Image] =
    OptionT(Sync[F].delay {
      path match {
        case "/zoidberg.png" =>
          println("Found in archive")
          Some("lobster.png")
        case _ =>
          println("Not in archive")
          None
      }
    })
}

object Cache {
  def fetch(path: String): Future[Option[Image]] =
    Future.successful {
      path match {
        case _ => None
      }
    }

  def fetchT(path: String): OptionT[Future, Image] =
    OptionT(fetch(path))

  def lfetchT(path: String): OptionT[Future, Image] =
    OptionT(Future.successful {
      path match {
        case "/zoidberg.png" =>
          println("Found in cache")
          Some("lobster.png")
        case _ =>
          println("Not in cache")
          None
      }
    })

  def lfetch[F[_]: Sync](path: String): OptionT[F, Image] =
    OptionT(Sync[F].delay {
      path match {
        case "/zoidberg.png" =>
          println("Found in cache")
          Some("lobster.png")
        case _ =>
          println("Not in cache")
          None
      }
    })
}
