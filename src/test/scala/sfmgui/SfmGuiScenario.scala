package sfmgui

import io.gatling.core.Predef._
import io.gatling.core.structure._
import sfmgui.Grupper_SfmGui._

object SfmGuiScenario {

  def function1: ChainBuilder = {exec(grp_login)}
  def function2: ChainBuilder = {exec(grp_oppdater)}
  def function3: ChainBuilder = {exec(grp_endreforskrivning)}
  def function4: ChainBuilder = {exec(grp_kritiskereaksjoner)}
  def function5: ChainBuilder = {exec(grp_nyttlegemiddel)}
  def function6: ChainBuilder = {exec(grp_lagreresept)}
  def function7: ChainBuilder = {exec(grp_sendlegemiddel)}
  def function8: ChainBuilder = {exec(grp_settpinkode)}
  def function9: ChainBuilder = {exec(grp_korrespondanselogg)}


  /* val scn_gui: ScenarioBuilder = scenario("random").repeat(1) {
     randomSwitch(
       12.5 -> function1,
       12.5 -> function2,
       12.5 -> function3,
       12.5 -> function4,
       12.5 -> function5,
       12.5 -> function6,
       12.5 -> function7,
       12.5 -> function8
     )}*/

  val scn_gui: ScenarioBuilder = scenario("random").repeat(1) {
    randomSwitch(
    100.0 -> function9)}

  val SCN_SFMGUI = scenario("SFM GUI")
    .exec(scn_gui)

}
