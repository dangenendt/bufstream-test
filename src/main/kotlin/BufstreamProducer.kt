package org.example

import bufstream_demo.UserOuterClass.User
import build.buf.protovalidate.ValidatorFactory
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.Properties

fun main() {
    val props = Properties().apply {
        put("bootstrap.servers", "localhost:9092")
        put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer")
    }

    val producer = KafkaProducer<String, ByteArray>(props)

    // uncomment if you want to produce an invalid message
    // val user = User.newBuilder().setId(123).setName("DavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavid").build()
    // uncomment if you want to produce a valid message
    val user = User.newBuilder().setId(123).setName("David").build()

    val validator = ValidatorFactory.newBuilder().build()
    val validationResult = validator.validate(user)
    println("$validationResult")
    // cancel producing on invalid message

    val record = ProducerRecord("topic1", user.id.toString(), user.toByteArray())
    producer.send(record).get()
    println("Sent user: $user")
    producer.close()
}