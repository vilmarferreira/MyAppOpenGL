package com.example.vilmar.myappopengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;



class Renderizador implements GLSurfaceView.Renderer
{
    int iFPS;
    long tempoInicial=0;
    long tempoAtual=0;
    @Override
    //será chamado quando o aplicativo for criado, 1 vez só
    public void onSurfaceCreated(GL10 vrOpengl, EGLConfig eglConfig) {
        //configura a cor que será usada para limpar a tela
        vrOpengl.glClearColor(1.0f,0.0f,0.0f,0.0f);
        //metodo de limpar a tela
        //preenche todos os picels com a cor selecionada
        vrOpengl.glClear(GL10.GL_COLOR_BUFFER_BIT);


        //Chamado DUrante a criação do aplicativo 1 vez
        //Bom local para inicializar os recursos do programa
        tempoInicial=System.currentTimeMillis();

    }

    @Override
    //Vai ser chamada quando a superficie mudar
    public void onSurfaceChanged(GL10 gl10, int largura, int altura) {
        //Vai ser chamado sempre que as caracteristicas da superficie mudarem
    }

    @Override
    //Esse metodo é o que vai ser chamado a todo momento
    //ele que vai ser chamado.
    public void onDrawFrame(GL10 vrOpengl) {


        //Calculo de FPS
        tempoAtual= System.currentTimeMillis();
        iFPS++;
        if(tempoAtual-tempoInicial >=1000)
        {
            Log.i("FPS",iFPS+"");
            iFPS=0;
            tempoInicial=System.currentTimeMillis();
        }
        //Metodo automaticamente chamado n vezes por segundo

        //Para piscar a tela, em cada frame, trocar a cor
        vrOpengl.glClearColor((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
        vrOpengl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }
}

public class telaPrincipal extends AppCompatActivity {
    //Cria uma variavel de referencia para a OpenGL
    GLSurfaceView superficieDesenho=null;
    Renderizador render=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Valida a variavel de referencia com uma instancia da superficie
        superficieDesenho=new GLSurfaceView(this);
        render= new Renderizador();
        //Ligando classe
        superficieDesenho.setRenderer(render);
        //Configura a tela do aparelho para mostrar a sup. de desenho
        setContentView(superficieDesenho);
        //IMPRIME UMA MENSAGEM NO LOG COM A TAG FPS E O TEXTO DO 2 PARAMETRO
        Log.i("FPS","Alguma coisa");

    }
}
