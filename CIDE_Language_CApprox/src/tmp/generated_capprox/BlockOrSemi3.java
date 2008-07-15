package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BlockOrSemi3 extends BlockOrSemi {
  public BlockOrSemi3(ArrayList<VarDecl> varDecl, Block block1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<VarDecl>("varDecl", varDecl),
      new PropertyOne<Block>("block1", block1)
    }, firstToken, lastToken);
  }
  public BlockOrSemi3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BlockOrSemi3(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<VarDecl> getVarDecl() {
    return ((PropertyOneOrMore<VarDecl>)getProperty("varDecl")).getValue();
  }
  public Block getBlock1() {
    return ((PropertyOne<Block>)getProperty("block1")).getValue();
  }
}
