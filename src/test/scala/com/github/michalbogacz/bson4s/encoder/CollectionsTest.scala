package com.github.michalbogacz.bson4s.encoder

import com.github.michalbogacz.bson4s.BsonWriter
import com.github.michalbogacz.bson4s.encoder.instances.Collections
import com.github.michalbogacz.bson4s.encoder.instances.Primitives
import org.mongodb.scala.Document
import org.mongodb.scala.bson.BsonArray
import org.mongodb.scala.bson.BsonDocument
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class CollectionsTest extends AnyFlatSpecLike with Matchers with Primitives with Collections {

  case class TestCollections(list: List[Int], seq: Seq[Int], set: Set[Int], map: Map[String, Int])

  it should "encode collections" in {
    val coll = TestCollections(
      list = List(1,2,3),
      seq = Seq(3,2,1),
      map = Map("1" -> 1),
      set = Set(4,5,6)
    )

    val collectionsEncoder = BsonWriter.bsonEncoder[TestCollections]

    val catResult : Document = collectionsEncoder.encode(coll).asInstanceOf[BsonDocument]

    catResult shouldBe Document(
      "list" -> BsonArray(1,2,3),
      "seq" -> BsonArray(3,2,1),
      "map" -> Document("1" -> 1),
      "set" -> BsonArray(4,5,6)
    )
  }
}
