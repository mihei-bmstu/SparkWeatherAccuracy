package system

import java.util.Properties

object Properties {
  val userPG = "airflow"
  val passPG = "airflow"
  val urlPG = "jdbc:postgresql://localhost:5432/weather"
  val tablePGOb = "moscow_ob"
  val tablePGFor = "moscow_for"
  val tablePGDiff = "moscow_diff"

  val propertiesPG = new Properties()
  propertiesPG.setProperty("user", userPG)
  propertiesPG.setProperty("password", passPG)
  propertiesPG.setProperty("driver", "org.postgresql.Driver")
}
