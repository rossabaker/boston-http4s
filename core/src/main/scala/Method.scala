package boston2018

sealed trait Method
case object GET extends Method
case object POST extends Method

sealed trait Status
case object OK extends Status
case object NotFound extends Status
