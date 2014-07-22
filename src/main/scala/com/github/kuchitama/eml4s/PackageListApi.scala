package com.github.kuchitama.eml4s

import skinny.http._
import com.github.kuchitama.eml4s.models.{ EMLPackageParser, EMLPackage }

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

  protected def get(apiKey: String): List[EMLPackage] = {
    val response = HTTP.get(apiUrl, "api_key" -> apiKey)
    val resultJson = response.asString
    EMLPackageParser.parseAsList(resultJson)
  }

}

