package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Initializer extends GenASTNode {
  public Initializer(ASTTextNode text24, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text24", text24),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public Initializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Initializer(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText24() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text24")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
