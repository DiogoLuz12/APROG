package pt.ipg.mywork

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.Calendar

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BDInstrumentedTest {


    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BdLivrosOpenHelper(getAppContext())
        return openHelper.writableDatabase
    }

    private fun getAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBD(){
        getAppContext().deleteDatabase(BdLivrosOpenHelper.NOME_BASE_DADOS)
    }


    @Test

    fun consegueAbrirBD() {

        val openHelper = BdLivrosOpenHelper(getAppContext())
        val bd = openHelper.readableDatabase
        assert(bd.isOpen)

        // Context of the app under test.

    }
    @Test
    fun consegueInserirVenda(){
        val bd = getWritableDatabase()

        val categoria = Categorias("Drama")
        insereCategoria(bd, categoria)
    }


    private fun insereCategoria(
        bd: SQLiteDatabase,
        categoria: Categorias
    ) {
        categoria.id = TabelaCategorias(bd).insere(categoria.toContentValues())
        assertNotEquals(0, categoria.id)
    }

    private fun insereLivro(bd: SQLiteDatabase, livro: Livro) {
        TabelaLivros(bd).insere(livro.toContentValues())
        assertNotEquals(-1, livro.id)
    }

    @Test
    fun consegueInserirLivros(){
        val bd = getWritableDatabase()

        val categoria = Categorias("humor")
        insereCategoria(bd, categoria)

        val livro1 = Livro("O Lixo na Minha Cabeça",categoria.id)
        insereLivro(bd, livro1)

        val livro2 =Livro("Novíssimas crónicas da boca do inferno",categoria.id," 9789896711788")
        insereLivro(bd, livro2)
    }

    @Test
    fun consegueLerCategorias(){
        val bd = getWritableDatabase()

        val categRomance = Categorias("romance")
        insereCategoria(bd, categRomance)

        val categFic = Categorias("ficçao cientifica")
        insereCategoria(bd, categFic)

        val tabelaCategorias = TabelaCategorias(bd)

        val cursor = tabelaCategorias.consulta(
            TabelaCategorias.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(categFic.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())
        val categoriaBD = Categorias.fromcursor(cursor)

        assertEquals(categFic, categoriaBD)

        val cursorTodasCategorias = tabelaCategorias.consulta(
            TabelaCategorias.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaCategorias.CAMPO_DESCRICAO
        )
        assert(cursorTodasCategorias.count > 1)
    }
    @Test
    fun consegueLerLivros() {
        val bd = getWritableDatabase()

        val categoria = Categorias("Contos")
        insereCategoria(bd, categoria)

        val livro1 = Livro("Todos os Contos", categoria.id)
        insereLivro(bd, livro1)

        val dataPub = Calendar.getInstance()
        dataPub.set(2016, 4, 1)

        val livro2 = Livro("Contos de Grimm", categoria.id, "978-1473683556", dataPub)
        insereLivro(bd, livro2)

        val tabelaLivros = TabelaLivros(bd)

        val cursor = tabelaLivros.consulta(
            TabelaLivros.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(livro1.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val livroBD = Livro.fromCursor(cursor)

        assertEquals(livro1, livroBD)

        val cursorTodosLivros = tabelaLivros.consulta(
            TabelaLivros.CAMPOS,
            null, null, null, null,
            TabelaLivros.CAMPO_TITULO
        )

        assert(cursorTodosLivros.count > 1)
    }

    @Test
    fun consegueAlterarCategorias(){
        val bd = getWritableDatabase()

        val categRomance = Categorias("Romance")
        insereCategoria(bd, categRomance)

        categRomance.descricao = "Poesia"
        val registsoAlterados = TabelaCategorias(bd).altera(
            categRomance.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(categRomance.id.toString()),
        )
        assertNotEquals(1, registsoAlterados)


    }

    @Test
    fun consegueAlterarLivros(){
        val bd = getWritableDatabase()

        val categoriaJuvenil = Categorias("Literatura Infato-juvenil")
        insereCategoria(bd, categoriaJuvenil)

        val categoriaNacional = Categorias("Literatura Nacional")
        insereCategoria(bd, categoriaNacional)

        val livro1 = Livro("...", categoriaNacional.id)
        insereLivro(bd, livro1)

        val novaDataPub = Calendar.getInstance()

        novaDataPub.set(1999,1,1)
        livro1.idCategoria = categoriaJuvenil.id
        livro1.titulo = "O meu Pé de Laranja Lima"
        livro1.dataPublicacao = novaDataPub
        livro1.isbn = "8506043662"

        val registosAlterados = TabelaLivros(bd).altera(livro1.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(livro1.id.toString()),
        )

        assertNotEquals(1,registosAlterados)
    }

    @Test
    fun deleteCategorias(){
        val bd = getWritableDatabase()

        val categRomance = Categorias("Romance")
        insereCategoria(bd, categRomance)

        categRomance.descricao = "Poesia"
        val registsoEliminados = TabelaCategorias(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(categRomance.id.toString()),
        )
        assertNotEquals(1, registsoEliminados)

    }

    @Test
    fun deleteLivros(){
        val bd = getWritableDatabase()

        val categoria = Categorias("Programaçao")
        insereCategoria(bd, categoria)


        val livro1 = Livro("...", categoria.id)
        insereLivro(bd, livro1)

        val novaDataPub = Calendar.getInstance()

        novaDataPub.set(1999,1,1)
        livro1.idCategoria = categoria.id
        livro1.titulo = "O meu Pé de Laranja Lima"
        livro1.dataPublicacao = novaDataPub
        livro1.isbn = "8506043662"

        val registosEliminados = TabelaLivros(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(livro1.id.toString()),
        )

        assertNotEquals(1,registosEliminados)
    }

}