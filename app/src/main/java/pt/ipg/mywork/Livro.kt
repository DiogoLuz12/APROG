package pt.ipg.mywork

import android.content.ContentValues

data class Livro(
    var titulo: String,
    var idCategoria: Long,
    var isbn: String? = null,
    var id: Long = -1
){

    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaLivros.CAMPO_TITULO, titulo)
        valores.put(TabelaLivros.CAMPO_ISBN, isbn)
        valores.put(TabelaLivros.CAMPO_FK_CATEGORIA, idCategoria)

        return valores
    }
}