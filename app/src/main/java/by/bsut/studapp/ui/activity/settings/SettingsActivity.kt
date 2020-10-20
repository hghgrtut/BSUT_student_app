package by.bsut.studapp.ui.activity.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import by.bsut.studapp.R
import by.bsut.studapp.timetable.presenter.preferences.PREFERENCES
import by.bsut.studapp.timetable.presenter.preferences.TimetablePreferenceEditor
import by.bsut.studapp.utils.HelperFuns.showToast
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputLayout

class SettingsActivity : AppCompatActivity(R.layout.activity_settings) {

    private lateinit var timetablePrefEditor: TimetablePreferenceEditor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timetablePrefEditor = TimetablePreferenceEditor(applicationContext.getSharedPreferences(
            PREFERENCES, MODE_PRIVATE))

        val militarySwitch: SwitchMaterial = findViewById(R.id.switchMilitary)
        val podgroupaText: TextInputLayout = findViewById(R.id.podgroupa)
        val savePodgroupa: AppCompatImageButton = findViewById(R.id.savePogroupa)

        militarySwitch.isChecked = timetablePrefEditor.getMilitary()
        podgroupaText.editText?.setText(timetablePrefEditor.getPodgroupa().toString())

        militarySwitch.setOnClickListener {
            timetablePrefEditor.setMilitary(militarySwitch.isChecked)
        }

        savePodgroupa.setOnClickListener {
            try {
                timetablePrefEditor.setPodgroupa(podgroupaText.editText?.text.toString().toInt())
                showToast(this, R.string.toast_refresh_podgroupa)
            } catch (e: IllegalArgumentException) {
                showToast(this, R.string.toast_error)
            }
        }
    }
}