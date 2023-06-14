package pt.ipg.livros

import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.mywork.Livro
import pt.ipg.mywork.MainActivity
import pt.ipg.mywork.R
import pt.ipg.mywork.databinding.FragmentEliminarLivroBinding

class EliminarLivroFragment : Fragment() {
    private lateinit var livro: Livro
    private var _binding: FragmentEliminarLivroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEliminarLivroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        livro = EliminarLivroFragmentArgs.fromBundle(requireArguments()).livro

        binding.textViewTitulo.text = livro.titulo
        binding.textViewISBN.text = livro.isbn
        binding.textViewCategoria.text = livro.categoria.descricao
        if (livro.dataPublicacao != null) {
            binding.textViewDataPub.text = DateFormat.format("yyyy-MM-dd", livro.dataPublicacao)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
                eliminar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaLivros()
                true
            }
            else -> false
        }
    }

    private fun voltaListaLivros() {
        findNavController().navigate(R.id.action_eliminarLivroFragment_to_ListaLivrosFragment)
    }

    private fun eliminar() {
        val enderecoLivro = Uri.withAppendedPath(LivrosContentProvider.ENDERECO_LIVROS, livro.id.toString())
        val numLivrosEliminados = requireActivity().contentResolver.delete(enderecoLivro, null, null)

        if (numLivrosEliminados == 1) {
            Toast.makeText(requireContext(), getString(R.string.livro_eliminado_com_sucesso), Toast.LENGTH_LONG).show()
            voltaListaLivros()
        } else {
            Snackbar.make(binding.textViewTitulo, getString(R.string.erro_eliminar_livro), Snackbar.LENGTH_INDEFINITE)
        }
    }
}