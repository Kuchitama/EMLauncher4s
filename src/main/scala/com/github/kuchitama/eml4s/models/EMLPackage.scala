package com.github.kuchitama.eml4s.models;

import java.util.Date

case class EMLPackage(packageUrl: String,
  applicationUrl: String,
  id: Long,
  platform: String,
  title: String,
  description: String,
  iosIdentifier: Option[String],
  originalFileName: String,
  fileSize: Long,
  created: Date,
  tags: List[String],
  installCount: Int)