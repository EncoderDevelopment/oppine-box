package br.com.encoder.lista.adptador;


import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.unidade.UnidadeEmpresa;
import br.com.oppinebox.R;

public class AdaptadorListaEmpresas extends ArrayAdapter<UnidadeEmpresa>{
    
    private List<UnidadeEmpresa> empresas;
    private ImageLoader imageLoader;
    
    public AdaptadorListaEmpresas(Context context, List<UnidadeEmpresa> empresas, ImageLoader imageLoader){
        super(context, R.layout.lista_layout);
        this.empresas = empresas;
        this.imageLoader = imageLoader;
    }

    @Override
	public int getCount() {
        return empresas.size();
    }

    @Override
	public UnidadeEmpresa getItem(int posicao) {
        return empresas.get(posicao);
    }

    @Override
	public long getItemId(int posicao) {
        return posicao;
    }
 

    @Override
    public View getView(int posicao, View convertView, ViewGroup viewGroup) {
        
        View view = convertView;
        AuxiliaEmpresa ap;
               
            if(naoExisteView(view)){
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_lista_empresas, viewGroup, false);
                ap = new AuxiliaEmpresa();
                
                              
                ap.iconeEmpresa = (ImageView) view.findViewById(R.id.iconeEmpresa);
                ap.txNome = (TextView) view.findViewById(R.id.txNome);
                
                
                Typeface font = Typeface.createFromAsset(getContext().getAssets(), ConfiguracaoFontCaminho.FONT_SENSATION_NEGRITO);
                ap.txNome.setTypeface(font);
                view.setTag(ap);
                
           }else{
                   ap = (AuxiliaEmpresa) view.getTag();
           }

        UnidadeEmpresa empresa = empresas.get(posicao);
        
        if(existeEmpresa(empresa)){
            ap = (AuxiliaEmpresa) view.getTag();
            
            if (empresa.getIconeEmpresa() != null) {
            	 ap.iconeEmpresa.setImageBitmap(empresa.getIconeEmpresa());
				
			} 
           	
            ap.txNome.setText(empresa.getEmpr_nome());
        }
        
        
        imageLoader.displayImage(empresa.getEmpr_logomarca(), ap.iconeEmpresa, null, new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub
				
			}
		}, new ImageLoadingProgressListener(){

			@Override
			public void onProgressUpdate(String uri, View view, int current, int total) {
				// TODO Auto-generated method stub
				
			}} );
        
        return view;
    }

    
    private boolean naoExisteView(View view) {
        return view == null;
    }

    private boolean existeEmpresa(UnidadeEmpresa empresa) {
        return empresa != null;
    }
    
    
    
    
//ViewHolder que evita criar os textView para todos os itens da lista, por isso a otimização
    static class AuxiliaEmpresa{
        ImageView iconeEmpresa;
    	TextView txNome;
        
    }
}