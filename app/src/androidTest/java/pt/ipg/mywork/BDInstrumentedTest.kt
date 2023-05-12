package pt.ipg.mywork

import android.database.sqlite.SQLiteDatabase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BDInstrumentedTest {

    fun consegueInserirLivros(){
        val bd = getWritableDatabase()

        val categoria = Categorias("humor")
        InsereCategoria(bd, categoria)

        val livro1 = Livro("O Lixo na Minha Cabeça",categoria.id)
        InsereLivro(bd, livro1)

        val livro2 =Livro("Novíssimas crónicas da boca do inferno",categoria.id," 9789896711788")
        InsereLivro(bd, livro2)
    }

    fun consegueInserirVenda(){
        val bd = getWritableDatabase()

        val categoria = Categorias("Drama")
        InsereCategoria(bd, categoria)
    }

    private fun InsereCategoria(
        bd: SQLiteDatabase,
        categoria: Categorias
    ) {
        TabelaCategorias(bd).insere(categoria.toContentValues())
        assertNotEquals(-1, categoria.id)
    }

    private fun InsereLivro(bd: SQLiteDatabase, livro: Livro) {
        TabelaLivros(bd).insere(livro.toContentValues())
        assertNotEquals(-1, livro.id)
    }





    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BdLivrosOpenHelper(getAppContext())
        val bd = openHelper.writableDatabase
        return bd
    }

    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBD(){
        getAppContext().deleteDatabase(BdLivrosOpenHelper.NOME_BASE_DADOS)
    }


    @Test

    fun ConsegueAbrirBD() {

        val openHelper = BdLivrosOpenHelper(getAppContext())
        val bd = openHelper.readableDatabase
        assert(bd.isOpen)

        // Context of the app under test.

    }


}