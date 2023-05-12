package pt.ipg.mywork

import android.content.ContentValues

class Categorias (
    var descricao: String,
    var id: Long = -1
){
    fun toContentValues(): ContentValues{
        val valores =  ContentValues()

        valores.put(TabelaCategorias.CAMPO_DESCRICAO, descricao)

        return valores
    }
}