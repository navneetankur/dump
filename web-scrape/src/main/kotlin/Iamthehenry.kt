import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    //read manually saved html
    val src = File("/home/navn/temp.html").readText()
    val jsoupDoc = Jsoup.parse(src)
    val outputLocation = "/home/navn/data/iamthehenry/htmls/"
    val outputFile = File(outputLocation)
    outputFile.mkdirs()
    //set base url
    jsoupDoc.setBaseUri("https://ianthehenry.com/")
    //get all useful links
    val div = jsoupDoc.select("aside.series-rider").get(1)
    val al = div.getElementsByTag("a")
    val links = mutableListOf<String>()
    for(a in al) {
        if(a.attr("href").contains("how-to-learn-nix")) {
            links.add(a.absUrl("href"))
        }
    }
    links.removeAt(0)
    println(links)
    //download the links using chromedriver
    val chromeDriver = getNewChromeDriver()
    for(link in links) {
        chromeDriver.get(link)
        //#maincontent > div > table > caption
        val fileName = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".post-title > a:nth-child(1)"))).text
        val src = chromeDriver.pageSource
        File(outputLocation + fileName+".html").writeText(src)
        println(link)
    }
//    println(trs)
}

