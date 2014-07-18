package com.github.kuchitama.eml4s

import dispatch._
import dispatch.Defaults._

/**
 * Created by kunihira on 2014/07/18.
 */
object PackageListApi {
  def get(host: String, apiKey: String) = {
    val request = url(s"https://${host}/api/package_list").addQueryParameter("api_key", apiKey)
    val resultJson = Http(request OK as.String)
    println(resultJson())
  }
}
