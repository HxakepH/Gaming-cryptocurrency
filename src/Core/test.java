package Core;

public class test {
public static void main(String[] args) throws NullPointerException{
System.out.println(GenesisBlock.getGenesis("test").getBytes().toString());
System.out.println(Block.getBlock(GenesisBlock.getGenesis("test").getBytes()).getHash());
}
}
