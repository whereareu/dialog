package com.dialogapp.dialog.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.dialogapp.dialog.GlideApp
import com.dialogapp.dialog.R
import com.dialogapp.dialog.auth.SessionManager
import com.dialogapp.dialog.databinding.FragmentMoreBinding
import com.dialogapp.dialog.di.Injector
import com.dialogapp.dialog.ui.util.autoCleared

class MoreFragment : Fragment() {

    lateinit var sessionManager: SessionManager

    private var binding by autoCleared<FragmentMoreBinding>()

    override fun onAttach(context: Context?) {
        sessionManager = Injector.get().sessionManager()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textMoreUsername.text = String.format(view.resources.getString(R.string.post_username),
                sessionManager.user?.username)
        GlideApp.with(this)
                .load(sessionManager.user?.gravatarUrl)
                .into(binding.imageAvatar)

        binding.buttonProfile.setOnClickListener {
            val action = MoreFragmentDirections.actionMoreDestToProfileDest(sessionManager.user?.username
                    ?: "")
            findNavController().navigate(action)
        }

        binding.buttonLogout.setOnClickListener {
            MaterialDialog(this.requireContext())
                    .title(R.string.confirm)
                    .message(R.string.dialog_logout_message).show {
                        positiveButton(R.string.logout) { dialog ->
                            dialog.dismiss()
                            sessionManager.logout()
                            val mainNavController = activity?.findNavController(R.id.nav_host_main)
                            mainNavController?.navigate(R.id.action_home_dest_to_login_dest)
                        }
                        negativeButton(android.R.string.cancel)
                    }
        }

        binding.fabNewPost.setOnClickListener {
            val navOptions = NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_bottom)
                    .setExitAnim(R.anim.nav_default_exit_anim)
                    .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                    .setPopExitAnim(R.anim.slide_out_bottom)
                    .build()
            val mainNavController = activity?.findNavController(R.id.nav_host_main)
            mainNavController?.navigate(R.id.new_post_dest, bundleOf("isReply" to false), navOptions)
        }
    }
}