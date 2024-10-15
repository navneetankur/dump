
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.time.Duration


fun main() {
    val url = "https://developer.android.com/training/basics/firstapp"
    val relevantPrefix = "developer.android.com"
    val excludeEndingList = listOf<String>() //urls ending with these will be ignored
    val excludeContainingList = listOf("developer.android.com/courses","training/wearables","developer.android.com/topic","training/tv","training/cars")
    val baseUrl = "https://developer.android.com/"
    val title = "Android Guide"
    val outputLocation = "/home/navn/data/android-guide"
    println("Hello World")
    File(outputLocation).mkdirs()
    val chromeDriver = getNewChromeDriver()
    chromeDriver.get(url)
    val nav = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.tagName("devsite-book-nav")))
    val navHtml = nav.getAttribute("innerHTML")
    val jsoupDoc = Jsoup.parse(navHtml)
    jsoupDoc.setBaseUri(baseUrl)
    val aList = jsoupDoc.getElementsByTag("a")
    val indexBuilder = StringBuilder()
    indexBuilder.append("<!DOCTYPE html>\n<html><head><title>$title</title></head><body>\n<h1>Table of Contents</h1>\n<ol>\n")
//    val imageSet = ConcurrentHashMap<String,Int>().keySet(5)
    val imageSet = mutableSetOf<String>()
    val baseRemover = "^http.?:\\/\\/.*?\\/".toRegex()
    runBlocking {
        for(a in aList) {
            val href = a.absUrl("href")
            val partialHref = a.attr("href")
            if(partialHref.count { '/' == it } < 2) continue;
            if (relevantPrefix !in href) continue;
            if (excludeEndingList.any { href.endsWith(it) || href.endsWith("$it/") }) continue
            if (excludeContainingList.any { it in href }) continue
            val filename = a.text().safeFilename()
            indexBuilder.append("<li><a href='$filename.html'>$filename</a></li>\n")
            println(href)
            chromeDriver.get(href)
            //#maincontent > div > table > caption
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("devsite-content")))
            val contentHtml = (content.getAttribute("innerHTML"))
            val jsoupContent = Jsoup.parse(contentHtml)
            jsoupContent.setBaseUri(baseUrl)
            val imgList = jsoupContent.getElementsByTag("img")
            for (img in imgList) {
                val srcAbs = img.absUrl("src")
                val srcRel = baseRemover.replace(srcAbs,"");
                val (imgdir, imgname) = getImageLocation(srcRel)
                if(!imageSet.contains("$imgdir/$imgname")) {
                    imageSet.add("$imgdir/$imgname")
                    launch(Dispatchers.IO) {
                        println("downloading $imgdir/$imgname")
                        File("$outputLocation/$imgdir").mkdirs()
                        try {
                            URL(img.absUrl("src")).openStream().use { `in` ->
                                Files.copy(
                                    `in`,
                                    Paths.get("$outputLocation/$imgdir/$imgname"),
                                    StandardCopyOption.REPLACE_EXISTING
                                )
                                println("done $outputLocation/$imgdir/$imgname")
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }//end launch
                }
                println("$imgdir/$imgname")
                img.attr("src", "$imgdir/$imgname")
            }//end for imgs

            File("$outputLocation/$filename.html").writeText(jsoupContent.outerHtml())
            println("$href , $filename")
        }//end for href
    } //end runblocking
    indexBuilder.append("</ol></body></html>")
    File("$outputLocation/index.html").writeText(indexBuilder.toString())
//    println(html_str)
}

fun getImageLocation(src: String): Pair<String, String> {
    val slashIndex = src.lastIndexOf('/')
    return if(slashIndex == -1) Pair("",src)
    else {
        val imgdir = src.substring(0,slashIndex).let {
            if (it.startsWith("/"))
                it.substring(1)
            else
                it
        }
        val imgname = src.substring(slashIndex+1).safeFilename()
        Pair(imgdir,imgname)
    }
}
val filenameRegex="[^a-zA-Z0-9._-]".toRegex()
fun String.safeFilename(): String {
    return filenameRegex.replace(this,"_");
}
