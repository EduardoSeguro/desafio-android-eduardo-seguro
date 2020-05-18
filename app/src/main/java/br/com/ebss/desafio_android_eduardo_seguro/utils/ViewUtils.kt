package br.com.ebss.desafio_android_eduardo_seguro.utils

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import br.com.ebss.desafio_android_eduardo_seguro.ui.dialog.LoadingDialog

object ViewUtils {

    fun showDialogProgressBar(activity: FragmentActivity, cancelable: Boolean): LoadingDialog {
        return showDialogProgressBar(activity, cancelable, null)
    }

    fun showDialogProgressBar(
        activity: FragmentActivity,
        cancelable: Boolean,
        onCancelRunnable: Runnable?
    ): LoadingDialog {
        return showDialogProgressBar(activity, null, cancelable, onCancelRunnable)
    }

    fun showDialogProgressBar(
        activity: FragmentActivity,
        customLoadingText: String?,
        cancelable: Boolean,
        onCancelRunnable: Runnable?
    ): LoadingDialog {
        val loadingDialog = LoadingDialog()
        if (customLoadingText != null) {
            val args = Bundle()
            args.putString("customLoadingText", customLoadingText)
            loadingDialog.arguments = args
        }
        loadingDialog.isCancelable = cancelable
        loadingDialog.setOnCancelRunnable(
            onCancelRunnable ?: Runnable { hideDialogProgressBar(loadingDialog) })

        loadingDialog.show(activity)
        return loadingDialog
    }

    fun hideDialogProgressBar(loadingDialog: LoadingDialog?) {

        loadingDialog?.dismiss()
    }
}