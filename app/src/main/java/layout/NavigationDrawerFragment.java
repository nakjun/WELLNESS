package layout;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nj.myapplication.CDI_Activity.CDI_Activity;
import com.example.nj.myapplication.DP_Activity.Calendar3;
import com.example.nj.myapplication.DP_Activity.DpActivity;
import com.example.nj.myapplication.DW_Activity.DW_Activity;
import com.example.nj.myapplication.R;
import com.example.nj.myapplication.SM_Activity.SM_Activity;
import com.example.nj.myapplication.YN_Activity.YesNoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private boolean userLearnedDrawer;
    private boolean FromSavedInstanceState;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private View containerView;
    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            FromSavedInstanceState = true;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    public void setUp(int fragment, DrawerLayout drawerLayout, Toolbar toolbar, final int part) {
        containerView = getActivity().findViewById(fragment);
        this.drawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!userLearnedDrawer) {
                    userLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, userLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
                if(getActivity().getActionBar()!=null) {
                    if (Build.VERSION.SDK_INT < 21) {
                        switch (part) {
                            case 0:

                                getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_pink));
                                break;
                            case 1:
                                getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_blue));
                                break;
                            case 2:
                                getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_green));
                                break;
                            case 3:
                                getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_yellow));
                                break;
                            case 4:
                                break;

                        }
                    } else {
                        switch (part) {
                            case 0:
                                getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_pink, getContext().getTheme()));
                                break;
                            case 1:
                                getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_blue, getContext().getTheme()));
                                break;
                            case 2:
                                getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_green, getContext().getTheme()));
                                break;
                            case 3:
                                getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_yellow, getContext().getTheme()));
                                break;
                            case 4:
                                break;

                        }
                    }
                }


            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if (!userLearnedDrawer && !FromSavedInstanceState) {
            drawerLayout.openDrawer(containerView);
        }
        this.drawerLayout.setDrawerListener(drawerToggle);
        this.drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }
}
