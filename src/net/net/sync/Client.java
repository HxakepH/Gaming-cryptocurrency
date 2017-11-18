package net.net.sync;

import java.net.Socket;

import Core.Block;
import Core.GenesisBlock;


public class Client extends Thread
{
	
    public static void main(String args[])
    {
        try
        {
            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            @SuppressWarnings("resource")
			Socket s = new Socket("localhost", 3128);
            Block newB = new Block();
            newB = GenesisBlock.getGenesis("test");
            
            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт
            s.getOutputStream().write("newBlock".getBytes());
            byte buf[] = new byte[64*1024];
            @SuppressWarnings("unused")
			int r = s.getInputStream().read(buf);
            String data = new String(buf, 0, buf.length);
            if (data == "Go"){
            s.getOutputStream().write(newB.getBytes());
            }
            
            
            // читаем ответ
            r = s.getInputStream().read(buf);
            data = new String(buf, 0, buf.length);
            if(data == "OK"){
            	
            }

            // выводим ответ в консоль
            System.out.println(data);
        }
        catch(Exception e)
        {e.printStackTrace();} // вывод исключений
    }
}