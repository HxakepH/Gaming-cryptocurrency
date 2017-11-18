package Core;
import Core.Block;
//Генезис блок или основа всех блоков
public class GenesisBlock {
	public static Block getGenesis(String data){
	Block bchain = new Block();
	bchain.setIndex(1);
	bchain.setPreviousHash("0");
	bchain.setTimestamp("14:48 29.08.2017");
	bchain.setData(data);
	bchain.setHash("2f61d13a60fee470f3e2cb4cddc3de3b518826e96ce5e2ab9f89f4e5507c18ab");
	return bchain;
	}
}
