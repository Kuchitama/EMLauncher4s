package com.github.kuchitama.eml4s

/**
 * Created by k2 on 2014/07/22.
 */
trait EMLApi {
  val isSecure: Boolean
  val host: String

  protected val scheme = if (isSecure) "https" else "http"
  protected val endPoint: String
  protected val apiUrl = s"${scheme}://${host}${safePath(endPoint)}"

  private def safePath(str: String): String = if (str.startsWith("/")) str else s"/${str}"

}
