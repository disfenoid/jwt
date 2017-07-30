package io.igl.jwt

import io.circe.Json


/**
 * A representation of a jwt field value.
 */
trait JwtValue {

  /** The field to which a value belongs **/
  val field: JwtField

  /** The real value of a field **/
  val value: Any

  /** The value of a field represented as json **/
  val jsValue: Json

}
