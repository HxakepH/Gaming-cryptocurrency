package Core;
//Проверка на валидность
import Core.Block;
import Core.Hashing;
public class validation {
    public static boolean valid(Block block, Block backBlock){
		String nextHash = Hashing.CryptSHA256(backBlock.getIndex()+1, backBlock.getHash(), block.getTimestamp(), "DorCoin_Blockhain");
    	if (backBlock.getIndex()+1 != block.getIndex()){
    		return true;
    	}else if (backBlock.getHash() != block.getPreviousHash()){
    		return true;
    	}else if (nextHash != block.getHash()){
    		return true;
    	}else if (nextHash != block.getHash()){
    		return true;
    	}else{
    	return false;
    	}
    }
}
