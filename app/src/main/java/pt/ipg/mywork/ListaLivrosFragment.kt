package pt.ipg.livros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.mywork.R
import pt.ipg.mywork.databinding.FragmentListaLivrosBinding



/**
 * A simple [Fragment] subclass.
 * Use the [ListaLivrosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaLivrosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentListaLivrosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_livros, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterLivros = AdapterLivros()
        binding.recyclerViewLivros.adapter = adapterLivros
        binding.recyclerViewLivros.layoutManager = LinearLayoutManager(requireContext())
    }
    companion object {

                }
}

