package com.yukihirai0505.jobinterview

object VerifyItems {

  def verifyItems(origitems: Array[String], origprices: Array[Float], items: Array[String], prices: Array[Float]): Int = {
    def count(i: Int = 0, total: Int = 0): Int = {
      if (i > items.length - 1) total
      else {
        val item = items(i)
        val price: Float = origprices(origitems.indexOf(item))
        prices.find(_.equals(price)) match {
          case Some(_) => count(i + 1, total)
          case _ => count(i + 1, total + 1)
        }
      }
    }

    count()
  }

  def main(args: Array[String]): Unit = {
    println(verifyItems(
      Array("rice", "sugar", "wheat", "cheese"),
      Array(16.89, 56.92, 20.89, 345.99).map(_.toFloat),
      Array("rice", "cheese"),
      Array(16.89, 400.89).map(_.toFloat))
    )
  }
}
