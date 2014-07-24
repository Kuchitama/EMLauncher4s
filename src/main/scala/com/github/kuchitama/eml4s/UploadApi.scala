package com.github.kuchitama.eml4s

import com.github.kuchitama.eml4s.models.{ EMLPackageParser, EMLPackage }
import skinny.http._
import java.io.{ FileNotFoundException, File }

/**
 * UploadApi
 */
class UploadApi(val host: String, apiKey: String, val isSecure: Boolean = true) extends {
  protected val endPoint: String = "/api/upload"
} with EMLApi {
  def upload(filePath: String, title: String, description: String, tags: List[String], notify: Boolean = false): EMLPackage = {

    val notifyStr = {
      val str = notify.toString
      val head = str.charAt(0).toUpper

      head + str.drop(1)
    }
    val file: File = new File(filePath)
    if (file.exists() == false) {
      throw new FileNotFoundException(s"file is not exist at '${filePath}'")
    }

    val formData = List(
      FormData("file", FileInput(file, "multipart/form-data")),
      FormData("api_key", TextInput(apiKey)),
      FormData("title", TextInput(title)),
      FormData("description", TextInput(description)),
      FormData("tags", TextInput(tags.mkString(","))),
      FormData("notify", TextInput(notifyStr))
    )
    val request = Request(apiUrl).multipartFormData(formData).formParams()
    val response = HTTP.post(request)

    if (response.status >= 300) {
      throw new HTTPException(Some(s"status:${response.status}, Could not upload file[${filePath}]\n${response.asString}"), response)
    }

    EMLPackageParser.parse(response.asString)

  }
}
