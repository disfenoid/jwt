package io.igl.jwt

import io.circe.Json

/** Scope claim 
  * 
  * Not in JWT specification but required by Google Cloud Platform for example
  * 
  */
case class Scope(value: String) extends ClaimValue {
  override val jsValue: Json = Json.fromString(value)
  override val field: ClaimField = Scope
}

object Scope extends ClaimField {
  override def attemptApply(value: Json): Option[Scope] =
    value.as[String].toOption.map(apply)
  override val name = "scope"
}
