package tn.org.myresto.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber
import tn.org.myresto.databinding.RestaurantDetailScreenBinding
import tn.org.myresto.ui.main.MainViewModel
import tn.org.myresto.ui.main.MainViewModelFactory

class RestaurantDetailDialog : BottomSheetDialogFragment() {

    private lateinit var binding: RestaurantDetailScreenBinding

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(context = requireContext())
    }

    private val args: RestaurantDetailDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RestaurantDetailScreenBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.e("${args.restaurant}")
        viewModel.detail.observe(viewLifecycleOwner) {
            binding.restaurant = it
        }
        viewModel.loadDetails(args.restaurant!!)
    }
}