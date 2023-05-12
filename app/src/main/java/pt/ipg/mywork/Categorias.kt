package pt.ipg.mywork

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Categorias (
    var descricao: String,
    var id: Long = -1
){
    fun toContentValues(): ContentValues{
        val valores =  ContentValues()

        valores.put(TabelaCategorias.CAMPO_DESCRICAO, descricao)

        return valores
    }

    companion object{

        fun fromcursor(cursor: Cursor): Categorias{
            val posId = cursor.getColumnIndex(BaseColumns._ID)

            val posDescricao = cursor.getColumnIndex(TabelaCategorias.CAMPO_DESCRICAO)

            val id = cursor.getLong(posId)
            val descricao = cursor.getString(posDescricao)

            return Categorias(descricao, id)
        }
    }
}