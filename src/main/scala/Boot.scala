import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import system.Properties

object Boot {
  def main(args: Array[String]): Unit = {
    println("Start loading tables")
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("com").setLevel(Level.ERROR)

    val spark = SparkSession.builder()
      .master("local")
      .appName("WeatherAccuracy")
      .getOrCreate()

    import spark.implicits._

    val DFOb = spark.read
      .jdbc(Properties.urlPG, Properties.tablePGOb, Properties.propertiesPG)

    val DFFor = spark.read
      .jdbc(Properties.urlPG, Properties.tablePGFor, Properties.propertiesPG)

    val DFJoined = DFFor.as("forecast")
      .join(DFOb.as("observed"), "datetimeiso")
      .withColumn("temp_diff", col("observed.tempc") - col("forecast.tempc"))
      .withColumn("pressure_diff", col("observed.pressuremb") - col("forecast.pressuremb"))
      .withColumn("humidity_diff", col("observed.humidity") - col("forecast.humidity"))
      .withColumn("wind_diff", col("observed.windspeedkph") - col("forecast.windspeedkph"))
      .select('datetimeiso, 'temp_diff, 'pressure_diff, 'humidity_diff, 'wind_diff)

    DFJoined.show()

    DFJoined.write.jdbc(Properties.urlPG, Properties.tablePGDiff, Properties.propertiesPG)
  }
}
