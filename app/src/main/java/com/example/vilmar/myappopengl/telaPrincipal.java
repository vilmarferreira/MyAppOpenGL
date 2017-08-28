package com.example.vilmar.myappopengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

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
       // vrOpengl.glClearColor(1.0f,0.0f,0.0f,0.0f);
        //metodo de limpar a tela
        //preenche todos os picels com a cor selecionada
       // vrOpengl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //Chamado DUrante a criação do aplicativo 1 vez
        //Bom local para inicializar os recursos do programa
        //tempoInicial=System.currentTimeMillis();

    }

    @Override
    //Vai ser chamada quando a superficie mudar
    public void onSurfaceChanged(GL10 vrOpenGL, int largura, int altura) {

        float[] vetCoordenadas= {0.0f, 0.0f, largura, 0.0f, largura/2, altura};

        //Configura a area de visualização utilizada na tela do aparelho
        vrOpenGL.glViewport(0,0,largura,altura);


        //Vai ser chamado sempre que as caracteristicas da superficie mudarem

        //configura a matriz de projeção que define o volume de renderização
        //Criando matriz de projeção
        vrOpenGL.glMatrixMode(GL10.GL_PROJECTION);
        //zerando a matriz (matrix de identidade)
        vrOpenGL.glLoadIdentity();  // carrega a matriz identidade para tirar o lixo da memoria
        //setar volume de renderização
        vrOpenGL.glOrthof(0,largura, 0,altura,1,-1);

        //Setando uma matriz de modelView
        //configura a matriz de cameras e modelo
        vrOpenGL.glMatrixMode(GL10.GL_MODELVIEW);
        vrOpenGL.glLoadIdentity();
        //configura a cor que sera utilizada para limpar o fundo da tela
        vrOpenGL.glClearColor(1.0f,0.0f,1.0f,1.0f);

        FloatBuffer buffer= CriaBuffer(vetCoordenadas);

    }

    @Override
    //Esse metodo é o que vai ser chamado a todo momento
    //ele que vai ser chamado.
    public void onDrawFrame(GL10 vrOpengl) {

        vrOpengl.glClear(GL10.GL_COLOR_BUFFER_BIT);


        /*
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
        */
    }

    public static FloatBuffer CriaBuffer (float[] array)
    {
        //alloc Bytes in memory
        ByteBuffer vrByteBuffer= ByteBuffer.allocateDirect(array.length*4);
        vrByteBuffer.order(ByteOrder.nativeOrder());

        //Create a FloatBuffer
        FloatBuffer vrFloatBuffer= vrByteBuffer.asFloatBuffer();
        vrFloatBuffer.clear();

        //insert a java array into float buffer
        vrFloatBuffer.put(array);
        //reset floatBuffer attribs
        vrFloatBuffer.flip();

        return vrFloatBuffer;
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
