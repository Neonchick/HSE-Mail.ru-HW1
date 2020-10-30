package kuznetsov.hw1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class NumbFragment : Fragment() {
    private var numb: TextView? = null
    private var layout: ConstraintLayout? = null

    companion object {
        const val EXTRAS_POSITION = "POSITION"

        // helper-метод для создания инстанса фрагмента
        // Это один из подходов в упрощении
        fun newInstance(position: Int): NumbFragment {
            // Создаем данные, которые потом положим в фрагмент
            val extras = Bundle().apply {
                putInt(EXTRAS_POSITION, position)
            }

            val fragment = NumbFragment().apply {
                arguments = extras
            }

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.numb_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numb = view.findViewById(R.id.number_tv)
        layout = view.findViewById(R.id.layout)

        val position = (arguments?.getInt(EXTRAS_POSITION) ?: 0) + 1

        if (position % 2 == 0) {
            numb?.apply {
                text = position.toString()
                setTextColor(ContextCompat.getColor(activity!!, R.color.color_red))
            }
            layout?.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.color_blue))
        } else {
            numb?.apply {
                text = position.toString()
                setTextColor(ContextCompat.getColor(activity!!, R.color.color_blue))
            }
            layout?.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.color_red))
        }
    }
}
