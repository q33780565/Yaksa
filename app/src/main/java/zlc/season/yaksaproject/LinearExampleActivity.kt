package zlc.season.yaksaproject

import android.view.View
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.android.synthetic.main.header_item.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import zlc.season.yaksa.YaksaItem
import zlc.season.yaksa.linear

class LinearExampleActivity : ExampleActivity() {

    override fun onChange(data: List<ExampleViewModel.ExampleData>?) {
        super.onChange(data)
        data?.let {
            rv_list.linear {
                item {
                    HeaderItem("This is a Header")
                }

                itemDsl(index = 0) {
                    xml(R.layout.header_item)
                    render {
                        it.tv_header.text = "This is a dsl Header"
                        it.setOnClickListener { toast("DSL Header Clicked") }
                    }
                }

                data.forEach { each ->
                    itemDsl {
                        xml(R.layout.list_item)
                        render {
                            it.textView.text = each.title
                        }
                        renderX { position, it ->
                            it.setOnClickListener { toast("Clicked $position") }
                        }
                    }
                }

                item {
                    ListItem("This is item too!")
                }
            }
        }
    }

    private class HeaderItem(val title: String) : YaksaItem {
        override fun render(position: Int, view: View) {
            view.tv_header.text = title
            view.setOnClickListener { }
        }

        override fun xml(): Int {
            return R.layout.header_item
        }
    }

    private class ListItem(val str: String) : YaksaItem {
        override fun render(position: Int, view: View) {
            view.textView.text = str
            view.setOnClickListener { }
        }

        override fun xml(): Int {
            return R.layout.list_item
        }
    }
}
