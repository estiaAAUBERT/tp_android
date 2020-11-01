package com.example.neighbors.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.data.NeighborRepository
import com.example.neighbors.models.Neighbor
import com.google.android.material.textfield.TextInputLayout

class AddNeighbourFragment: Fragment(), TextWatcher{

    private lateinit var rgtButton:Button
    private lateinit var nameText:EditText
    private lateinit var avatarUrlText: EditText
    private lateinit var phoneText: EditText
    private lateinit var websteText: EditText
    private lateinit var adressText:EditText
    private lateinit var aboutmeText: EditText

    //ERRORS
    private lateinit var phoneLayout: TextInputLayout
    private lateinit var avatarurlLayout: TextInputLayout
    private lateinit var urlWebsiteLayout: TextInputLayout
    private lateinit var aboutmelayout: TextInputLayout

    private var errorAdd : Boolean = false


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_neighbor, container, false)
        rgtButton = view.findViewById(R.id.btn_register)

        nameText = view.findViewById(R.id.name)
        avatarUrlText = view.findViewById(R.id.avatarurl)
        phoneText = view.findViewById(R.id.telephone)
        websteText = view.findViewById(R.id.webste)
        adressText = view.findViewById(R.id.adresse)
        aboutmeText = view.findViewById(R.id.aboutme)

        phoneLayout = view.findViewById(R.id.telephonelyt)
        avatarurlLayout= view.findViewById(R.id.avatarurllyt)
        urlWebsiteLayout = view.findViewById(R.id.webstelyt)
        aboutmelayout = view.findViewById(R.id.aboutmelyt)



        nameText.addTextChangedListener(this)
        avatarUrlText.addTextChangedListener(this)
        phoneText.addTextChangedListener(this)
        websteText.addTextChangedListener(this)
        adressText.addTextChangedListener(this)
        aboutmeText.addTextChangedListener(this)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rgtButton.isEnabled=false

        Toast.makeText(view.context, R.string.toasttextadd, Toast.LENGTH_LONG).show()

        rgtButton.setOnClickListener {

            val neighbor = Neighbor(NeighborRepository.getInstance().getNeighbours().size + 1,nameText.text.toString(),
                    avatarUrlText.text.toString(),
                    phoneText.text.toString(),
                    websteText.text.toString(),
                    adressText.text.toString(),
                    false,
                    aboutmeText.text.toString())
            NeighborRepository.getInstance().createNeighbours(neighbor)

            (activity as? NavigationListener)?.let {
                it.showFragment(ListNeighborsFragment())
            }

            Toast.makeText(view.context, R.string.toasttextadded, Toast.LENGTH_LONG).show()
        }

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.nouveau_voisin)
        }

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
    // Le bouton reste grisé tant que tous les champs ne sont pas validés
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        rgtButton.isEnabled =
                nameText.text.isNotBlank() && avatarUrlText.text.isNotEmpty() && adressText.text.isNotEmpty() && websteText.text.isNotEmpty() && phoneText.text.isNotEmpty() && aboutmeText.text.isNotEmpty()
    }
    override fun afterTextChanged(s: Editable?) {

        with(phoneText.text.toString()) {

            if ((startsWith("0") || startsWith("0")) && length == 10 || this.isBlank()){
                phoneLayout.error = null
            } else {
                phoneLayout.error = getString(R.string.error_phone)
                rgtButton.isEnabled = false
            }
        }
        with(aboutmeText.text.toString()) {
            if (this.length <= 30) {
                aboutmelayout.error = null
            } else {
                aboutmelayout.error = getString(R.string.error_aboutme)
                rgtButton.isEnabled = false

            }
        }
        with(websteText.text.toString()) {
            if (Patterns.WEB_URL.matcher(websteText.text.toString()).matches() || this.isBlank()) {
                urlWebsiteLayout.error = null
            } else {
                urlWebsiteLayout.error = getString(R.string.error_website)
                rgtButton.isEnabled = false

            }
        }

        with(avatarUrlText.text.toString()) {
            if (Patterns.WEB_URL.matcher(avatarUrlText.text.toString()).matches() || this.isBlank()) {
                avatarurlLayout.error = null
            } else {
                avatarurlLayout.error = getString(R.string.error_website)
                rgtButton.isEnabled = false

            }
        }
    }


}