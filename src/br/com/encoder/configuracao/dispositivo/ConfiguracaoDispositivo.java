package br.com.encoder.configuracao.dispositivo;


import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConfiguracaoDispositivo {
	
	/**
	 * CGPoint recebe a configura��o da resolu��o do aparelho
	 * @param cgPoint
	 * @return
	 */
	public static CGPoint resolucao(CGPoint cgPoint) {
		return cgPoint;
	}

	/**
	 * Captura a largura da tela do dispositivo
	 * @return
	 */
	public static float larguraDaCena() {
		return tamanho().width;
	}

	/**
	 * Captura a altura da tela do dispositivo
	 * @return
	 */
	public static float alturaDaCena() {
		return tamanho().height;
	}

	/**
	 * Retorna os parametros de configura��o da largura e altura do dispositivo
	 * @return
	 */
	public static CGSize tamanho() {
		return CCDirector.sharedDirector().winSize();
	}
	
	
	public static boolean existeConexao(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)
         context.getSystemService(Context.CONNECTIVITY_SERVICE);
          if (connectivity != null) 
          {
              NetworkInfo netInfo = connectivity.getActiveNetworkInfo();
              
              // Se n�o existe nenhum tipo de conex�o retorna false
              if (netInfo == null) {
                return false;
              }
              
              int netType = netInfo.getType();

              // Verifica se a conex�o � do tipo WiFi ou Mobile e 
              // retorna true se estiver conectado ou false em
              // caso contr�rio
              if (netType == ConnectivityManager.TYPE_WIFI || 
                    netType == ConnectivityManager.TYPE_MOBILE) {
                  return netInfo.isConnected();

              } else {
            	  
                  return false;
              }
          }else{
            return false;
          }
    }
	
}
