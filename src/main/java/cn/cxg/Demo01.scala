package cn.cxg
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api._
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.table.catalog.hive.HiveCatalog
object Demo01 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val settings = EnvironmentSettings.newInstance().inBatchMode().useBlinkPlanner().build()
    val tableEnv = TableEnvironment.create(settings)
    val name            = "myhive"
    val defaultDatabase = "test"
    val hiveConfDir     = "D:\\idea_workspace\\flinknewapi\\src\\main\\resources"
    val hive = new HiveCatalog(name, defaultDatabase, hiveConfDir)
    tableEnv.registerCatalog("myhive", hive)
    tableEnv.useCatalog("myhive")
    tableEnv.getConfig().setSqlDialect(SqlDialect.HIVE)

//    tableEnv.executeSql("create table teacher (id int,name string,age int,sex string) ")
//    tableEnv.executeSql("alter table student set TBLPROPERTIES ('is_generic'='false')")
    tableEnv.executeSql("insert into student(id,name,age,sex) values(1,'zs',32,'nan')")

  }
}