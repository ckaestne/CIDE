package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BlockOrSemi3 extends BlockOrSemi {
  public BlockOrSemi3(ASTTextNode text95, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text95", text95),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public BlockOrSemi3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BlockOrSemi3(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText95() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text95")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
