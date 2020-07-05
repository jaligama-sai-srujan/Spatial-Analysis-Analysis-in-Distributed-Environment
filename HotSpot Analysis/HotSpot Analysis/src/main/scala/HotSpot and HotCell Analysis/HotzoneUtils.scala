package cse512

object HotzoneUtils {

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

}
