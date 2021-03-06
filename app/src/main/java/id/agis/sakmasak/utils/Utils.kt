package id.agis.sakmasak.utils

import android.content.Context
import android.widget.Toast

fun String.takeMainContent(): String{
    var mainContent = this.replace("Resep ", "")
    val indexSeparator = mainContent.indexOf(',')
    if(indexSeparator > 0){
        mainContent = mainContent.take(indexSeparator)
    }
    return mainContent
}

fun Context.toast(content: String?){
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}