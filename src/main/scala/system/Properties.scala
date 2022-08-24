package system

import org.apache.spark.sql.types._

import java.util.Properties

object Properties {
  val userPG = "airflow"
  val passPG = "airflow"
  val urlPG = "jdbc:postgresql://postgres:5432/weathers"
  val tablePGOb = "moscow_ob"
  val tablePGFor = "moscow_for"
  val tablePGDiff = "moscow_diff"

  val propertiesPG = new Properties()
  propertiesPG.setProperty("user", userPG)
  propertiesPG.setProperty("password", passPG)
  propertiesPG.setProperty("driver", "org.postgresql.Driver")

  val schemaWeather = StructType(
    StructField("DateTime", TimestampType, true) ::
      StructField("TempC", FloatType, true) ::
      StructField("Humidity", FloatType, true) ::
      StructField("PressureMB", FloatType, true) ::
      StructField("WindSpeedKPH", FloatType, true) ::
      StructField("Weather", StringType, true) ::
      Nil
  )
}
