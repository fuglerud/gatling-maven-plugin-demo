package sfmgui

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder

object Grupper_SfmGui{

  val grp_login: ChainBuilder = group("Logg inn") {
    exec(LoginRequest_SfmGui.login)}

  val grp_logout: ChainBuilder = group("Logg av") {
    exec(LogoutRequest_SfmGui.loggav)}

  val grp_oppdater: ChainBuilder = group("Oppdater") {
     exec(LoginRequest_SfmGui.login)
      .exec(OppdaterRequest_SfmGui.oppdater)
      .exec(LogoutRequest_SfmGui.loggav)}

  val grp_endreforskrivning: ChainBuilder = group("Endre Forskrivning") {
    exec(LoginRequest_SfmGui.login)
      .exec(EndreLegemiddelRequest_SfmGui.endrelegemiddel)
      .exec(LogoutRequest_SfmGui.loggav)}

  val grp_kritiskereaksjoner: ChainBuilder = group("Kritiske reaksjoner") {
    exec(LoginRequest_SfmGui.login)
      .exec(KritiskeReaksjonerRequest_SfmGui.kritiskereaksjoner)
      .exec(LogoutRequest_SfmGui.loggav)}

  val grp_nyttlegemiddel: ChainBuilder = group("Nytt legemiddel") {
    exec(LoginRequest_SfmGui.login)
      .exec(NyttLegemiddelRequest_SfmGui.nyttlegemiddel)
      .exec(LogoutRequest_SfmGui.loggav)}

  val grp_lagreresept: ChainBuilder = group("Lagre resept") {
    exec(LoginRequest_SfmGui.login)
      .exec(LagreReseptRequest_SfmGui.lagreresept)
      .exec(LogoutRequest_SfmGui.loggav)}

  val grp_sendlegemiddel: ChainBuilder = group("Sende legemiddel") {
    exec(LoginRequest_SfmGui.login)
      .exec(SendeLegemiddelRequest_SfmGui.send)
      .exec(LogoutRequest_SfmGui.loggav)}

  val grp_settpinkode: ChainBuilder = group("Sett pinkode") {
    exec(LoginRequest_SfmGui.login)
      .exec(SettPinKodeRequest_SfmGui.settpinkode)
      .exec(LogoutRequest_SfmGui.loggav)}

}