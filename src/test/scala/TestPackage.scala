import com.github.kuchitama.eml4s.models.EMLPackage
import java.text.SimpleDateFormat

package object TestPackage {

  /**
   * EMLauncher Date format
   */
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  /**
   * Default Test PackageList
   *
   * /doc/api_package_list
   */
  val packageList = List(EMLPackage("http://localhost/emlauncher/package?id=3",
    "http://localhost/emlauncher/app?id=1",
    3,
    "Android",
    "test upload",
    "upload package via upload api",
    None,
    "emlauncher.apk",
    5776313,
    dateFormat.parse("2013-11-29 12:26:19"),
    List(
      "test",
      "upload-api",
      "android"
    ),
    1),
    EMLPackage("http://localhost/emlauncher/package?id=1",
      "http://localhost/emlauncher/app?id=1",
      1,
      "iOS",
      "ipa file test",
      "test package for iPhone",
      Some("com.klab.playground-sandboxes.test6"),
      "emlauncher.ipa",
      4845763,
      dateFormat.parse("2013-11-29 09:03:01"),
      List(
        "test",
        "ios"
      ),
      0))

  val packageListJson: String = """[
                                  |  {
                                  |    "package_url": "http://localhost/emlauncher/package?id=3",
                                  |    "application_url": "http://localhost/emlauncher/app?id=1",
                                  |    "id": "3",
                                  |    "platform": "Android",
                                  |    "title": "test upload",
                                  |    "description": "upload package via upload api",
                                  |    "ios_identifier": "",
                                  |    "original_file_name": "emlauncher.apk",
                                  |    "file_size": "5776313",
                                  |    "created": "2013-11-29 12:26:19",
                                  |    "tags": [
                                  |      "test",
                                  |      "upload-api",
                                  |      "android"
                                  |    ],
                                  |    "install_count": 1
                                  |  },
                                  |  {
                                  |    "package_url": "http://localhost/emlauncher/package?id=1",
                                  |    "application_url": "http://localhost/emlauncher/app?id=1",
                                  |    "id": "1",
                                  |    "platform": "iOS",
                                  |    "title": "ipa file test",
                                  |    "description": "test package for iPhone",
                                  |    "ios_identifier": "com.klab.playground-sandboxes.test6",
                                  |    "original_file_name": "emlauncher.ipa",
                                  |    "file_size": "4845763",
                                  |    "created": "2013-11-29 09:03:01",
                                  |    "tags": [
                                  |      "test",
                                  |      "ios"
                                  |    ],
                                  |    "install_count": 0
                                  |  }
                                  |]""".stripMargin('|')
}
