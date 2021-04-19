package id.agis.sakmasak.utils

fun String.takeMainContent(): String{
    var mainContent = this.replace("Resep ", "")
    val indexSeparator = mainContent.indexOf(',')
    println(this)
    if(indexSeparator > 0){
        mainContent = mainContent.take(indexSeparator)
    }
    println(mainContent)
    return mainContent
}