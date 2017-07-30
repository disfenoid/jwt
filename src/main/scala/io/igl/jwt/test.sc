import io.circe._
import io.igl.jwt.{Alg, Algorithm, DecodedJwt, Iss}

val alg = Alg(Algorithm.HS256)
val iss = Iss("jeff")
val jwt = new DecodedJwt(Seq(alg), Seq(iss))

val encoded = jwt.encodedAndSigned("secret")

val iss2 = Iss.attemptApply(
  parser.parse(
    """
      "jeff"
    """.stripMargin
  ).toOption.get
)