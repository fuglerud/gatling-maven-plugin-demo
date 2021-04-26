package sfmgui

object Headers_SfmGui {

  val headers_0 = Map("Origin" -> "https://session.qa.forskrivning.no")
  //val headers_0 = Map("Origin" -> "https://session.staging.sfm.cloud")

  val headers_1 = Map("Authorization" -> "${Code}",
    "Content-type" ->"application/json")

  val headers_2 = Map("__Host-sfm_session" -> "${Cookie}",
    "Content-type" ->"application/json")


}