/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 12/12/17
* Ultima alteracao: 14/03/18
* Nome: Painel
* Funcao: Classe usada para colocar os componentes de Interface
***********************************************************************/

package view;

import javafx.scene.layout.AnchorPane;
import img.Imagem;
import model.MeioDeComunicacao;
import view.componentes.*;
import model.camadas.Temporizador;
import util.Alerta;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.application.Platform;


public class Painel extends AnchorPane {

  //Objeto que armazenas todas as imagens do programa
  public static Imagem allImage = new Imagem();

  public static Grafico GRAFICO;//Grafico da Onda

  public static Configuracao CONFIGURACOES;//Configuracoes para escolha dos algoritmos

  public static Computador COMPUTADOR_TRANSMISSOR;//Computador Transmissor
  public static Computador COMPUTADOR_RECEPTOR;//Computador Receptor

  public static MeioDeComunicacao MEIO_DE_COMUNICACAO;//Objeto que representa o Meio de Comunicacao

  public static PainelTemporizador TEMPORIZADORES;//Painel onde mostra os Temporizadores

  private static ImageView IMAGEM_ERRO1;// = allImage.getImageView("erro");
  private static ImageView IMAGEM_ERRO2;// = allImage.getImageView("erro");

  /*********************************************
  * Metodo: Painel - Construtor
  * Funcao: Cria os componentes para a interface do programa
  * Parametros: void
  * Retorno: void
  *********************************************/
  public Painel() {
    this.construirInterface();//Inicializa os Componentes de Interface
  }//Fim do Construtor


  /*********************************************
  * Metodo: Construir Interface
  * Funcao: Iniciarliza os Componentes presente no Painel
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void construirInterface() {
    COMPUTADOR_TRANSMISSOR = new Computador(Computador.TRANSMISSOR,10,20);
    this.getChildren().add(COMPUTADOR_TRANSMISSOR);//Adicionando na Interface

    COMPUTADOR_RECEPTOR = new Computador(Computador.RECEPTOR,790,20);
    this.getChildren().add(COMPUTADOR_RECEPTOR);//Adicionando na Interface

    MEIO_DE_COMUNICACAO = new MeioDeComunicacao(COMPUTADOR_TRANSMISSOR,COMPUTADOR_RECEPTOR);

    TEMPORIZADORES = new PainelTemporizador(450,20);
    this.getChildren().add(TEMPORIZADORES);//Adicionando na Interface

    GRAFICO = new Grafico();
    GRAFICO.setPosicao(413,445);
    this.getChildren().add(GRAFICO);//Adicionando na Interface

    CONFIGURACOES = new Configuracao(440,250);
    this.getChildren().add(CONFIGURACOES);//Adicionando na Interface

    IMAGEM_ERRO1 = allImage.getImageView("erro");
    IMAGEM_ERRO1.setPreserveRatio(true);
    IMAGEM_ERRO1.setFitHeight(60);
    IMAGEM_ERRO1.setLayoutX(425);
    IMAGEM_ERRO1.setLayoutY(370);
    IMAGEM_ERRO1.setVisible(false);

    IMAGEM_ERRO2 = allImage.getImageView("erro");
    IMAGEM_ERRO2.setPreserveRatio(true);
    IMAGEM_ERRO2.setFitHeight(60);
    IMAGEM_ERRO2.setLayoutX(755);
    IMAGEM_ERRO2.setLayoutY(370);
    IMAGEM_ERRO2.setVisible(false);


    this.getChildren().addAll(IMAGEM_ERRO1, IMAGEM_ERRO2);

  }//Fim construirInterface

  /*********************************************
  * Metodo: erroNoQuadro
  * Funcao: Mostra a Imagem de Erro na Interface
  * Parametros: computador : Computador
  * Retorno: void
  *********************************************/
  public static void erroNoQuadro(Computador computador) {
    try {

      if (computador == COMPUTADOR_TRANSMISSOR) {
        IMAGEM_ERRO1.setVisible(true);
        Thread.sleep(1000);
        IMAGEM_ERRO1.setVisible(false);
      } else if (computador == COMPUTADOR_RECEPTOR) {
        IMAGEM_ERRO2.setVisible(true);
        Thread.sleep(1000);
        IMAGEM_ERRO2.setVisible(false);
      }

    } catch (Exception e) {

    }
  }

}//Fim class