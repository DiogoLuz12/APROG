package pt.ipg.mywork

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

abstract class TabelaBD(val db: SQLiteDatabase, val nome: String) {
    abstract fun cria()

    fun insere(valores: ContentValues) =
        db.insert(nome, null, valores)


    fun altera(valores: ContentValues, where: String, argwhere: Array<String>) =
        db.update(nome, valores, where, argwhere )

    fun consulta(
        colunas: Array<String>,
        selecao: String?,
        argsSelecao: Array<String>?,
        groupby: String?,
        having: String?,
        orderby: String?
    ) : Cursor = db.query(nome, colunas, selecao, argsSelecao, groupby, having, orderby )


    fun elimina(where: String, argswhere: Array<String>) =
        db.delete(nome, where, argswhere,)

    companion object {
        const val CHAVE_TABELA = "${ BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, des TEXT NOT NULL UNIQUE"
    }


}

