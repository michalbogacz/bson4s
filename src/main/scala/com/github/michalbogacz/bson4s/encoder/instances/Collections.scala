package com.github.michalbogacz.bson4s.encoder.instances

import com.github.michalbogacz.bson4s.encoder.BsonValueEncoder
import com.github.michalbogacz.bson4s.Syntax._
import org.bson.{BsonArray, BsonDocument, BsonElement}

import scala.jdk.CollectionConverters._

trait Collections {

  implicit def seqEncoder[T: BsonValueEncoder]: BsonValueEncoder[Seq[T]] =
    (values: Seq[T]) => {
      new BsonArray(values.map(x => x.toBson).asJava)
    }

  implicit def listEncoder[T: BsonValueEncoder]: BsonValueEncoder[List[T]] =
    (values: List[T]) => {
      new BsonArray(values.map(x => x.toBson).asJava)
    }

  implicit def vectorEncoder[T: BsonValueEncoder]: BsonValueEncoder[Vector[T]] =
    (values: Vector[T]) => {
      new BsonArray(values.map(x => x.toBson).asJava)
    }

  implicit def setEncoder[T: BsonValueEncoder]: BsonValueEncoder[Set[T]] =
    (values: Set[T]) => {
      new BsonArray(values.map(x => x.toBson).toSeq.asJava)
    }

  implicit def mapEncoder[T: BsonValueEncoder]
      : BsonValueEncoder[Map[String, T]] = (values: Map[String, T]) => {
    val elements: Seq[BsonElement] = values.map { case (k, v) =>
      new BsonElement(k, v.toBson)
    }.toSeq
    new BsonDocument(elements.asJava)
  }

}

object Collections extends Collections
