package com.example.house_rent_and_payment.user_home_main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.house_rent_and_payment.R;

public class Page_View_Model extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input)
        {

            String myst = "A material metaphor is the" +
            " unifying theory of a rationalized space and a system of motion."+
            "The material is grounded in tactile reality, inspired by the study of paper and ink, yet "+
            "technologically advanced and open to imagination and magic.\n"+
            "Surfaces and edges of the material provide visual cues that are grounded in reality. The "+
            "use of familiar tactile attributes helps users quickly understand affordances. Yet the "+
            "flexibility of the material creates new affordances that supercede those in the physical "+
            "world, without breaking the rules of physics.\n"+
            "The fundamentals of light, surface, and movement are key to conveying how objects move, "+
            "interact, and exist in space and in relation to each other. Realistic lighting shows "+
            "seams, divides space, and indicates moving parts.\n\n";

            return "Hello world from section: " + input+"\n "+myst;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}