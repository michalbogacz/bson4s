package com.github.michalbogacz.bson4s.encoder.instances

import com.github.michalbogacz.bson4s.encoder.BsonValueEncoder
import org.bson.{BsonBoolean, BsonDouble, BsonInt32, BsonInt64, BsonObjectId, BsonString}
import org.bson.types.ObjectId

trait Primitives {

  implicit def intBson: BsonValueEncoder[Int] = (value: Int) =>
    new BsonInt32(value)

  implicit def longBson: BsonValueEncoder[Long] = (value: Long) =>
    new BsonInt64(value)

  implicit def booleanBson: BsonValueEncoder[Boolean] = (value: Boolean) =>
    new BsonBoolean(value)

  implicit def floatBson: BsonValueEncoder[Float] = (value: Float) =>
    new BsonDouble(value)

  implicit def doubleBson: BsonValueEncoder[Double] = (value: Double) =>
    new BsonDouble(value)

  implicit def stringBson: BsonValueEncoder[String] = (value: String) =>
    new BsonString(value)

  implicit def objectIdBson: BsonValueEncoder[ObjectId] = (value: ObjectId) =>
    new BsonObjectId(value)

}

object Primitives extends Primitives
