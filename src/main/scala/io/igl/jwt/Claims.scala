package io.igl.jwt

import io.circe._

trait ClaimValue extends JwtValue {
  val field: ClaimField
}

trait ClaimField extends JwtField {
  def attemptApply(value: Json): Option[ClaimValue]
}

case class Iss(value: String) extends ClaimValue {
  override val field: ClaimField = Iss
  override val jsValue: Json = Json.fromString(value)
}

object Iss extends ClaimField {
  override def attemptApply(value: Json): Option[ClaimValue] =
    value.as[String]
      .toOption
      .map(apply)

  override val name = "iss"
}

case class Sub(value: String) extends ClaimValue {
  override val jsValue: Json = Json.fromString(value)
  override val field: ClaimField = Sub
}

object Sub extends ClaimField {
  override def attemptApply(value: Json): Option[Sub] =
    value.as[String]
      .toOption
      .map(apply)

  override val name = "sub"
}

case class Aud(value: Either[String, Seq[String]]) extends ClaimValue {
  override val field: ClaimField = Aud
  override val jsValue: Json = value match {
    case Left(single) => Json.fromString(single)
    case Right(many) => Json.fromValues(many.map(Json.fromString))
  }
}

object Aud extends ClaimField {
  def apply(value: String): Aud = Aud(Left(value))
  def apply(value: Seq[String]): Aud = Aud(Right(value))

  override def attemptApply(value: Json): Option[ClaimValue] =
    value.as[Seq[String]].toOption
      .map(v => Aud(Right(v))).orElse {
      value.as[String]
        .toOption
        .map(v => Aud(Left(v)))
    }

  override val name = "aud"
}

case class Exp(value: Long) extends ClaimValue {
  override val field: ClaimField = Exp
  override val jsValue: Json = Json.fromLong(value)
}

case object Exp extends ClaimField {
  override def attemptApply(value: Json): Option[ClaimValue] =
    value.as[Long]
      .toOption
      .map(apply)

  override val name = "exp"
}

case class Nbf(value: Long) extends ClaimValue {
  override val field: ClaimField = Nbf
  override val jsValue: Json = Json.fromLong(value)
}

object Nbf extends ClaimField {
  override def attemptApply(value: Json): Option[ClaimValue] =
    value.as[Long].map(apply).toOption

  override val name = "nbf"
}

case class Iat(value: Long) extends ClaimValue {
  override val field: ClaimField = Iat
  override val jsValue: Json = Json.fromLong(value)
}

object Iat extends ClaimField {
  override def attemptApply(value: Json): Option[ClaimValue] =
    value.as[Long].map(apply).toOption

  override val name = "iat"
}

case class Jti(value: String) extends ClaimValue {
  override val field: ClaimField = Jti
  override val jsValue: Json = Json.fromString(value)
}

object Jti extends ClaimField {
  override def attemptApply(value: Json): Option[ClaimValue] =
    value.as[String].map(apply).toOption

  override val name = "jti"
}
