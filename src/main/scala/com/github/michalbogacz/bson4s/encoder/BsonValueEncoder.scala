package com.github.michalbogacz.bson4s.encoder

import org.bson.BsonValue

trait BsonValueEncoder[A] {
  def encode(value: A): BsonValue
}
