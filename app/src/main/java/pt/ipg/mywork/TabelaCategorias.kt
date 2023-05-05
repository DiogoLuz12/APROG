package pt.ipg.mywork

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns



class TabelaCategorias(db : SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA($CHAVE_TABELA , descricao TEXT NOT NULL  )")
    }

    companion object {
        const val NOME_TABELA = "categorias"
    }
}