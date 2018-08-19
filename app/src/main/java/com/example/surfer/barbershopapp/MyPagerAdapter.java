package com.example.surfer.barbershopapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private final Context context;
    private HomeTabFragment homeFragment;
    private BuscarTabFragment buscarFragment;
    private UsuarioTabFragment usuarioFragment;


    public MyPagerAdapter(android.support.v4.app.FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if(homeFragment ==null){
                    homeFragment = new HomeTabFragment();
                }
                return homeFragment;

            case 1:
                if(buscarFragment ==null){
                    buscarFragment = new BuscarTabFragment();
                }
                return buscarFragment;
            case 2:
                if(usuarioFragment ==null){
                    usuarioFragment = new UsuarioTabFragment();
                }
                return usuarioFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        /*if(position==0){
            return context.getResources().getString(R.string.send);
        }else if(position==1){
            return "tab 2";
        }
        return "";*/

        return super.getPageTitle(position);
    }
}
