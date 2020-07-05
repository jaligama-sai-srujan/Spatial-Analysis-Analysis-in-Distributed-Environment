package cse512
import org.apache.spark.sql.SparkSession
import scala.math._

object SpatialQuery extends App{
  def runRangeQuery(spark: SparkSession, arg1: String, arg2: String): Long = {

    val pointDf = spark.read.format("com.databricks.spark.csv").option("delimiter","\t").option("header","false").load(arg1);
    pointDf.createOrReplaceTempView("point")

    spark.udf.register("ST_Contains",(queryRectangle:String, pointString:String)=>{
		val RectanglePoints = queryRectangle.split(",").map(x => x.toDouble)
		val Points = pointString.split(",").map(x => x.toDouble)
		if(RectanglePoints(0) <= Points(0) & RectanglePoints(2) >= Points(0))
		{
			if(RectanglePoints(1) <= Points(1) & RectanglePoints(3) >= Points(1))
			{
			true
			}
			else{
				false
			}
		}
		else{
			false
		}
	})

    val resultDf = spark.sql("select * from point where ST_Contains('"+arg2+"',point._c0)")
    resultDf.show()

    return resultDf.count()
  }

  def runRangeJoinQuery(spark: SparkSession, arg1: String, arg2: String): Long = {

    val pointDf = spark.read.format("com.databricks.spark.csv").option("delimiter","\t").option("header","false").load(arg1);
    pointDf.createOrReplaceTempView("point")

    val rectangleDf = spark.read.format("com.databricks.spark.csv").option("delimiter","\t").option("header","false").load(arg2);
    rectangleDf.createOrReplaceTempView("rectangle")

    // YOU NEED TO FILL IN THIS USER DEFINED FUNCTION
    spark.udf.register("ST_Contains",(queryRectangle:String, pointString:String)=>{
		val RectanglePoints = queryRectangle.split(",").map(x => x.toDouble)
		val Points = pointString.split(",").map(x => x.toDouble)
		if(RectanglePoints(0) <= Points(0) & RectanglePoints(2) >= Points(0))
		{
			if(RectanglePoints(1) <= Points(1) & RectanglePoints(3) >= Points(1))
			{
			true
			}
			else{
				false
			}
		}
		else{
			false
		}
	})
	
    val resultDf = spark.sql("select * from rectangle,point where ST_Contains(rectangle._c0,point._c0)")
    resultDf.show()

    return resultDf.count()
  }
	
  def runDistanceQuery(spark: SparkSession, arg1: String, arg2: String, arg3: String): Long = {

    val pointDf = spark.read.format("com.databricks.spark.csv").option("delimiter","\t").option("header","false").load(arg1);
    pointDf.createOrReplaceTempView("point")

    // YOU NEED TO FILL IN THIS USER DEFINED FUNCTION
	spark.udf.register("ST_Within",(pointString1:String, pointString2:String, distance:Double)=> {
		val Point1 = pointString1.split(",").map(x => x.toDouble)
		val Point2 = pointString2.split(",").map(x => x.toDouble)
		val dist = sqrt(pow(Point1(0)-Point2(0),2) + pow(Point1(1)-Point2(1),2))
		if(dist<=distance)
			true
		else
			false
	})

    val resultDf = spark.sql("select * from point where ST_Within(point._c0,'"+arg2+"',"+arg3+")")
    resultDf.show()

    return resultDf.count()
  }

  def runDistanceJoinQuery(spark: SparkSession, arg1: String, arg2: String, arg3: String): Long = {

    val pointDf = spark.read.format("com.databricks.spark.csv").option("delimiter","\t").option("header","false").load(arg1);
    pointDf.createOrReplaceTempView("point1")

    val pointDf2 = spark.read.format("com.databricks.spark.csv").option("delimiter","\t").option("header","false").load(arg2);
    pointDf2.createOrReplaceTempView("point2")

    // YOU NEED TO FILL IN THIS USER DEFINED FUNCTION
	spark.udf.register("ST_Within",(pointString1:String, pointString2:String, distance:Double)=> {
		val Point1 = pointString1.split(",").map(x => x.toDouble)
		val Point2 = pointString2.split(",").map(x => x.toDouble)
		val dist = sqrt(pow(Point1(0)-Point2(0),2) + pow(Point1(1)-Point2(1),2))
		if(dist<=distance)
			true
		else
			false
	})
	
    val resultDf = spark.sql("select * from point1 p1, point2 p2 where ST_Within(p1._c0, p2._c0, "+arg3+")")
    resultDf.show()

    return resultDf.count()
  }
}
