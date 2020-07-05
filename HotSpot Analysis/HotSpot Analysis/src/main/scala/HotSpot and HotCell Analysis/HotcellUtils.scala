package cse512

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar

object HotcellUtils {
  val coordinateStep = 0.01

  def CalculateCoordinate(inputString: String, coordinateOffset: Int): Int =
  {
    // Configuration variable:
    // Coordinate step is the size of each cell on x and y
    var result = 0
    coordinateOffset match
    {
      case 0 => result = Math.floor((inputString.split(",")(0).replace("(","").toDouble/coordinateStep)).toInt
      case 1 => result = Math.floor(inputString.split(",")(1).replace(")","").toDouble/coordinateStep).toInt
      // We only consider the data from 2009 to 2012 inclusively, 4 years in total. Week 0 Day 0 is 2009-01-01
      case 2 => {
        val timestamp = HotcellUtils.timestampParser(inputString)
        result = HotcellUtils.dayOfMonth(timestamp) // Assume every month has 31 days
      }
    }
    return result
  }

  def timestampParser (timestampString: String): Timestamp =
  {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    val parsedDate = dateFormat.parse(timestampString)
    val timeStamp = new Timestamp(parsedDate.getTime)
    return timeStamp
  }

  def dayOfYear (timestamp: Timestamp): Int =
  {
    val calendar = Calendar.getInstance
    calendar.setTimeInMillis(timestamp.getTime)
    return calendar.get(Calendar.DAY_OF_YEAR)
  }

  def dayOfMonth (timestamp: Timestamp): Int =
  {
    val calendar = Calendar.getInstance
    calendar.setTimeInMillis(timestamp.getTime)
    return calendar.get(Calendar.DAY_OF_MONTH)
  }

  def ST_Contains(queryRectangle: String, pointString: String ): Boolean = {
    // YOU NEED TO CHANGE THIS PART
	val RectanglePoints = queryRectangle.split(",").map(x => x.toDouble)
		val Points = pointString.split(",").map(x => x.toDouble)
		if(RectanglePoints(0) <= Points(0) & RectanglePoints(2) >= Points(0))
		{
			if(RectanglePoints(1) <= Points(1) & RectanglePoints(3) >= Points(1))
			{
			return true
			}
			else{
			return false
			}
		}
		else{
			return false
		}

    //return true // YOU NEED TO CHANGE THIS PART
  }
  // YOU NEED TO CHANGE THIS PART
  
  


  def squared(value: Int): Double = {
    return (value*value).toDouble;
  }
  

  def CountOfNeighbors(minX: Int, minY: Int, minZ: Int, maxX: Int, maxY: Int, maxZ: Int, inputX: Int, inputY: Int, inputZ: Int): Int = {
    var count = 0;

    if (inputX == minX || inputX == maxX) {
      count += 1;
    }

    if (inputY == minY || inputY == maxY) {
      count += 1;
    }

    if (inputZ == minZ || inputZ == maxZ) {
      count += 1;
    }

    if (count == 1) {
      return 17;
    } 
    else if (count == 2) {
      return 11;
    }
    else if (count == 3){
      return 7;
    } 
    else{
      return 26;
    }
	
  }



  def getGScore(x: Int, y: Int, z: Int, mean:Double, sd: Double, countn: Int, sumn: Int, numcells: Int): Double = {
    val numerator = (sumn.toDouble - (mean*countn.toDouble));
    val denominator = sd*math.sqrt((((numcells.toDouble*countn.toDouble) -(countn.toDouble*countn.toDouble))/(numcells.toDouble-1.0).toDouble).toDouble).toDouble;
    return (numerator/denominator).toDouble;
  }
  
}
