package ru.tech_mail.translator.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

<<<<<<< HEAD
import ru.tech_mail.translator.activities.Languages;
=======
import ru.tech_mail.translator.ListLanguagesActivity;
>>>>>>> 036feb4e6cd4a9985ebe3467de4da65009e5dbcb

/**
 * Created by vadim on 02.03.15.
 */
public class ListLanguagesFragment extends ListFragment {
    private String[] languages;
    private OnItemSelectedListener mCallback;


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onArticleSelected(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //ArrayList<String> arrayListLanguages = savedInstanceState.getStringArrayList("languages");
        //languages = (String[]) arrayListLanguages.toArray();
        ListLanguagesActivity activity = (ListLanguagesActivity) getActivity();
        languages = activity.getLanguages();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,
                languages);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

    // Container Activity must implement this interface
    public interface OnItemSelectedListener {
        public void onArticleSelected(int position);
    }

}
