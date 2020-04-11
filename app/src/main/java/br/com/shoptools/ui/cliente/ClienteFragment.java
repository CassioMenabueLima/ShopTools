package br.com.shoptools.ui.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.com.shoptools.MainActivity;
import br.com.shoptools.R;
import br.com.shoptools.ui.configuracoes.ConfigActivity;
import br.com.shoptools.ui.pedido.PedidoActivity;
import br.com.shoptools.ui.produtos.ProdutoActivity;

public class ClienteFragment extends Fragment {

    private ClienteViewModel clienteViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        clienteViewModel =
                ViewModelProviders.of(this).get(ClienteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cliente, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        clienteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final Button btnCli = root.findViewById(R.id.btnCli);
        final Button btnPed = root.findViewById(R.id.btnPed);
        final Button btnProd = root.findViewById(R.id.btnProd);
        final Button tnConfig = root.findViewById(R.id.btnConfig);
        btnCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClienteActivity.class));
            }
        });

        btnPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PedidoActivity.class));
            }
        });

        btnProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProdutoActivity.class));
            }
        });

        tnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ConfigActivity.class));
            }
        });
        return root;
    }
}
