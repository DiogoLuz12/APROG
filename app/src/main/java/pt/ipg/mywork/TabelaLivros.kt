package pt.ipg.mywork

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns


class TabelaLivros(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA){
    companion object{
        const val NOME_TABELA = "livros"
    }

    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, titulo TEXT NOT NULL, isbn TEXT, id_categoria INTEGER NOT NULL UNIQUE), FOREIGN KEY(id_categoria) REFERENCES ${TabelaCategorias.NOME_TABELA}(${BaseColumns._ID})")
    }


}
