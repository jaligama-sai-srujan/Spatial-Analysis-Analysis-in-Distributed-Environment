# Large Scale GeoSpatial Analysis in Distributed Environment

## Description
The project was aimed to setup a spark cluster with HDFS and run geo-spatial queries on the clusters.

* The setup was done in both Amazon EC2 cluster instances and Google Cloud Platform Data Proc's Cluster with Hadoop(version 2.7.7) and Apache Spark(version 2.0.2)
* Geo-Spatial Queries executed are range query, range join query, distance query, distance join query, hot zone analysis and hot cell analysis.
* ST_contains and ST_within are the user defined functions implemented to execute Spatial queries.
** ST_contains takes a point and a rectangle and returns a boolean indicating whether the point is inside the rectangle.
** ST_within takes two points and a distance and returns a boolean indication whether the distance between the points is not more than the distance provided.


## Technology used: Apache Spark, Hadoop Distributed File System (HDFS), Scala, sbt build tool, Amazon EC2, Google Cloud Platform DataProc.
