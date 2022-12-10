package ps.room.redcard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ps.room.redcard.adapters.ExamViewPagerAdapter


class ExamScheduleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_exam_schedule, container, false)

        //        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
//        val tabLayout: TabLayout? = view?.findViewById(R.id.tab_layout)
        val tabLayout = view.findViewById<View>(R.id.tab_layout) as TabLayout
//        val viewPager2=findViewById<ViewPager2>(R.id.view_pager_2)
//        val viewPager2: ViewPager2? = view?.findViewById(R.id.view_pager_2)

        val viewPager2 = view.findViewById<View>(R.id.view_pager_2) as ViewPager2

        val adapter= ExamViewPagerAdapter(childFragmentManager,lifecycle)

        viewPager2?.adapter=adapter


            TabLayoutMediator(tabLayout,viewPager2){tab,position->
                when(position){
                    0->{
                        tab.text="All"
                    }
                    1->{
                        tab.text="Past"
                    }
                }
            }.attach()



        return view
    }


}
