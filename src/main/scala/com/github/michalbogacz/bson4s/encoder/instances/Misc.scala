package com.github.michalbogacz.bson4s.encoder.instances

import com.github.michalbogacz.bson4s.Syntax.EncoderOps
import com.github.michalbogacz.bson4s.encoder.BsonValueEncoder
import org.bson.BsonNull

trait Misc {

  implicit def optionEncoder[T: BsonValueEncoder]
      : BsonValueEncoder[Option[T]] = {
    case None            => new BsonNull()
    case Some(someValue) => someValue.toBson
  }

}

object Misc extends Misc
