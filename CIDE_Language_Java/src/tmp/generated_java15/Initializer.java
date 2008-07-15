package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Initializer extends GenASTNode {
  public Initializer(ASTTextNode text395, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text395", text395),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public Initializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Initializer(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText395() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text395")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
