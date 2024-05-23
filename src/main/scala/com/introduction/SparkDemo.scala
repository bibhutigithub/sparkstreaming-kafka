package com.introduction

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

case class Employee(id: Int, name: String)

object SparkDemo {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder()
      .master("local[1]")
      .appName("SparkDemo")
      .getOrCreate()

    import spark.implicits._

    val dataSeq = Seq((100, "Bibhuti"), (200, "Soumya"), (300, "Plaban"))
    val dataList = Seq(Row(100, "Bibhuti"), Row(200, "Soumya"), Row(300, "Plaban"))
    val mySchema = StructType(List(StructField("id", IntegerType, true), StructField("name", StringType, true)))
    val dataRdd = spark.sparkContext.parallelize(dataList)
    val df1 = dataSeq.toDF("id", "name")
    val df2 = spark.createDataFrame(dataRdd, mySchema)
    df1.show()
  }
}

