# Practices - Data engineering

## TP - Data processing with Apache Spark
To process a large amount of data partitioned on a data lake, you can use data processing frameworks such as Apache Spark :
1. Read : https://spark.apache.org/docs/latest/sql-programming-guide.html

Some questions :
* What is Spark RDD API ?

  The Spark RDD API is the programming interface used to work with RDDs in Apache Spark, which are distributed collections of objects that can be processed in parallel across a cluster of machines.


* What is Spark Dataset API ?

  The Spark Dataset API is a high-level, strongly-typed API for working with structured and semi-structured data in Apache Spark. It is built on top of the RDD API and offers a more efficient and expressive way to work with structured data compared to the RDD API.


* With which languages can you use Spark ? 

    Scala, Java, Python, R


* Which data sources or data sinks can Spark work with ? 
    - DataSources:
      - File Systems
      - Apache Kafka
      - Apache Flume
      - JDBC Data Sources
    - Data Sinks:
      - File Systems
      - Apache Kafka
      - Apache Cassandra
      - JDBC Data Sinks

### Analyse data with Apache Spark and Scala 
One engineering team of your company created for you a TV News data stored as JSON inside the folder `data-news-json/`.

Your goal is to analyze it with your savoir-faire, enrich it with metadata, and store it as [a column-oriented format](https://parquet.apache.org/).

1. Look at `src/main/scala/com/github/polomarcus/main/Main.scala` and update the code 

**Important note:** As you work for a top-notch software company following world-class practices, and you care about your project quality, you'll write a test for every function you write.

You can see tests inside `src/test/scala/` and run them with `sbt test`

### How can you deploy your app to a cluster of machines ?
* https://spark.apache.org/docs/latest/cluster-overview.html

### Business Intelligence (BI)
How could use we Spark to display data on a BI tool such as [Metabase](https://www.metabase.com/) ?

Tips: https://github.com/polomarcus/television-news-analyser#spin-up-1-postgres-metabase-nginxand-load-data-to-pg

### Continuous build and test
**Pro Tips** : https://www.scala-sbt.org/1.x/docs/Running.html#Continuous+build+and+test

Make a command run when one or more source files change by prefixing the command with ~. For example, in sbt shell try:
```bash
sbt
> ~ testQuick
```