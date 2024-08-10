package com.jianjiao.bx.view.setting

import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.vcore.BlackBoxCore
import com.jianjiao.bx.R
import com.jianjiao.bx.app.AppManager
import com.jianjiao.bx.util.ToastEx.toast
import com.jianjiao.bx.view.gms.GmsManagerActivity
import com.jianjiao.bx.view.xp.XpActivity

class SettingFragment : PreferenceFragmentCompat() {
    private lateinit var xpEnable: SwitchPreferenceCompat
    private lateinit var xpModule: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        xpEnable = findPreference("xp_enable")!!
        xpEnable.isChecked = BlackBoxCore.get().isXPEnable

        xpEnable.setOnPreferenceChangeListener { _, newValue ->
            BlackBoxCore.get().isXPEnable = (newValue == true)
            true
        }
        // xp模块跳转
        xpModule = findPreference("xp_module")!!
        xpModule.setOnPreferenceClickListener {
            val intent = Intent(requireActivity(), XpActivity::class.java)
            requireContext().startActivity(intent)
            true
        }

        initGms()
        invalidHideState{
            val xpHidePreference: Preference = (findPreference("xp_hide")!!)
            val hideXposed = com.jianjiao.bx.app.AppManager.mBlackBoxLoader.hideXposed()
            xpHidePreference.setDefaultValue(hideXposed)
            xpHidePreference
        }

        invalidHideState{
            val rootHidePreference: Preference = (findPreference("root_hide")!!)
            val hideRoot = com.jianjiao.bx.app.AppManager.mBlackBoxLoader.hideRoot()
            rootHidePreference.setDefaultValue(hideRoot)
            rootHidePreference
        }

        invalidHideState {
            val daemonPreference: Preference = (findPreference("daemon_enable")!!)
            val mDaemonEnable = com.jianjiao.bx.app.AppManager.mBlackBoxLoader.daemonEnable()
            daemonPreference.setDefaultValue(mDaemonEnable)
            daemonPreference
        }
    }

    private fun initGms() {
        val gmsManagerPreference: Preference = (findPreference("gms_manager")!!)

        if (BlackBoxCore.get().isSupportGms) {
            gmsManagerPreference.setOnPreferenceClickListener {
                GmsManagerActivity.start(requireContext())
                true
            }
        } else {
            gmsManagerPreference.summary = getString(R.string.no_gms)
            gmsManagerPreference.isEnabled = false
        }
    }

    private fun invalidHideState(block: () -> Preference) {
        val pref = block()
        pref.setOnPreferenceChangeListener { preference, newValue ->
            val tmpHide = (newValue == true)
            when (preference.key) {
                "xp_hide" -> {
                    com.jianjiao.bx.app.AppManager.mBlackBoxLoader.invalidHideXposed(tmpHide)
                }

                "root_hide" -> {
                    com.jianjiao.bx.app.AppManager.mBlackBoxLoader.invalidHideRoot(tmpHide)
                }

                "daemon_enable" -> {
                    com.jianjiao.bx.app.AppManager.mBlackBoxLoader.invalidDaemonEnable(tmpHide)
                }
            }

            toast(R.string.restart_module)
            return@setOnPreferenceChangeListener true
        }
    }
}
