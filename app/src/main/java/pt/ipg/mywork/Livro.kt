package pt.ipg.mywork

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.Calendar

data class Livro(
    var titulo: String,
    var categoria: Categorias,
    var isbn: String? = null,
    var dataPublicacao: Calendar? = null,
    var id: Long? = -1

){

    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaLivros.CAMPO_DATA_PUB, dataPublicacao?.timeInMillis)
        valores.put(TabelaLivros.CAMPO_TITULO, titulo)
        valores.put(TabelaLivros.CAMPO_ISBN, isbn)
        valores.put(TabelaLivros.CAMPO_FK_CATEGORIA, categoria.id)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor) : Livro {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posTitulo = cursor.getColumnIndex(TabelaLivros.CAMPO_TITULO)
            val posISBN = cursor.getColumnIndex(TabelaLivros.CAMPO_ISBN)
            val posDataPub = cursor.getColumnIndex(TabelaLivros.CAMPO_DATA_PUB)
            val posCategoriaFK = cursor.getColumnIndex(TabelaLivros.CAMPO_FK_CATEGORIA)
            val posDescCateg = cursor.getColumnIndex(TabelaLivros.CAMPO_DESC_CATEGORIA)

            val id = cursor.getLong(posId)
            val titulo = cursor.getString(posTitulo)
            val isbn = cursor.getString(posISBN)

            var dataPub: Calendar?

            if (cursor.isNull(posDataPub)) {
                dataPub = null
            } else {
                dataPub = Calendar.getInstance()
                dataPub.timeInMillis = cursor.getLong(posDataPub)
            }

            val categoriaId = cursor.getLong(posCategoriaFK)
            val desricaoCategoria = cursor.getString(posDescCateg)

            return Livro(titulo, Categorias(desricaoCategoria, categoriaId), isbn, dataPub, id)
        }
    }
}