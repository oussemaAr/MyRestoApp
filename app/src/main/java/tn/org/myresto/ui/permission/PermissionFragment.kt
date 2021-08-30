package tn.org.myresto.ui.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import tn.org.myresto.R
import tn.org.myresto.databinding.PermissionScreenBinding
import tn.org.myresto.utils.isLocationEnable
import tn.org.myresto.utils.isPermissionGranted

private const val PERMISSION_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION

class PermissionFragment : Fragment() {

    private lateinit var binding: PermissionScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PermissionScreenBinding.inflate(
            inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var requestPermissionLauncher =
            requireActivity().registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Timber.i("Grant")
                } else {
                    Timber.i("Denied")
                }
            }

        updateUI()
        binding.enableGps.setOnClickListener {
            requireActivity().startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        binding.grantPermission.setOnClickListener {
            requestPermission(requestPermissionLauncher)
        }
    }

    private fun updateUI() {
        if (!isLocationEnable(requireContext())) {
            binding.enableGps.isVisible = true
        }
        if (!isPermissionGranted(requireContext())) {
            binding.grantPermission.isVisible = true
        }
    }

    override fun onResume() {
        super.onResume()
        if (isLocationEnable(requireContext()) and isPermissionGranted(requireContext())) {
            findNavController().navigate(R.id.action_permissionFragment_to_restaurantMapFragment)
        } else {
            updateUI()
        }
    }


    private fun requestPermission(requestPermissionLauncher: ActivityResultLauncher<String>) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                PERMISSION_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                Snackbar.make(
                    binding.root,
                    getString(R.string.permission_granted),
                    Snackbar.LENGTH_INDEFINITE,
                ).show()
                findNavController().navigate(R.id.action_permissionFragment_to_restaurantMapFragment)
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                PERMISSION_LOCATION
            ) -> {
                Snackbar.make(
                    binding.root,
                    getString(R.string.permission_required),
                    Snackbar.LENGTH_INDEFINITE,
                ).setAction(
                    getString(R.string.grant)
                ) {
                    requestPermissionLauncher.launch(
                        PERMISSION_LOCATION
                    )
                }.show()
            }
            else -> {
                requestPermissionLauncher.launch(
                    PERMISSION_LOCATION
                )
            }
        }
    }

}