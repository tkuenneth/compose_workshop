package eu.thomaskuenneth.counterdemoandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.thomaskuenneth.counterdemoandroid.databinding.MainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        var counter = 0
        binding.button.setOnClickListener {
            updateUI(counter = ++counter)
        }
        updateUI(counter = counter)
        setContentView(binding.root)
    }

    private fun updateUI(counter: Int) {
        binding.textview.text = if (counter == 0)
            getString(R.string.not_clicked)
        else
            counter.toString()
        binding.textview.setTextAppearance(
            if (counter == 0)
                android.R.style.TextAppearance_Material_Display2
            else
                android.R.style.TextAppearance_Material_Display4
        )
    }
}