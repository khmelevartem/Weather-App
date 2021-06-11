package com.tubetoast.weather.view.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.tubetoast.weather.R
import com.tubetoast.weather.databinding.FragmentListBinding
import com.tubetoast.weather.view.fragments.details.DetailsFragment
import com.tubetoast.weather.viewmodel.MainViewModel


class ListFragment : Fragment() {

    private var isRussian : Boolean = true
    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    private val nav : NavController by lazy{
        findNavController(this)
    }
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    companion object{

        private const val ARG_IS_RUSSIAN = "is_russian"

        fun newInstance(isRussian: Boolean): ListFragment {
            val args = Bundle().apply { this.putBoolean(ARG_IS_RUSSIAN, isRussian) }
            return ListFragment().apply {
                arguments = args

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isRussian = it.getBoolean(ARG_IS_RUSSIAN, true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListAdapter {
            nav.navigate(
                R.id.action_mainFragment_to_detailsFragment,
                Bundle().apply { putParcelable(DetailsFragment.ARG_CITY, it) })
        }
        binding.recyclerView.adapter = adapter

        viewModel.getCities(isRussian).observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}