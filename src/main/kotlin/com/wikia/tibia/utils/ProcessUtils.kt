package com.wikia.tibia.utils

fun pauseForABit() {
  try {
    Thread.sleep(200)
  } catch (e: InterruptedException) {
    Thread.currentThread().interrupt()
  }
}
