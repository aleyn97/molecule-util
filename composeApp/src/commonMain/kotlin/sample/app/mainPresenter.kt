package sample.app

import androidx.compose.runtime.Composable

/**
 * @author : Aleyn
 * @date : 2025/11/11 16:28
 */


data class MainModel(
    val name: String,
)

@Composable
fun mainPresenter(

): MainModel {
    return MainModel(name = "")
}