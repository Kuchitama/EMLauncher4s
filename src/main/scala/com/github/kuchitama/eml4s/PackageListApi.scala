package com.github.kuchitama.eml4s

import skinny.http._
import java.util.Date
import org.json4s._
import org.json4s.jackson.JsonMethods._
import java.text.SimpleDateFormat
import com.github.kuchitama.eml4s.models.EMLPackage

/**
 * PackageListAPI
 * @param host
 * @param apiKey
 * @param isSecure
 */
class PackageListApi(val host: String, apiKey: String, val isSecure: Boolean = true) extends {
  protected val endPoint: String = "/api/package_list"
} with EMLApi {
  /**
   * EMLに登録されているPacageのリスト
   */
  lazy val list = get(apiKey)

  implicit val formats = new DefaultFormats {
    override protected def dateFormatter: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  }
  protected def get(apiKey: String): List[EMLPackage] = {
    val response = HTTP.get(apiUrl, "api_key" -> apiKey)
    val resultJson = response.asString
    val packageList = parse(resultJson).extract[List[EMLPackageParser]]
    packageList.map(_.toPackage)
  }

}

/**
 * PackageListのJsonをパースするためのクラス
 */
case class EMLPackageParser(package_url: String,
    application_url: String,
    id: String,
    platform: String,
    title: String,
    description: String,
    ios_identifier: Option[String],
    original_file_name: String,
    file_size: String,
    created: Date,
    tags: List[String],
    install_count: Int) {

  def toPackage: EMLPackage = {
    EMLPackage(package_url,
      application_url,
      id.toLong,
      platform,
      title,
      description,
      ios_identifier,
      original_file_name,
      file_size.toLong,
      created,
      tags,
      install_count: Int)
  }
}

object EMLPackageParser {
  def apply(emlPackage: EMLPackage): EMLPackageParser = {
    EMLPackageParser(emlPackage.packageUrl,
      emlPackage.applicationUrl,
      emlPackage.id.toString,
      emlPackage.platform,
      emlPackage.title,
      emlPackage.description,
      emlPackage.iosIdentifier,
      emlPackage.originalFileName,
      emlPackage.fileSize.toString,
      emlPackage.created,
      emlPackage.tags,
      emlPackage.installCount)
  }
}

