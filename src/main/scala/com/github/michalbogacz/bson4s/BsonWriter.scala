package com.github.michalbogacz.bson4s

import com.github.michalbogacz.bson4s.encoder.BsonValueEncoder
import magnolia1.CaseClass
import magnolia1.Magnolia
import magnolia1.SealedTrait
import org.bson.{BsonDocument, BsonString, BsonValue}

import scala.jdk.CollectionConverters._
import scala.language.experimental.macros

object BsonWriter {

  type Typeclass[T] = BsonValueEncoder[T]

  def join[T](ctx: CaseClass[BsonValueEncoder, T]): BsonValueEncoder[T] = {
    new Typeclass[T] {
      override def encode(value: T): BsonValue = {
        val document = new BsonDocument()
        ctx.parameters.foreach { param =>
          val fieldVal = param.dereference(value)
          val result = param.typeclass.encode(fieldVal)
          document.put(param.label, result)
        }
        document
      }
    }
  }

  def split[T](ctx: SealedTrait[BsonValueEncoder, T]): BsonValueEncoder[T] = {
    value =>
      ctx.split(value) { sub =>
        val encoded = sub.typeclass.encode(sub.cast(value))

        val traitDocument =
          new BsonDocument("className", new BsonString(sub.typeName.full))
        encoded match {
          case value: BsonDocument =>
            val builder = Map.newBuilder[String, BsonValue]
            value.entrySet().forEach(x => builder += x.getKey -> x.getValue)
            traitDocument.putAll(builder.result().asJava)
            traitDocument
          case _ =>
            traitDocument
        }
      }
  }

  implicit def bsonEncoder[T]: BsonValueEncoder[T] = macro Magnolia.gen[T]

}
