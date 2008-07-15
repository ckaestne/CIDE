package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BlockOrSemi4 extends BlockOrSemi {
  public BlockOrSemi4(ArrayList<VarDecl> varDecl, Block block1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<VarDecl>("varDecl", varDecl),
      new PropertyOne<Block>("block1", block1)
    }, firstToken, lastToken);
  }
  public BlockOrSemi4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BlockOrSemi4(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<VarDecl> getVarDecl() {
    return ((PropertyOneOrMore<VarDecl>)getProperty("varDecl")).getValue();
  }
  public Block getBlock1() {
    return ((PropertyOne<Block>)getProperty("block1")).getValue();
  }
}
