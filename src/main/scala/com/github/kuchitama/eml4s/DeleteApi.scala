package com.github.kuchitama.eml4s

import com.github.kuchitama.eml4s.models.{ EMLPackageParser, EMLPackage }
import skinny.http._

/**
 * DeleteApi
 */
class DeleteApi(val host: String, apiKey: String, val isSecure: Boolean = true) extends {
  protected val endPoint: String = "/api/delete"
} with EMLApi {
  def delete(emlPackage: EMLPackage): EMLPackage = {
    val response = HTTP.get(apiUrl, "api_key" -> apiKey, "id" -> emlPackage.id.toString)

    if (response.status >= 300) {
      throw new HTTPException(Some(s"Could not delete package[id=${emlPackage.id}]"), response)
    }

    EMLPackageParser.parse(response.asString)

  }
}
