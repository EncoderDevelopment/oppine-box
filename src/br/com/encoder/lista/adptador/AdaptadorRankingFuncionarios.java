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
import br.com.encoder.unidade.UnidadeFncionarioRanking;
import br.com.oppinebox.R;

public class AdaptadorRankingFuncionarios extends ArrayAdapter<UnidadeFncionarioRanking>{
    
    private List<UnidadeFncionarioRanking> funcionarios;
    
    public AdaptadorRankingFuncionarios(Context context, List<UnidadeFncionarioRanking> funcionarios){
        super(context, R.layout.lista_layout);
        this.funcionarios = funcionarios;
    }

    @Override
	public int getCount() {
        return funcionarios.size();
    }

    @Override
	public UnidadeFncionarioRanking getItem(int posicao) {
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
                view = inflater.inflate(R.layout.item_ranking_funcionario, viewGroup, false);
                af = new AuxiliaFuncionario();
                
                af.fotoFuncionario = (ImageView) view.findViewById(R.id.fotoFuncionarioRanking);
                af.txNome = (TextView) view.findViewById(R.id.txNomeRanking);
                af.txPorcentagem = (TextView) view.findViewById(R.id.txPorcentagemRanking);
                af.txAvaliacoes = (TextView) view.findViewById(R.id.txAvaliacoes);
                
                
                Typeface font = Typeface.createFromAsset(getContext().getAssets(), ConfiguracaoFontCaminho.FONT_SENSATION_NEGRITO);
                af.txNome.setTypeface(font);
                af.txAvaliacoes.setTypeface(font);
                af.txPorcentagem.setTypeface(font);
                view.setTag(af);
                
           }else{
                   af = (AuxiliaFuncionario) view.getTag();
           }

            UnidadeFncionarioRanking fncionario = funcionarios.get(posicao);
        
        if(existeFncionario(fncionario)){
        	
        	af = (AuxiliaFuncionario) view.getTag();   
        	 if (fncionario.getFotoFuncionario() != null) {
            	 af.fotoFuncionario.setImageBitmap(fncionario.getFotoFuncionario());
			} 
            
            af.txNome.setText(fncionario.getFunc_nome());
            af.txAvaliacoes.setText("Avaliações");
            af.txPorcentagem.setText(Float.toString(fncionario.getPorcentagem()) + "%");
        }
        return view;
    }

    
    private boolean naoExisteView(View view) {
        return view == null;
    }

    private boolean existeFncionario(UnidadeFncionarioRanking fncionario) {
        return fncionario != null;
    }
    
    
//AuxiliaFuncionario que evita criar os textView para todos os itens da lista, por isso a otimização
    static class AuxiliaFuncionario{
    	ImageView fotoFuncionario;
    	TextView txNome;
    	TextView txAvaliacoes;
    	TextView txPorcentagem;
    	
    }
    
}