package com.example.house_rent_and_payment.user_home_main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.apartment_details.Apartment_Frag1_DB;
import com.example.house_rent_and_payment.apartment_details.Apartment_Frag2_DB;
import com.example.house_rent_and_payment.market_view.Common_Market_DB;
import com.example.house_rent_and_payment.rent_details.Rent_Frag2_DB;
import com.example.house_rent_and_payment.user_credentials.User_Info_DB;
import com.example.house_rent_and_payment.user_credentials.User_Personal_Info_DB;

import static com.example.house_rent_and_payment.user_home_main.Sections_Pager_Adapter.detect_main_content;

public class Placeholder_Fragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private Page_View_Model pageViewModel;
    private int detect_tab = 0;

    public static Placeholder_Fragment newInstance(int index) {
        Placeholder_Fragment fragment = new Placeholder_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageViewModel = ViewModelProviders.of(this).get(Page_View_Model.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            detect_tab = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.b_c_user_home_main_fragment1, container, false);

        if (detect_main_content == 0) {

            if (detect_tab == 1) {
                final TextView textView = root.findViewById(R.id.section_label_0_a);
                pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        textView.setText("main0_tab1\n" + s);
                    }
                });
            }
            else if (detect_tab == 2) {
                root = inflater.inflate(R.layout.b_c_user_home_main_fragment2, container, false);
                final TextView textView = root.findViewById(R.id.section_label_0_b);
                pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        textView.setText("main0_tab2\n" + s);
                    }
                });
            } else if (detect_tab == 3) {
                root = inflater.inflate(R.layout.b_c_user_home_main_fragment3, container, false);
                final TextView textView = root.findViewById(R.id.section_label_0_c);
                pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        textView.setText("main0_tab3\n" + s);
                    }
                });
            }
        } else if (detect_main_content == 1) {
            if (detect_tab == 1) {
                root = inflater.inflate(R.layout.c_a_user_profile_main_fragment1, container, false);
                final View root_profile_frag1 = root;

                Handler handler2_1 = new Handler(Looper.getMainLooper()) {
                    public void handleMessage(Message msg) {
                        User_Info_DB user_info_db = new User_Info_DB(
                                getContext(),
                                getActivity(),
                                root_profile_frag1);
                    }
                };
                Thread updateUi = new Thread() {
                    public void run() {
                        try {
                            handler2_1.sendEmptyMessage(0);
                        }

                        catch (Exception e) {
                            e.printStackTrace();
                            Log.d("failed", "exception");
                        }
                    }
                };
                updateUi.start();
            }
            else if (detect_tab == 2) {
                root = inflater.inflate(R.layout.c_a_user_profile_main_fragment2, container, false);
                final View root_profile_frag2 = root;

                Handler handler2_2 = new Handler(Looper.getMainLooper()) {
                    public void handleMessage(Message msg) {
                        User_Personal_Info_DB user_personal_info_db = new User_Personal_Info_DB(
                                getContext(),
                                getActivity(),
                                root_profile_frag2);
                    }
                };
                Thread updateUi = new Thread() {
                    public void run() {
                        try {
                            handler2_2.sendEmptyMessage(0);
                        }

                        catch (Exception e) {
                            e.printStackTrace();
                            Log.d("failed", "exception");
                        }
                    }
                };
                updateUi.start();
            }
        }
        else if (detect_main_content == 2) {
            if (detect_tab == 1) {
                root = inflater.inflate(R.layout.d_a_payment_main_fragment1, container, false);
//                final TextView textView = root.findViewById(R.id.section_label_0_a);
//                pageViewModel.getText().observe(this, new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        textView.setText("main2_tab1\n"+s);
//                    }
//                });
            } else if (detect_tab == 2) {
                root = inflater.inflate(R.layout.d_a_payment_main_fragment2, container, false);
                //    final TextView textView = root.findViewById(R.id.section_label_0_b);
//                pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        // textView.setText("main2_tab2\n"+s);
//                    }
//                });
            } else if (detect_tab == 3) {
                root = inflater.inflate(R.layout.d_a_payment_main_fragment3, container, false);
//                final TextView textView = root.findViewById(R.id.section_label_0_c);
//                pageViewModel.getText().observe(this, new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        textView.setText("main2_tab3\n"+s);
//                    }
//                });
            }
        } else if (detect_main_content == 3) {
            if (detect_tab == 1) {
                root = inflater.inflate(R.layout.e_a_messages_main_fragment1, container, false);
//                final TextView textView = root.findViewById(R.id.section_label_0_a);
//                pageViewModel.getText().observe(this, new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        textView.setText("main3_tab1\n"+s);
//                    }
//                });
            } else if (detect_tab == 2) {
                root = inflater.inflate(R.layout.e_a_messages_main_fragment2, container, false);
//                final TextView textView = root.findViewById(R.id.section_label_0_b);
//                pageViewModel.getText().observe(this, new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        textView.setText("main3_tab2\n"+s);
//                    }
//                });
            } else if (detect_tab == 3) {
                root = inflater.inflate(R.layout.e_a_messages_main_fragment3, container, false);
//                final TextView textView = root.findViewById(R.id.section_label_0_c);
//                pageViewModel.getText().observe(this, new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        textView.setText("main3_tab3\n"+s);
//                    }
//                });
            }

        } else if (detect_main_content == 4) {
            if (detect_tab == 1) {
                root = inflater.inflate(R.layout.f_a_news_and_notifications_main_fragment1, container, false);
//                final TextView textView = root.findViewById(R.id.section_label_0_a);
//                pageViewModel.getText().observe(this, new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        textView.setText("main4_tab1\n"+s);
//                    }
//                });
            } else if (detect_tab == 2) {
                root = inflater.inflate(R.layout.f_a_news_and_notifications_main_fragment2, container, false);
//                final TextView textView = root.findViewById(R.id.section_label_0_b);
//                pageViewModel.getText().observe(this, new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        textView.setText("main4_tab2\n"+s);
//                    }
//                });
            } else if (detect_tab == 3) {
                root = inflater.inflate(R.layout.f_a_news_and_notifications_main_fragment3, container, false);
//                final TextView textView = root.findViewById(R.id.section_label_0_c);
//                pageViewModel.getText().observe(this, new Observer<String>() {
//                    @Override
//                    public void onChanged(@Nullable String s) {
//                        textView.setText("main4_tab3\n"+s);
//                    }
//                });
            }

        }
        else if (detect_main_content == 5) {
          if (detect_tab == 1) {
             root = inflater.inflate(R.layout
                           .g_a_my_apartment_main_fragment1, container,
                            false);

             final View root_apart_frag1 = root;
             Handler handler7_1 = new Handler(Looper.getMainLooper()) {
             public void handleMessage(Message msg) {
                Apartment_Frag1_DB apff = new Apartment_Frag1_DB(
                        getContext(),getActivity(),root_apart_frag1);
               }
             };

             Thread updateUi = new Thread() {
                 public void run() {
                    try {
                      handler7_1.sendEmptyMessage(0);
                    }
                    catch (Exception e) {
                       e.printStackTrace();
                       Log.d("failed", "exception");
                    }
                 }
              };
              updateUi.start();
            }
            else if (detect_tab == 2) {

               root = inflater.inflate(R.layout.g_a_my_apartment_main_fragment2,
                               container, false);
               final View root_apart_frag2 = root;

               Handler handler7_2 = new Handler(Looper.getMainLooper()) {
                 public void handleMessage(Message msg) {
                     Apartment_Frag2_DB apartment_frag2_db = new Apartment_Frag2_DB(
                                                                 getContext(),
                                                                 getActivity(),
                                                                 root_apart_frag2);
                 }
               };
               Thread updateUi = new Thread() {
                public void run() {
                    try {
                         handler7_2.sendEmptyMessage(0);
                       }

                    catch (Exception e) {
                            e.printStackTrace();
                            Log.d("failed", "exception");
                    }
                }
                };
                updateUi.start();
            }
        }

        else if(detect_main_content==6){
        if (detect_tab == 1) {
            root = inflater.inflate(R.layout.h_a_my_rent_main_fragment1, container, false);
        }
        else if (detect_tab == 2) {
            root = inflater.inflate(R.layout.h_a_my_rent_main_fragment2, container, false);
            final View root_rent_frag2 = root;

            Handler handler8_2 = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message msg) {
                    Rent_Frag2_DB rent_frag2_db = new Rent_Frag2_DB(
                            getContext(),
                            getActivity(),
                            root_rent_frag2);
                }
            };
            Thread updateUi = new Thread() {
                public void run() {
                    try {
                        handler8_2.sendEmptyMessage(0);
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d("failed", "exception");
                    }
                }
            };
            updateUi.start();
        }
        else if (detect_tab == 3) {
            root = inflater.inflate(R.layout.h_a_my_rent_main_fragment3, container, false);

        }
    }
        else if(detect_main_content==7) {
        if (detect_tab == 1) {
            root = inflater.inflate(R.layout.i_a_available_rent_main_fragment1, container, false);
            final View root_market_frag = root;

            Handler handler9_2 = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message msg) {
                    Common_Market_DB common_market_db = new Common_Market_DB(
                            getContext(),
                            getActivity(),
                            root_market_frag);
                }
            };
            Thread updateUi = new Thread() {
                public void run() {
                    try {
                        handler9_2.sendEmptyMessage(0);
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d("failed", "exception");
                    }
                }
            };
            updateUi.start();
        }

    }

        return root;
  }
}
