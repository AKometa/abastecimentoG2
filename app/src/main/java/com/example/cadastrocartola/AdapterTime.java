package com.example.cadastrocartola;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterTime extends BaseAdapter {

    private List<Time> listaTimes;
    private Context context;
    private LayoutInflater inflater;

    public AdapterTime(Context context, List<Time> listaTimes) {
        this.context = context;
        this.listaTimes = listaTimes;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaTimes.size();
    }

    @Override
    public Object getItem(int i) {
        return listaTimes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaTimes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemSuporte item;

        if (view == null) {
            view = inflater.inflate(R.layout.layout_lista, null);
            item = new ItemSuporte();
            item.tvId = (TextView) view.findViewById(R.id.tvListaId);
            item.tvNome = (TextView) view.findViewById(R.id.tvListaNome);
            item.tvKm = (TextView) view.findViewById(R.id.tvListaKm);
            item.tvListaAnoFundamento = (TextView) view.findViewById(R.id.tvListaAnoFundamento);
            item.layout = (LinearLayout) view.findViewById(R.id.layout);
            view.setTag(item);
        } else {
            item = (ItemSuporte) view.getTag();
        }

        Time produto = listaTimes.get(i);
        item.tvId.setText(String.valueOf(produto.getId()));
        item.tvNome.setText(produto.getNome());
        item.tvKm.setText(produto.getKm());
        item.tvListaAnoFundamento.setText(String.valueOf(produto.getAno()));

        if (produto.getNome().equals("MainActivity Vazia!")) {
            item.tvId.setText(" ");
            item.tvListaAnoFundamento.setText(" ");
        }

        if (i % 2 == 0) {
            item.layout.setBackgroundColor(Color.WHITE);
        } else {
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }


        return view;
    }


    private class ItemSuporte {
        TextView tvId, tvNome, tvListaAnoFundamento, tvKm;
        LinearLayout layout;
    }
}

