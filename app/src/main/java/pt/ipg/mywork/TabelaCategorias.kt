package pt.ipg.mywork

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns



class TabelaCategorias(db : SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA($CHAVE_TABELA , $CAMPO_DESCRICAO TEXT NOT NULL  )")
    }

    companion object {

        const val NOME_TABELA = "categorias"
        const val CAMPO_DESCRICAO = "descricao"
        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"

        val CAMPOS = arrayOf(BaseColumns._ID, CAMPO_DESCRICAO )

    }
}