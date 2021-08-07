package io.realworld.android.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.realworld.android.AuthViewModel
import io.realworld.android.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val authViewModel by activityViewModels<AuthViewModel>()
    private var _binding: FragmentSettingsBinding? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user?.observe( {lifecycle}) {

            _binding?.apply {
                bioEditText.setText(it?.bio?:"")
                usernameEditText.setText(it?.username?:"")
                emailEditText.setText(it?.email?:"")
                imageEditText.setText(it?.image?:"")
            }

            _binding?.apply {
                updateSettingsButton.setOnClickListener {
                    authViewModel.update(
                        image = imageEditText.text.toString(),
                        username = usernameEditText.text.toString().takeIf { it.isNotBlank() },
                        bio = bioEditText.text.toString(),
                        email = emailEditText.text.toString().takeIf { it.isNotBlank() },
                        password = passwordEditText.text.toString().takeIf { it.isNotBlank() }
                    )
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}