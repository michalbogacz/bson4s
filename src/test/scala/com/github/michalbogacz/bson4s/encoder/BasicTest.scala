package com.github.michalbogacz.bson4s.encoder

import com.github.michalbogacz.bson4s.BsonWriter
import org.mongodb.scala.Document
import org.mongodb.scala.bson.{BsonDocument, ObjectId}
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers
import com.github.michalbogacz.bson4s.encoder.instances.All._

class BasicTest extends AnyFlatSpecLike with Matchers {

  case class SimpleTest(a: Int, b: Boolean, c: Double, d: String, id: ObjectId)

  it should "encode simple case class" in {
    val simpleEncoder = BsonWriter.bsonEncoder[SimpleTest]

    val simpleInstance = SimpleTest(
      id = new ObjectId("6192697bae3320b55c3fb2bb"),
      a = 1,
      b = false,
      c = 0.1,
      d = "test"
    )

    val result : Document = simpleEncoder.encode(simpleInstance).asInstanceOf[BsonDocument]

    result shouldBe Document(
      "id" -> new ObjectId("6192697bae3320b55c3fb2bb"),
      "a" -> 1,
      "b" -> false,
      "c" -> 0.1,
      "d" -> "test"
    )
  }

}
