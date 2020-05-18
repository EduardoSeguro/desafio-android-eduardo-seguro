package br.com.ebss.desafio_android_eduardo_seguro

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.ebss.desafio_android_eduardo_seguro.ui.character.CharacterListFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            addFragment(CharacterListFragment.newInstance())
        }
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right)
            .add(R.id.container, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count <= 1) {
            validateExit()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun validateExit() {
        AlertDialog.Builder(this)
            .setTitle("Sair")
            .setMessage("Deseja sair do aplicativo?")
            .setPositiveButton("Sim") { _, _ ->
                exitProcess(0)
            }
            .setNegativeButton("Não", null)
            .show()
    }
}
