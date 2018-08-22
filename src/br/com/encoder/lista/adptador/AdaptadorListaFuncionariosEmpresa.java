package br.com.encoder.lista.adptador;


import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.unidade.UnidadeFncionario;
import br.com.oppinebox.R;

public class AdaptadorListaFuncionariosEmpresa extends ArrayAdapter<UnidadeFncionario>{
    
    private List<UnidadeFncionario> funcionarios;
    
    public AdaptadorListaFuncionariosEmpresa(Context context, List<UnidadeFncionario> funcionarios){
        super(context, R.layout.lista_layout);
        this.funcionarios = funcionarios;
    }

    @Override
	public int getCount() {
        return funcionarios.size();
    }

    @Override
	public UnidadeFncionario getItem(int posicao) {
        return funcionarios.get(posicao);
    }

    @Override
	public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup viewGroup) {
        
        View view = convertView;
        AuxiliaFuncionario af;
               
            if(naoExisteView(view)){
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_lista_funcionario, viewGroup, false);
                af = new AuxiliaFuncionario();
                
                af.fotoFuncionario = (ImageView) view.findViewById(R.id.fotoFuncionario);
                af.txNome = (TextView) view.findViewById(R.id.txNome);
                //af.txCargo = (TextView) view.findViewById(R.id.txCargo);
                
                
                Typeface font = Typeface.createFromAsset(getContext().getAssets(), ConfiguracaoFontCaminho.FONT_SENSATION_NEGRITO);
                af.txNome.setTypeface(font);
                view.setTag(af);
                
           }else{
                   af = (AuxiliaFuncionario) view.getTag();
           }

            UnidadeFncionario fncionario = funcionarios.get(posicao);
        
        if(existeFncionario(fncionario)){
        	
        	af = (AuxiliaFuncionario) view.getTag();   
        	 if (fncionario.getFotoFuncionario() != null) {
            	 af.fotoFuncionario.setImageBitmap(fncionario.getFotoFuncionario());
				
			} 
            
            af.txNome.setText(fncionario.getNome());
           // af.txCargo.setText(fncionario.getCargo());
        }
        return view;
    }

    
    private boolean naoExisteView(View view) {
        return view == null;
    }

    private boolean existeFncionario(UnidadeFncionario funcionario) {
        return funcionario != null;
    }
    
    
//ViewHolder que evita criar os textView para todos os itens da lista, por isso a otimização
    static class AuxiliaFuncionario{
    	ImageView fotoFuncionario;
    	TextView txNome;
    	//TextView txCargo;
    }
    
}