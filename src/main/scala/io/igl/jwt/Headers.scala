package io.igl.jwt

import io.circe._

trait HeaderValue extends JwtValue {
  val field: HeaderField
}

trait HeaderField extends JwtField {
  def attemptApply(value: Json): Option[HeaderValue]
}

case class Typ(value: String) extends HeaderValue {
  override val field: HeaderField = Typ
  override val jsValue: Json = Json.fromString(value)
}

object Typ extends HeaderField {
  override def attemptApply(value: Json): Option[Typ] =
    value.as[String].toOption.map(apply)

  override val name = "typ"
}

case class Alg(value: Algorithm) extends HeaderValue {
  override val field: HeaderField = Alg
  override val jsValue: Json = Json.fromString(value.name)
}

object Alg extends HeaderField {
  override def attemptApply(value: Json): Option[Alg] =
    value.as[String]
      .toOption
      .flatMap(Algorithm.getAlgorithm).map(apply)

  override val name = "alg"
}

case object Cty extends HeaderField with HeaderValue {
  override val jsValue: Json = Json.fromString(value)

  override val name = "cty"
  override val field: HeaderField = this
  override val value = "JWT"

  override def attemptApply(value: Json): Option[HeaderValue] =
    value.as[String].toOption.map { case this.value => Cty }
}
