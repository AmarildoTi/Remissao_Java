
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Remissao.java
 */

package View;

/**
 * @author Amarildo dos Santos de Lima
 */

import Controller.CarregaBanco;
import Controller.CriaSpool;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Remissao extends JFrame 
{
 private JLabel rotulo1, Contador;                                              // Label
 private JTextField ArqEnt, ArqCodigo, ArqSaida;                                // Caixa de texto
 private JButton botao1, botao2, botao3, botao4;                                // Botão
 private String Caminho, Spool;                                                 // String 
 private int  RetExtesao, Verifica = 0;                                         // Inteiro
 private ImageIcon BtSair, BtBuscar;                                            // Imagems

// Começo metodo loadFrameIcon() Para Carregar Imagens ************************
 private void loadFrameIcon() {
  URL imgUrl = null;
  ImageIcon imgIcon = null;
  imgUrl = Remissao.class.getResource("Icones/MyComp.png");
  imgIcon = new ImageIcon(imgUrl);
  Image img = imgIcon.getImage();
  this.setIconImage(img);
  BtSair  = new ImageIcon(getClass().getResource("Icones/BtSair.png"));
  BtBuscar = new ImageIcon(getClass().getResource("Icones/BtBuscar.png"));
 }
// Final metodo loadFrameIcon() Para Carregar Imagens *************************

 
 public Remissao()
  {
   super("Programa Gerar Remissões Version = 02/07/2015");                      // Nome Titulo do Formulário
   final Container tela = getContentPane();                                     // Conteiner da Tela
   setLayout(null); 
   loadFrameIcon();                                                             // Ativa a Contrutor loadFrame

// Começo Objetos Estrutura e Imprime
    final CarregaBanco   Estrutura   =   new   CarregaBanco();                  // Classe Opção Estrutura do Arquivo
    final CriaSpool      Imprime     =   new   CriaSpool(); 
// Final Objetos Estrutura e Imprime
  
   
        
// Começo Objeto Para Carregar Label ****************************************** Começo Descrição de Ações *********
 rotulo1 = new JLabel("Remissão Geral");                                        // Nome da Label
 rotulo1.setBounds(150, 20, 200, 20);                                           // Posição da Label
 rotulo1.setForeground(Color.blue);                                             // Cor da Fonte da Label
 rotulo1.setFont(new Font("Century Gothic", Font.BOLD + Font.PLAIN, 24));       // Tamanho e Tipo de Fonte da Label
 rotulo1.setVisible(true);                                                      // Visibibildade do Componente no Frame  
 tela.add(rotulo1);                                                             // Adiciona Label na Tela

 rotulo1 = new JLabel("Arquivo de Remissão:");                                  // Nome da Label
 rotulo1.setBounds(10, 60,500, 30);                                             // Posição da Label
 rotulo1.setForeground(Color.black);                                            // Cor da Fonte da Label
 rotulo1.setFont(new Font("Century Gothic", Font.BOLD + Font.PLAIN, 18));       // Tamanho e Tipo de Fonte da Label
 rotulo1.setVisible(true);                                                      // Visibibildade do Componente no Frame  
 tela.add(rotulo1);                                                             // Adiciona Label na Tela

 rotulo1 = new JLabel("Arquivo de Codigo: ");                                   // Nome da Label
 rotulo1.setBounds(10, 95,500, 30);                                             // Posição da Label
 rotulo1.setForeground(Color.black);                                            // Cor da Fonte da Label
 rotulo1.setFont(new Font("Century Gothic", Font.BOLD + Font.PLAIN, 18));       // Tamanho e Tipo de Fonte da Label
 rotulo1.setVisible(true);                                                      // Visibibildade do Componente no Frame  
 tela.add(rotulo1);                                                             // Adiciona Label na Tela

 rotulo1 = new JLabel("Arquivo de Saida:");                                     // Nome da Label
 rotulo1.setBounds(10, 130,500, 30);                                            // Posição da Label
 rotulo1.setForeground(Color.black);                                            // Cor da Fonte da Label
 rotulo1.setFont(new Font("Century Gothic", Font.BOLD + Font.PLAIN, 18));       // Tamanho e Tipo de Fonte da Label
 rotulo1.setVisible(true);                                                      // Label Data De Postagem Fac
 tela.add(rotulo1);                                                             // Adiciona Label na Tela
// Final Objeto Para Carregar Label ******************************************* Final Descrição de Ações *********
 
// Começo Objeto Para Carregar Caixa de Texto ********************************* Começo Descrição de Ações *********
 ArqEnt = new JTextField("Arquivo de Remissão ");                               // Nome da Caixa de Texto
 ArqEnt.setBounds(215, 65, 175, 25);                                            // Posição da Caixa de Texto
 ArqEnt.setBackground(Color.white);                                             // Cor de Dentro da Caixa de Texto
 ArqEnt.setFont(new Font("Century Gothic", Font.BOLD + Font.ITALIC , 15));      // Fonte e Tamanho de Fonte do Texto Digitado Dentro da Caixa de Texto
 ArqEnt.setHorizontalAlignment(JTextField.LEFT);                                // Posição do Texto Digidado Dentro da Caixa de Texto
 ArqEnt.setVisible(true);                                                       // Visibibildade do Componente no Frame  
 tela.add(ArqEnt);                                                              // Adiciona Caixa de Texto na tela

 ArqCodigo = new JTextField("Arquivo de Codigo ");                              // Nome da Caixa de Texto
 ArqCodigo.setBounds(215, 100, 175, 25);                                        // Posição da Caixa de Texto
 ArqCodigo.setBackground(Color.white);                                          // Cor de Dentro ca Caixa de Texto
 ArqCodigo.setFont(new Font("Century Gothic", Font.BOLD + Font.ITALIC , 15));   // Fonte e Tamanho de Fonte do Texto Digitado Dentro da Caixa de Texto
 ArqCodigo.setHorizontalAlignment(JTextField.LEFT);                             // Posição do Texto Digidado Dentro da Caixa de Texto
 ArqCodigo.setVisible(true);                                                    // Visibibildade do Componente no Frame
 tela.add(ArqCodigo);                                                           // Adiciona Caixa de Texto na tela
 
 ArqSaida = new JTextField("Arquivo de Saida ");                                // Nome da Caixa de Texto
 ArqSaida.setBounds(215, 135, 175, 25);                                         // Posição da Caixa de Texto
 ArqSaida.setBackground(Color.white);                                           // Cor de Dentro ca Caixa de Texto
 ArqSaida.setFont(new Font("Century Gothic", Font.BOLD + Font.ITALIC , 15));    // Fonte e Tamanho de Fonte do Texto Digitado Dentro da Caixa de Texto
 ArqSaida.setHorizontalAlignment(JTextField.LEFT);                              // Posição do Texto Digidado Dentro da Caixa de Texto
 ArqSaida.setVisible(true);                                                     // Visibibildade do Componente no Frame
 tela.add(ArqSaida);                                                            // Adiciona Caixa de Texto na tela
// Final Objeto Para Carregar Caixa de Texto ********************************** Final Descrição de Ações *********

// Começo Objeto Para Carregar os botões ************************************** Começo Descrição de Ações *********

//******* Botão Carrega Arquivo de Remissao Começo
 botao1 = new JButton("",BtBuscar);                                             // Nome do Botão
 botao1.setBounds(395, 60, 80, 30);                                             // Posição do Botão
 botao1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));    // Retira a Borda
 botao1.setFont(new Font("Century Gothic", Font.BOLD + Font.ITALIC, 14));       // Fonte e Tamanho de fonte do Botão
 botao1.setHorizontalAlignment(JButton.CENTER);                                 // Centraliza a Imagem e o Texto do Botão
 botao1.setMnemonic(KeyEvent.VK_ENTER);                                         // Evento Enter do Botão
 botao1.setVisible(true);                                                       // Visibibildade do Componente no Frame  
 tela.add(botao1);                                                              // Adiciona Botão a Tela
 botao1.addFocusListener(new FocusAdapter(){
  @Override //public void focusLost(FocusEvent e) {                             // FocusLost Sinaliza a Perda de Foco  // focusGained Sinaliza o Ganho do Foco
   public void focusGained(FocusEvent e){
    //botao1.requestFocus();
    getRootPane().setDefaultButton(botao1);
    // System.out.println("O Foco Está botao1");
   }
 });
    
 botao1.addActionListener(new ActionListener(){                                 // Evento Click Para Busca de Arquivo
   public void actionPerformed(ActionEvent Clik){
   JFileChooser fileChooser = new JFileChooser("C:/Amarildo/Remissao");         // Força a Abrir em uma Pasta Determinada
   fileChooser.setMultiSelectionEnabled(true);
   fileChooser.showOpenDialog(tela);
   File[] Arquivo_Remissao = fileChooser.getSelectedFiles();
 
   for (int X = 0 ; X < Arquivo_Remissao.length;X++){
    //System.out.print(" "+Arquivo_Remissao[X].getName());
    ArqEnt.setText(Arquivo_Remissao[X].getName().toUpperCase());                // Repassa Somente o Nome do arquivo Selecionado no Chooser Para Caixa de Texto Arquivo de Entrada
    RetExtesao = Arquivo_Remissao[X].getName().length()- 4;                     // Captura a Quatidade de Caracter de Um Arquivo Para Poder Retirar a Extensão
    ArqSaida.setText(Arquivo_Remissao[X].getName().toUpperCase().substring(0,RetExtesao)); 
    Spool = ArqEnt.getText().substring(0,RetExtesao);
   }
 
if (Arquivo_Remissao != null){
     Estrutura.ZapRemissao();                                                   // Limpa os Bancos Antes de Carregar
     Estrutura.AppendRemissao(Arquivo_Remissao);                                // Carrega O Banco Remissao Com os Dados de Remissão
     } else {
     JOptionPane.showMessageDialog(null, "            Não Foi Selecionado Nenhum Arquivo remissão "+"\n"+"Por Favor Click em Abrir Para Selecionar o Arquivo "+"\n"+"                             a Ser Pesquisado", "                   Mensagem de Erro Verificar", JOptionPane.ERROR_MESSAGE) ;
    }
   }
});
//******* Botão Carrega Arquivo de Remissao Final


//******* Botão Carrega Arquivo de Codigo Começo
 botao2 = new JButton("",BtBuscar);                                             // Nome do Botão
 botao2.setBounds(395, 95, 80, 30);                                             // Posição do Botão
 botao2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));    // Retira a Borda
 botao2.setFont(new Font("Century Gothic", Font.BOLD + Font.ITALIC, 14));       // Fonte e Tamanho de fonte do Botão
 botao2.setHorizontalAlignment(JButton.CENTER);                                 // Centraliza a Imagem e o Texto do Botão
 botao2.setMnemonic(KeyEvent.VK_ENTER);                                         // Evento Enter do Botão
 botao2.setVisible(true);                                                       // Visibibildade do Componente no Frame  
 tela.add(botao2);                                                              // Adiciona Botão a Tela
 botao2.addFocusListener(new FocusAdapter(){
  @Override //public void focusLost(FocusEvent e) {                             // FocusLost Sinaliza a Perda de Foco  // focusGained Sinaliza o Ganho do Foco
   public void focusGained(FocusEvent e){
    //botao1.requestFocus();
    getRootPane().setDefaultButton(botao2);
    // System.out.println("O Foco Está botao1");
   }
 });
    
 botao2.addActionListener(new ActionListener(){                                 // Evento Click Para Busca de Arquivo
   public void actionPerformed(ActionEvent Clik){
   JFileChooser fileChooser = new JFileChooser("C:/Amarildo/Remissao");         // Força a Abrir em uma Pasta Determinada
   fileChooser.showOpenDialog(tela);                                            // Abri Chooser na Tela
   File Arquivo_Codigo = fileChooser.getSelectedFile();                         // Grava Arquivo Selecionado
   
if (Arquivo_Codigo != null){
     Estrutura.ZapCodigo();                                                     // Limpa os Bancos Antes de Carregar
     Estrutura.AppendCodigos(Arquivo_Codigo);                                   // Carrega O Banco Codigo Com os Dados de Codigo
     Caminho = Arquivo_Codigo.getPath();                                        // Captura Caminho e Nome do Arquivo Selecionado
     ArqCodigo.setText(Caminho.toUpperCase());                                  // Repassa Caminho e o Nome do arquivo Selecionado no Chooser Para Caixa de Texto Arquivo de Entrada
     ArqCodigo.setText(Arquivo_Codigo.getName().toUpperCase());                 // Repassa Somente o Nome do arquivo Selecionado no Chooser Para Caixa de Texto Arquivo de Entrada
     RetExtesao = Arquivo_Codigo.getName().length()- 4;                         // Captura a Quatidade de Caracter de Um Arquivo Para Poder Retirar a Extensão
     ArqCodigo.setText(Arquivo_Codigo.getName().toUpperCase().substring(0,RetExtesao)); 
     //Spool = ArqEnt.getText().substring(0,RetExtesao);
    } else {
     JOptionPane.showMessageDialog(null, "            Não Foi Selecionado Nenhum Arquivo de Cifs "+"\n"+"Por Favor Click em Abrir Para Selecionar o Arquivo "+"\n"+"                             a Ser Pesquisado", "                   Mensagem de Erro Verificar", JOptionPane.ERROR_MESSAGE) ;
    }
   }
});
//******* Botão Carrega Arquivo de Remissao Final


//******* Botão Processar Começo
 botao3 = new JButton("Processar");                                             // Nome do Botão
 botao3.setBounds(395, 128, 80, 30);                                            // Posição do Botão
 botao3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));    // Retira a Borda
 botao3.setFont(new Font("Century Gothic", Font.BOLD + Font.ITALIC, 14));       // Fonte e Tamanho de fonte do Botão
 botao3.setHorizontalAlignment(JButton.CENTER);                                 // Centraliza a Imagem e o Texto do Botão
 botao3.setMnemonic(KeyEvent.VK_ENTER);                                         // Evento Enter do Botão
 botao3.setVisible(true);                                                       // Visibibildade do Componente no Frame  
 botao3.addFocusListener(new FocusAdapter(){
     @Override //public void focusLost(FocusEvent e) {                          // FocusLost Sinaliza a Perda de Foco  // focusGained Sinaliza o Ganho do Foco
   public void focusGained(FocusEvent e){
    botao3.requestFocus();
    getRootPane().setDefaultButton(botao3);
    // System.out.println("O Foco Está botao1");
   }
 });
 
 tela.add(botao3);                                                              // Adiciona Botão a Tela
 botao3.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent Clik){
     try {
       Imprime.ImprimeSpool(Spool);
      } catch (SQLException ex) {
       Logger.getLogger(Remissao.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
});
//******* Botão Processar Final


//******* Botão Sair Começo
 botao4 = new JButton("",BtSair);                                               // Nome do Botão
 botao4.setBounds(215, 190,67, 30);                                             // Posição do Botão
 botao4.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));    // Retira a Borda
 botao4.setFont(new Font("Century Gothic", Font.BOLD + Font.ITALIC, 14));       // Fonte e Tamanho de fonte do Botão
 botao4.setHorizontalAlignment(JButton.CENTER);                                 // Centraliza a Imagem e o Texto do Botão
 botao4.setMnemonic(KeyEvent.VK_ENTER);                                         // Evento Enter do Botão
 botao4.setVisible(true);                                                       // Visibibildade do Componente no Frame  
 botao4.addFocusListener(new FocusAdapter(){
     @Override //public void focusLost(FocusEvent e) {                          // FocusLost Sinaliza a Perda de Foco  // focusGained Sinaliza o Ganho do Foco
   public void focusGained(FocusEvent e){
    botao4.requestFocus();
    getRootPane().setDefaultButton(botao4);
    // System.out.println("O Foco Está botao1");
   }
 });
 
 tela.add(botao4);                                                              // Adiciona Botão a Tela
 botao4.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent Clik){
  System.exit(0);                                                               // Está Função Da a Ação Sair Para o Botão
  }
});
//******* Botão Sair Final

// Final Objeto Para Carregar os botões *************************************** Final Descrição de Ações *********
     
// Começo Objeto Para Carregar Tela ******************************************* Começo Descrição de Ações *********
//tela.setBackground(Color.black);                                              // Tela cores padrão
  tela.setBackground(new Color(230, 230, 230));                                 // Telas cores Personalizadas
  setSize(500, 270);                                                            // Tamanho da Tela
  setVisible(true);                                                             // Visibilidada da Tela no DeskTop
  setLocationRelativeTo(null);                                                  // Posiçao da Tela no DeskTop Sendo Null Aparecera no Centro do DeskTop
  //setExtendedState(ICONIFIED);                                                // Para Exibir Janela Minimizada
  //setExtendedState(MAXIMIZED_BOTH);                                           // Para Exibir Janela Maximizada
  setResizable(false);                                                          // Para Desabilitar o Botão de Maximizar
  System.gc();                                                                  // Coletor de Lixo em Memoria
 
 
 }
// Final Objeto Para Carregar Tela ******************************************** Final Descrição de Ações *********
    
// Começo Metodo Principal Tela ************************************************
public static void main(String args[]){
  Remissao app = new Remissao();
  app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
// Final Metodo Principal Tela *************************************************
}
