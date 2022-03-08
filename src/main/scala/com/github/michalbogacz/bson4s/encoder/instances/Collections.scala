package com.github.michalbogacz.bson4s.encoder.instances

import com.github.michalbogacz.bson4s.encoder.BsonValueEncoder
import com.github.michalbogacz.bson4s.Syntax._
import org.mongodb.scala.bson.BsonArray
import org.mongodb.scala.bson.BsonDocument

trait Collections {

  implicit def seqEncoder[T: BsonValueEncoder]: BsonValueEncoder[Seq[T]] =
    (values: Seq[T]) => {
      BsonArray.fromIterable(values.map(x => x.toBson))
    }

  implicit def listEncoder[T: BsonValueEncoder]: BsonValueEncoder[List[T]] =
    (values: List[T]) => {
      BsonArray.fromIterable(values.map(x => x.toBson))
    }

  implicit def vectorEncoder[T: BsonValueEncoder]: BsonValueEncoder[Vector[T]] =
    (values: Vector[T]) => {
      BsonArray.fromIterable(values.map(x => x.toBson))
    }

  implicit def setEncoder[T: BsonValueEncoder]: BsonValueEncoder[Set[T]] =
    (values: Set[T]) => {
      BsonArray.fromIterable(values.map(x => x.toBson))
    }

  implicit def mapEncoder[T: BsonValueEncoder]
      : BsonValueEncoder[Map[String, T]] = (values: Map[String, T]) => {
    BsonDocument(values.transform { (_, v) => v.toBson })
  }

}

object Collections extends Collections
