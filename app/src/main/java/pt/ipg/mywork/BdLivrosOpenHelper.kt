package pt.ipg.mywork

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//CONSTANTES
private const val  NOME_BD = "Livros.db"
private const val VERSAO_BD = 1

class BdLivrosOpenHelper(
    context: Context?,
) : SQLiteOpenHelper(context, NOME_BD, null, VERSAO_BD) {
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}