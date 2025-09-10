package org.example

import bufstream_demo.UserOuterClass.User
import build.buf.protovalidate.ValidatorFactory
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration
import java.util.Properties

fun main() {
    val props = Properties().apply {
        put("bootstrap.servers", "localhost:9092")
        put("group.id", "topic1-consumer")
        put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
        put("auto.offset.reset", "earliest")
    }
    val consumer = KafkaConsumer<String, ByteArray>(props)
    consumer.subscribe(listOf("topic1"))

    while (true) {
        val records = consumer.poll(Duration.ofSeconds(1))
        for (r in records) {
            val msg: User = User.parseFrom(r.value())
            val validator = ValidatorFactory.newBuilder().build()
            val validationResult = validator.validate(msg)
            println("$validationResult")
        }
    }
}