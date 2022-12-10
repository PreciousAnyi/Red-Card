package ps.room.redcard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ps.room.redcard.tablayout.AllFragment
import ps.room.redcard.tablayout.PastFragment

class ExamViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return    when(position){
                0->{
                    AllFragment()
                }
                1->{
                    PastFragment()
                }
                else->{
                    Fragment()
                }
            }
    }


}