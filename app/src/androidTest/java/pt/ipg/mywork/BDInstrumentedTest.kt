package pt.ipg.mywork

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

    fun consegueInserirVenda(){
        val openHelper = BdLivrosOpenHelper(getAppContext())
        val bd = openHelper.writableDatabase

        val categoria = Categorias("Drama")
        val id = TabelaCategorias(bd).insere(categoria.toContentValues())
        assertNotEquals(-1, id)
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