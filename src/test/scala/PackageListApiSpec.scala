import com.github.kuchitama.eml4s.PackageListApi
import org.scalatest._
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.mockito.Matchers._
import TestPackage._

class PackageListApiSpec extends FunSpec with MockitoSugar with Matchers {

  describe("PackageListApi") {
    it("get package list") {
      class TestPackageListApi extends PackageListApi("localhost", "apikey") {
        override def get(apiKey: String) = Nil
      }
      val tester = spy(new TestPackageListApi())

      tester.list

      verify(tester).get("apikey")
    }
  }
}
