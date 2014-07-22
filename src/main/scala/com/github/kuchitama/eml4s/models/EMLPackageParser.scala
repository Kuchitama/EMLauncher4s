package com.github.kuchitama.eml4s.models

import java.util.Date
import java.text.SimpleDateFormat
import org.json4s._

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
  implicit val formats = new DefaultFormats {
    override protected def dateFormatter: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  }

  def parse(json: String): EMLPackage = {
    org.json4s.jackson.JsonMethods.parse(json).extract[EMLPackageParser].toPackage
  }

  def parseAsList(json: String): List[EMLPackage] = {
    org.json4s.jackson.JsonMethods.parse(json).extract[List[EMLPackageParser]] map (_.toPackage)
  }
}
