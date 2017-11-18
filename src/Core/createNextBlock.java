package Core;
//Генератор новых блоков
import Core.Hashing;

import java.util.Date;

import Core.Block;
public class createNextBlock {
	//Функция создания блока
	public static Block getHash(Block bchain, String data){
	 int nextIndex = bchain.getIndex() + 1;
	 @SuppressWarnings("deprecation")
	String nextTimestamp = new Date().toGMTString();
	 String nextHash = Hashing.CryptSHA256(nextIndex, bchain.getHash(), nextTimestamp, data);
	 Block nextBlock = new Block();
	 nextBlock.setIndex(nextIndex);
	 nextBlock.setPreviousHash(bchain.getHash());
	 nextBlock.setTimestamp(nextTimestamp);
	 nextBlock.setData(data);
	 nextBlock.setHash(nextHash);
	 return nextBlock;
	}

}