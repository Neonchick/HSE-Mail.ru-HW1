package kuznetsov.hw1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NumbListAdapter(val numberList: MutableList<Int>,
                      val listener: NumbListViewHolder.IListener) :
        RecyclerView.Adapter<NumbListViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbListViewHolder
    {
        // Получаем инфлейтер и создаем нужный layout
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.list_element, parent, false)

        // Создаем ViewHolder
        return NumbListViewHolder(layout, parent.context, listener)
    }

    override fun onBindViewHolder(holder: NumbListViewHolder, position: Int)
    {
        holder.bind(numberList[position])
    }

    override fun getItemCount() = numberList.size

    fun onAddElem() = numberList.add(numberList.size + 1)
}
