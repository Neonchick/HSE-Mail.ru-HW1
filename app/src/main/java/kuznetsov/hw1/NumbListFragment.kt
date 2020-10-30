package kuznetsov.hw1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_fragment.*
import kuznetsov.hw1.R.bool.is_land

class NumbListFragment : Fragment() {
    companion object {
        const val PORT_COLUMN_COUNT = 3
        const val LAND_COLUMN_COUNT = 4
    }

    // Подписчик на события
    interface IListener {
        fun onAddClicked()
        fun onNumbClicked(position: Int)
    }

    private var listener: IListener? = null
    private var recycler: RecyclerView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = requireActivity() as? IListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val size = (activity!! as MainActivity).listSize
        // Инициализируем View для отображения списка
        recycler = view.findViewById(R.id.recycler)
        recycler?.apply {
            adapter = NumbListAdapter(MutableList(size) { it + 1 }, NumbClickHandler())
            layoutManager = GridLayoutManager(
                context,
                if (resources.getBoolean(is_land)) LAND_COLUMN_COUNT else PORT_COLUMN_COUNT
            )
        }

        add_button.setOnClickListener {
            (recycler?.adapter as NumbListAdapter).onAddElem()
            recycler?.adapter?.notifyDataSetChanged()
            listener?.onAddClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        recycler = null
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    // Одна из возможных реализаций отслеживания клика по элементу
    // обработчик клика по элементу
    inner class NumbClickHandler : NumbListViewHolder.IListener {
        override fun onNumbClicked(position: Int) {
            listener?.onNumbClicked(position)
        }
    }
}
