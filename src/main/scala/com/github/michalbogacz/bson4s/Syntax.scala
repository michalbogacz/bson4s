package com.github.michalbogacz.bson4s

import com.github.michalbogacz.bson4s.encoder.BsonValueEncoder
import org.bson.BsonValue

object Syntax {

  implicit class EncoderOps[T](val t: T) extends AnyVal {
    def toBson(implicit encoder: BsonValueEncoder[T]): BsonValue =
      encoder.encode(t)
  }

//  implicit class DecoderOps(val json: Json) extends AnyVal {
//    def to[T](implicit decoder: Decoder[T]): Result[T] = decoder.decode(json)
//  }

}
