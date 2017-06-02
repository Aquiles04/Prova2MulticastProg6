/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova2multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandre.chaves
 */
public class Processo extends Thread {
      
    private int rg;
    //mensagens se fossem boolean mais facil?
    private String aya = "Are you alive?";
    private String iaa = "I am alive";
    private String wel = "Welcome";
    private String join = "Join";
    private String group = "224.0.0.1";
    private MulticastSocket mSocket;
    private DatagramSocket socket;
    private Random rand = new Random();
    private int opcao;
    private byte[] receive = new byte[1024];
    //private byte[] send = new byte[1024];
    private int portaProcesso;
    private int portaGrupo = 7777;
    private ArrayList processos;

    public Processo() throws SocketException, IOException {
        

        //int espera = rand.nextInt((30 - 5 + 1)+5);
        this.rg = rand.nextInt(1000);
        this.mSocket = new MulticastSocket(portaGrupo);
        this.socket = new DatagramSocket();
        this.processos = new ArrayList();


    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

  

    public String getAya() {
        return aya;
    }

    public void setAya(String aya) {
        this.aya = aya;
    }

    public String getIaa() {
        return iaa;
    }

    public void setIaa(String iaa) {
        this.iaa = iaa;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public MulticastSocket getmSocket() {
        return mSocket;
    }

    public void setmSocket(MulticastSocket mSocket) {
        this.mSocket = mSocket;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public int getOpcao() {
        return opcao;
    }

    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    public byte[] getReceive() {
        return receive;
    }

    public void setReceive(byte[] receive) {
        this.receive = receive;
    }

    public int getPortaProcesso() {
        return portaProcesso;
    }

    public void setPortaCoordenador(int portaProcesso) {
        this.portaProcesso = portaProcesso;
    }

    public int getPortaGrupo() {
        return portaGrupo;
    }

    public void setPortaGrupo(int portaGrupo) {
        this.portaGrupo = portaGrupo;
    }

    public String getWel() {
        return wel;
    }

    public void setWel(String wel) {
        this.wel = wel;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public ArrayList getProcessos() {
        return processos;
    }

    public void setProcessos(ArrayList processos) {
        this.processos = processos;
    }
    
    
    

    // Referencia ? http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html
    @Override
    public void run() {

        //criar socket
        // criar multicast socket entrar no grupo
        try {
            
            Processo p = new Processo();
            System.out.println("Criei Processo");
            System.out.println("Minha rg eh: " + p.getRg());

            InetAddress iGroup = InetAddress.getByName(p.group);
            p.mSocket.joinGroup(iGroup);
            p.getProcessos().add(p);
            
            System.out.println("Entrei no grupo");

            //Exercicio2
            DatagramPacket sendPacket = new DatagramPacket(p.join.getBytes(), p.join.length(), iGroup, p.portaGrupo);
            DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
            
            //Exercicio2
            p.mSocket.send(sendPacket);
            
            while (true) {

                // fazer um if para cada situacao setar um case = x que eh a opcao da thread
               
                System.out.println("No loop");
                sleep(5000);
                
                DatagramPacket sendPacket2 = new DatagramPacket(p.wel.getBytes(), p.wel.length(), iGroup, p.portaGrupo);
                p.mSocket.send(sendPacket2);

                //DatagramPacket recebe2 = new DatagramPacket(receive, receive.length);
                //mSocket.receive(recebe2);

//                p.mSocket.receive(receivePacket);
//                receive = receivePacket.getData();

                //not working
                if (receivePacket.equals("JOIN"))
                {
                    sendPacket = new DatagramPacket(wel.getBytes(), wel.length(),receivePacket.getAddress() , receivePacket.getPort());
                    p.socket.send(sendPacket);
                    System.out.println("O maluco de rg " + p.getRg() + " me enviou " + sendPacket);
                    p.getProcessos().add(p);

                }
                if(receivePacket.equals("AYA") == false)
                {
                  //remove processo do fucking array
                  //p.getProcessos().remove();
                }
               
//                
//                switch (opcao) {
//                    case 1:
//                        break;
//                    case 2:
//                        break;
//
//                    default:
//
//                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
