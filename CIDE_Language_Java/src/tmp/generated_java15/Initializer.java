package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Initializer extends GenASTNode {
  public Initializer(ASTTextNode text396, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text396", text396),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public Initializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Initializer(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText396() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text396")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
