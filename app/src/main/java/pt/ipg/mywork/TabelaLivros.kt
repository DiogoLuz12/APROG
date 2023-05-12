package pt.ipg.mywork

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns


class TabelaLivros(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA){
    companion object{
        const val NOME_TABELA = "livros"
        const val CAMPO_TITULO = "titulo"
        const val CAMPO_ISBN = "isbn"
        const val CAMPO_FK_CATEGORIA ="id_categorias"
    }

    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_TITULO TEXT NOT NULL, $CAMPO_ISBN TEXT, $CAMPO_FK_CATEGORIA INTEGER NOT NULL UNIQUE,    FOREIGN KEY($CAMPO_FK_CATEGORIA) REFERENCES ${TabelaCategorias.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }


}
