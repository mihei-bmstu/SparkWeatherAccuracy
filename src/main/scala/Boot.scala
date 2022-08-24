import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
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

    val DFOb = spark.read
      .jdbc(Properties.urlPG, Properties.tablePGOb, Properties.propertiesPG)

    val DFFor = spark.read
      .jdbc(Properties.urlPG, Properties.tablePGFor, Properties.propertiesPG)

    DFFor.show()
    DFOb.show()
  }
}
