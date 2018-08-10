/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsis4.bd;

import ifsp.dsis4.entidades.ListaEntidade;

import java.sql.*;

/**
 *
 * @author denis
 */
public class EntidadeBD {
    
    public ListaEntidade buscarEntidades(String nomeTabela) {
        String sql = String.format("select * from %s", nomeTabela);
        ConexaoBD conexao = ConexaoBD.getInstance();
        ListaEntidade lista = null;
        try (
            Connection con = conexao.getConnection();
            PreparedStatement pStat = con.prepareStatement(sql);
            ResultSet rs = pStat.executeQuery())
        {
            //verifica se tabela existe
            DatabaseMetaData dmd = con.getMetaData();
            ;
              try (ResultSet rs1 = dmd.getTables(null,null, nomeTabela.toUpperCase(), new String[]{"table"})){
                  if(rs1.next()){
                      ResultSetMetaData rsmd = pStat.getMetaData();
                      String[] colunas = getColunas(rsmd);
                      lista = new ListaEntidade(colunas);
                      while(rs.next()){
                          Object[] linha = new Object[colunas.length];
                          for(int i = 0; i < colunas.length; i++){
                              linha[i]=rs.getObject(i+1);
                          }
                          lista.addLinha(linha);
                  }
              }else{
                      System.out.println("else");
                      System.out.println(nomeTabela);
                  }


            }
        }
        catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
        return lista;
    }

    private String[] getColunas(ResultSetMetaData rsmd) throws SQLException{
        int qtdColunas = rsmd.getColumnCount();
        String[] colunas =  new String[qtdColunas];

        for(int i = 0; i < qtdColunas; i++){
            colunas[i] = rsmd.getColumnName(i+1);
        }
        return colunas;
    }
    
    //Para testes
    public void salvarEntidades() {
        String sql = "insert into Entidade (codigo, titulo) values(?,?)";
        ConexaoBD conexao = ConexaoBD.getInstance();
        try (
            Connection con = conexao.getConnection();
            PreparedStatement pStat = con.prepareStatement(sql)) 
        {
            for(int i = 0; i < 200; i++) {
                pStat.setObject(1, i+1);
                pStat.setObject(2, "titulo "+(i+1));
                pStat.executeUpdate();
            }
        }
        catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
}
