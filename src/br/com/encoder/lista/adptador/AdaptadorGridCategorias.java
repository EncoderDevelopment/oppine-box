package br.com.encoder.lista.adptador;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.unidade.UnidadeCategoriaEmpresa;
import br.com.oppinebox.R;

public class AdaptadorGridCategorias extends ArrayAdapter<UnidadeCategoriaEmpresa>{
    private List<UnidadeCategoriaEmpresa> categorias;

    
    
    public AdaptadorGridCategorias(Context context ,  List<UnidadeCategoriaEmpresa> categoriaEmpresas) {
    	 super(context, R.layout.categoria_layout);
    	 this.categorias = categoriaEmpresas;
    }

   
    @Override
    public View getView(int posicao, View convertView, ViewGroup viewGroup) {
       
        
        View view = convertView;
        AuxiliaCategoria ap;
               
            if(naoExisteView(view)){
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_grid_categorias, viewGroup, false);
                ap = new AuxiliaCategoria();
                              
                ap.txNome = (TextView) view.findViewById(R.id.txCategoria);
                ap.iconeCategoria = (ImageView) view.findViewById(R.id.iconeCategoria);
                
                Typeface font = Typeface.createFromAsset(getContext().getAssets(), ConfiguracaoFontCaminho.FONT_SENSATION_NEGRITO);
                
                ap.txNome.setTypeface(font);
                ap.txNome.setGravity(Gravity.CENTER_HORIZONTAL);
                view.setTag(ap);
                
                
           }else{
                   ap = (AuxiliaCategoria) view.getTag();
           }

        UnidadeCategoriaEmpresa categoriaEmpresa = categorias.get(posicao);
        
        if(existeEmpresa(categorias)){

        	if (categoriaEmpresa.getIconeCategoria() != null) {
        		ap.iconeCategoria.setImageBitmap(categoriaEmpresa.getIconeCategoria());	
        	}
            
        	ap = (AuxiliaCategoria) view.getTag();
           	
            ap.txNome.setText(categoriaEmpresa.getCate_nome());
            
            
        }
        return view;

    }

    @Override
	public int getCount() {
        return categorias.size();
    }

    @Override
	public UnidadeCategoriaEmpresa getItem(int posicao) {
        return categorias.get(posicao);
    }

    @Override
	public long getItemId(int posicao) {
        return posicao;
    }
    
    private boolean naoExisteView(View view) {
        return view == null;
    }

    private boolean existeEmpresa(List<UnidadeCategoriaEmpresa> categorias) {
        return categorias != null;
    }
    
    static class AuxiliaCategoria {
        TextView txNome;
        ImageView iconeCategoria;
    }
    
    

}
