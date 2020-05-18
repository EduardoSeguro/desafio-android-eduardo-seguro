package br.com.ebss.desafio_android_eduardo_seguro.ui.dialog


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import br.com.ebss.desafio_android_eduardo_seguro.R
import kotlinx.android.synthetic.main.loading_dialog.view.*


class LoadingDialog : DialogFragment() {

    private var onCancelRunnable: Runnable? = null
    private val DIALOG_TAG = "DIALOG_TAG"
    private var customLoadingText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.loading_dialog, null)

        val args = arguments
        if (args != null) {
            customLoadingText = args.getString("customLoadingText")
            val tvCustomLoading = layout.tvCustomLoading
            tvCustomLoading.text = customLoadingText
        }
        dialog?.let {
            it.window?.attributes?.windowAnimations = R.style.LoadingDialogAnimation
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)

            it.setCanceledOnTouchOutside(false)
        }
        return layout
    }

    fun setOnCancelRunnable(runnable: Runnable) {
        this.onCancelRunnable = runnable
    }

    fun show(activity: FragmentActivity?) {

        if (activity == null) {
            return
        }

        val loadingDialog =
            activity.supportFragmentManager.findFragmentByTag(DIALOG_TAG) as DialogFragment?

        if (loadingDialog != null) {
            activity.supportFragmentManager.beginTransaction().remove(loadingDialog)
                .commitAllowingStateLoss()
        }

        this.show(activity.supportFragmentManager, DIALOG_TAG)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        if (onCancelRunnable != null) {
            onCancelRunnable!!.run()
        }
    }

    override fun dismiss() {
        try {
            super.dismiss()
        } catch (e: Exception) {
        }

        try {
            activity!!.supportFragmentManager.beginTransaction().remove(this)
                .commitAllowingStateLoss()
        } catch (e: Exception) {
        }

    }
}