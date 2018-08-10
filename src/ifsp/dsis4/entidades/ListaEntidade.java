/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsis4.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denis
 */
//Representa uma tabela do BD
public class ListaEntidade {
    
    //Completar aqui
    private String[] colunas;
    private List<Object[]> linhas;

    public ListaEntidade(String[] colunas){
        this.colunas=colunas;
        linhas = new ArrayList<>();
    }

    public void addLinha(Object[] linha){
        linhas.add(linha);
    }

    public String[] getColunas(){
        return colunas;
    }

    public Object[][] toArray2d() {
        Object[][] matriz = new Object[linhas.size()][colunas.length];

                for(int i = 0; i < linhas.size(); i++){
                    for(int j = 0; j < colunas.length; j++){
                        System.arraycopy(linhas.get(i), 0, matriz[i], 0, colunas.length);
                    }
                }
                return matriz;
    }
}
