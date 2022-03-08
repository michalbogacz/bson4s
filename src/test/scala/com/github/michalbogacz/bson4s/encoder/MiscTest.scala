package com.github.michalbogacz.bson4s.encoder

import com.github.michalbogacz.bson4s.BsonWriter
import com.github.michalbogacz.bson4s.encoder.instances.{Collections, Misc, Primitives}
import org.mongodb.scala.Document
import org.mongodb.scala.bson.{BsonDocument, BsonNull}
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class MiscTest extends AnyFlatSpecLike with Matchers with Primitives with Collections with Misc{

  sealed trait Animal
  case object Bat extends Animal
  case class Cat(name: String) extends Animal

  it should "encode trait" in {
    val cat = Cat("Ludwik")

    val animalEncoder = BsonWriter.bsonEncoder[Animal]

    val catResult : Document = animalEncoder.encode(cat).asInstanceOf[BsonDocument]

    catResult shouldBe Document(
      "className" -> "com.github.michalbogacz.bson4s.encoder.MiscTest.Cat",
      "name" -> "Ludwik"
    )

    val batResult : Document = animalEncoder.encode(Bat).asInstanceOf[BsonDocument]
    batResult shouldBe Document(
      "className" -> "com.github.michalbogacz.bson4s.encoder.MiscTest.Bat"
    )
  }

  case class OptionTest(text: Option[String], number: Option[Int])

  it should "encode option" in {
    val optionTest = OptionTest(
      number = Some(1),
      text = None
    )

    val miscEncoder = BsonWriter.bsonEncoder[OptionTest]

    val result = miscEncoder.encode(optionTest)

    result shouldBe Document(
      "number" -> 1,
      "text" -> BsonNull()
    ).toBsonDocument()
  }
}
