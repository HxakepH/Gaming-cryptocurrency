package Core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

//Класс блока(составляющего блокчейн)
public class Block {
	   private int index;           //Индекс блока
       private String previousHash; //Хеш предведущего блока
       private String timestamp;    //Время создания блока
       private String data;         //Данные блока
       private String hash;         //Хеш блока
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public byte[] getBytes(){
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String in = String.valueOf(index);
		String indexS = in+"|";
		String pHashS = getPreviousHash()+"|";
		String timeSS = getTimestamp()+"|";
		String dataS = getData()+"|";
		String hashS = getHash()+"|";
		
		byte[] indexB = indexS.getBytes();
		byte[] pHashB = pHashS.getBytes();
		byte[] timeSB = timeSS.getBytes();
		byte[] dataB = dataS.getBytes();
		byte[] hashB = hashS.getBytes();
		
		
        try{
            baos.write(indexB);
            baos.write(pHashB);
            baos.write(timeSB);
            baos.write(dataB);
            baos.write(hashB);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
		return baos.toByteArray();
	}
	public static Block getBlock(byte[] byt){
		Block block = new Block();
		ByteArrayInputStream bais = new ByteArrayInputStream(byt);
		String[] stra = new String[1000]; 
		int b;
		int i = 1;
		while((b=bais.read())!=-1){
			stra[i] = Character.toString((char)b);
			i++;
		}
		String[] str = new String[1000];
		String copy = new String();
		int ii = 1;
		int isi = 1;
		String sss;
        do{
        	copy = copy + stra[ii];
        	sss = stra[ii];
        	if (isi<=5){
        	if(sss.equalsIgnoreCase("|")){
        		copy = copy.replaceAll("|", "");
        		str[isi]= copy;
        		copy ="";
        		
        		isi++;
        	}
        	}
        	ii++;
        }while(ii != stra.length);
        block.index = Integer.parseInt(str[1].replace("|", ""));
        block.previousHash = str[2];
        block.timestamp = str[3];
        block.data = str[4];
        block.hash = str[5];
		return block;
	}
}
