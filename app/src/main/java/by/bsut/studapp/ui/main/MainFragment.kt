package by.bsut.studapp.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.bsut.studapp.R
import by.bsut.studapp.constants.BSUT_URL
import by.bsut.studapp.constants.DIST_URL
import by.bsut.studapp.constants.ENDPOINT_OBSCH_ORG
import by.bsut.studapp.constants.ENDPOINT_REKTOR_QUESTION

class MainFragment : Fragment(R.layout.main_fragment) {
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.go_to_auditories).setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_auditoriesFragment)
        }

        view.findViewById<Button>(R.id.go_to_dist).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(DIST_URL) }
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.go_to_obsch_org).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(BSUT_URL + ENDPOINT_OBSCH_ORG) }
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.go_to_rektor).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(BSUT_URL + ENDPOINT_REKTOR_QUESTION) }
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.go_to_site).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(BSUT_URL) }
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.go_to_timetable).setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_timetableFragment)
        }
    }
}