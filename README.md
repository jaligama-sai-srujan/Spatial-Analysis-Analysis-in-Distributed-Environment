# Large Scale GeoSpatial Analysis in Distributed Environment

## Description
The project was aimed to setup a spark cluster with HDFS and run geo-spatial queries on the clusters.

* The setup was done in both Amazon EC2 cluster instances and Google Cloud Platform Data Proc's Cluster with Hadoop(version 2.7.7) and Apache Spark(version 2.0.2)
* Geo-Spatial Queries executed are range query, range join query, distance query, distance join query, hot zone analysis and hot cell analysis.
* ST_contains and ST_within are the user defined functions implemented to execute Spatial queries.
  * ST_contains takes a point and a rectangle and returns a boolean indicating whether the point is inside the rectangle.
  * ST_within takes two points and a distance and returns a boolean indication whether the distance between the points is not more than the distance provided.


## Technology used: Apache Spark, Hadoop Distributed File System (HDFS), Scala, sbt build tool, Amazon EC2, Google Cloud Platform DataProc.

### Spatial Queries Description:
* Range query: Use ST_Contains. Given a query rectangle R and a set of points P, find all the points within R.
* Range join query: Use ST_Contains. Given a set of Rectangles R and a set of Points S, find all (Point, Rectangle) pairs such that the point is within the rectangle.
* Distance query: Use ST_Within. Given a point location P and distance D in km, find all points that lie within a distance D from P
* Distance join query: Use ST_Within. Given a set of Points S1 and a set of Points S2 and a distance D in km, find all (s1, s2) pairs such that s1 is within a distance D from s2 (i.e., s1 belongs to S1 and s2 belongs to S2).

### Spatial Hot Spot Analysis:
* Hot zone analysis: Hot zone analysis will perform a range join operation on a rectangle datasets and a point dataset. For each rectangle, the number of points located within the rectangle will be obtained. The hotter rectangle means that it include more points. So this task is to calculate the hotness of all the rectangles.
* Hot cell analysis will focus on applying spatial statistics to spatio-temporal big data in order to identify statistically significant spatial hot spots.
