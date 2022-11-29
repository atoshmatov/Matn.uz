package uz.uicgroup.presentation.screens.about

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uicgroup.R
import uz.uicgroup.data.local.SharedPref
import uz.uicgroup.databinding.ScreenAboutBinding
import uz.uicgroup.presentation.screens.about.viewModel.AboutViewModel
import uz.uicgroup.presentation.screens.about.viewModel.impl.AboutViewModelImpl
import uz.uicgroup.utils.extension.myApply

@AndroidEntryPoint
class AboutScreen : Fragment(R.layout.screen_about) {

    // TODO: move to data
    private var sharedPref: SharedPref? = null

    private val binding by viewBinding(ScreenAboutBinding::bind)

    private val viewModel: AboutViewModel by viewModels<AboutViewModelImpl>()

    //observer Object
    private val backScreenObserver = Observer<Unit> {
        findNavController().navigateUp()
    }
    private val nightModeObserver = Observer<Boolean> { isLight ->
        setImage(isLight)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backScreenLiveData.observe(this@AboutScreen, backScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPref = SharedPref(requireContext())
        setImage(sharedPref?.theme ?: false)

        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        //observer
        observeNightMode()
    }

    // TODO: Use ContextCompat API
    @SuppressLint("ResourceAsColor")
    private fun setImage(isLight: Boolean) {
        binding.myApply {
            if (isLight) {
                lightMode.setCardBackgroundColor(R.color.mode_bg_color)
                darkMode.setCardBackgroundColor(Color.TRANSPARENT)
                iconDark.setImageResource(R.drawable.ic_unchecked_image)
                iconLight.setImageResource(R.drawable.ic_checked_image)
            } else {
                darkMode.setCardBackgroundColor(R.color.mode_bg_color)
                lightMode.setCardBackgroundColor(Color.TRANSPARENT)
                iconLight.setImageResource(R.drawable.ic_unchecked_image)
                iconDark.setImageResource(R.drawable.ic_checked_image)
            }
        }
    }

    private fun setupClickListeners() = binding.myApply {
        itemInfo1.setOnClickListener {
            findNavController().navigate(R.id.action_aboutScreen_to_appInfoScreen)
        }

        itemInfo2.setOnClickListener {
            findNavController().navigate(R.id.action_aboutScreen_to_policyScreen)
        }

        itemInfo3.setOnClickListener {
            findNavController().navigate(R.id.action_aboutScreen_to_userPolicyScreen)
        }

        backBtn.setOnClickListener {
            viewModel.backScreen()
        }

        lightMode.setOnClickListener {
            viewModel.setTheme(true)
        }

        darkMode.setOnClickListener {
            viewModel.setTheme(false)
        }

        telegramImage.setOnClickListener {
            setUir("https://t.me/matnuzofficial", "org.telegram.messenger")
        }

        instagramImage.setOnClickListener {
            setUir("https://www.instagram.com/matn.uz/?hl=en", "com.instagram.android")
        }

        facebookImage.setOnClickListener {
            setUir("https://www.facebook.com/matn.uz/", "com.facebook.katana")
        }

        twitterImage.setOnClickListener {
            setUir("https://twitter.com/matnuz?lang=en", "com.twitter.android")
        }

        siteLink.setOnClickListener {
            setUir("https://matn.uz/", "")
        }
    }

    // TODO: Remove package name, uri is enough
    private fun setUir(uri: String, pack: String) {
        val installed = checkAppInstall(pack);
        val open = Intent(Intent.ACTION_VIEW)
        open.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        open.data = Uri.parse(uri)
        requireContext().startActivity(open)
    }

    private fun checkAppInstall(uri: String): Boolean {
        val pm: PackageManager = requireContext().packageManager
        return try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    private fun observeNightMode() {
        viewModel.nightModeLiveData.observe(viewLifecycleOwner, nightModeObserver)
    }
}