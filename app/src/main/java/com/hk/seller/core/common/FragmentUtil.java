package com.hk.seller.core.common;

import android.content.Context;

import com.hk.seller.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.hk.seller.core.common.AppConstant.FragmentTransition.DOWN_TO_TOP;
import static com.hk.seller.core.common.AppConstant.FragmentTransition.LEFT_TO_RIGHT;
import static com.hk.seller.core.common.AppConstant.FragmentTransition.NEITHER_LEFT_NOR_RIGHT;
import static com.hk.seller.core.common.AppConstant.FragmentTransition.RIGHT_TO_LEFT;
import static com.hk.seller.core.common.AppConstant.FragmentTransition.TOP_TO_DOWN;

public class FragmentUtil {

    private FragmentUtil() {
    }

    public synchronized static void replaceFragment(Context context, Fragment fragment, int frameLayoutId, boolean removeStack, int animConstant) {

        try {
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();


            switch (animConstant) {
                case LEFT_TO_RIGHT:
                    transaction.setCustomAnimations(R.anim.frag_exit_right, R.anim.frag_enter_left);
                    break;

                case RIGHT_TO_LEFT:
                    transaction.setCustomAnimations(R.anim.frag_enter_right, R.anim.frag_exit_left);
                    break;

                case NEITHER_LEFT_NOR_RIGHT:
                    break;

                case TOP_TO_DOWN:
                    transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up);
                    break;

                case DOWN_TO_TOP:
                    transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                    break;

                default:
                    break;
            }


            if (!removeStack) {
                transaction.addToBackStack(fragment.getClass().getName());
            }

            transaction.replace(frameLayoutId, fragment);
            transaction.commitAllowingStateLoss();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public synchronized static void addFragment(Context context, Fragment fragment, int frameLayoutId, int animConstant) {

        try {

            FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            switch (animConstant) {
                case LEFT_TO_RIGHT:
                    // transaction.setCustomAnimations(R.anim.frag_exit_right, R.anim.frag_enter_left);
                    break;

                case RIGHT_TO_LEFT:
                    // transaction.setCustomAnimations(R.anim.frag_enter_right,R.anim.frag_exit_left);
                    break;

                case NEITHER_LEFT_NOR_RIGHT:
                    break;

                case TOP_TO_DOWN:
                    transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up);
                    break;

                case DOWN_TO_TOP:
                    transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                    break;

                default:
                    break;
            }

            transaction.add(frameLayoutId, fragment);
            transaction.addToBackStack(fragment.getClass().getName());
            transaction.commitAllowingStateLoss();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public synchronized static void removeMemberFragment(Context context, int animConstant, Fragment fragment) {
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        switch (animConstant) {
            case LEFT_TO_RIGHT:
                transaction.setCustomAnimations(R.anim.frag_exit_right, R.anim.frag_enter_left);
                break;

            case RIGHT_TO_LEFT:
                transaction.setCustomAnimations(R.anim.frag_enter_right, R.anim.frag_exit_left);
                break;

            case NEITHER_LEFT_NOR_RIGHT:
                break;

            case TOP_TO_DOWN:
                transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up);
                break;

            case DOWN_TO_TOP:
                transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                break;

            default:
                break;
        }
        transaction.remove(fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();


    }
}