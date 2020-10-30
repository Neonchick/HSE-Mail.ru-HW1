package kuznetsov.hw1

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView

class NumbListViewHolder(itemView: View, private val context: Context, val listener: IListener) :
        RecyclerView.ViewHolder(itemView)
{
    private val numberTV: TextView = itemView.findViewById(R.id.number_tv)
    private val layout: ConstraintLayout = itemView.findViewById(R.id.element_layout)

    init
    {
        itemView.setOnClickListener {
            listener.onNumbClicked(adapterPosition)
        }
    }

    // Подписчик на события этого холдера
    interface IListener {

        // уведомляение подписчика о том, что был клик по элементу
        fun onNumbClicked(position: Int)
    }

    fun bind(item: Int)
    {
        // Ставим число
        numberTV.text = item.toString()

        // Ставим цвет, в зависимости от четности
        if (item % 2 == 0)
        {
            numberTV.setTextColor(getColor(context, R.color.color_red))
            layout.setBackgroundColor(getColor(context, R.color.color_blue))
        }
        else
        {
            numberTV.setTextColor(getColor(context, R.color.color_blue))
            layout.setBackgroundColor(getColor(context, R.color.color_red))
        }
    }
}