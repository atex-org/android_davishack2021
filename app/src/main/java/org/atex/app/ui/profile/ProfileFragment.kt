package org.atex.app.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import io.realm.mongodb.User
import kotlinx.android.synthetic.main.fragment_profile.*
import org.atex.app.R
import org.atex.app.activity.IntroductionActivity
import org.atex.app.atexApp
import org.atex.app.ui.notifications.NotificationsViewModel

class ProfileFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val sign_out_button: MaterialButton = root.findViewById(R.id.sign_out_button)
        sign_out_button.setOnClickListener {
            val user: User? = atexApp.currentUser()
            user?.logOut()
            val intent = Intent(this.activity, IntroductionActivity::class.java)
            startActivity(intent)
            this.activity?.finish()
        }
        return root
    }
}